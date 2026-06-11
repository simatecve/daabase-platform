(ns billing.provider
  "Abstracción de proveedor de pagos.
   Usa ( System/getenv \"BILLING_PROVIDER\" ) para seleccionar implementación."
  (:require [billing.stub]
            [billing.payphone]
            [billing.stripe-passthrough]))

(defmulti make-provider
  "Factory: devuelve la implementación activa según BILLING_PROVIDER."
  (fn [] (or (System/getenv "BILLING_PROVIDER") "stub")))

(defmethod make-provider "stub" [_]
  (reify billing.provider.protocol/BillingProvider
    (prepare [_ user-id plan-id] (billing.stub/prepare user-id plan-id))
    (redirect [_ session-id] (billing.stub/redirect session-id))
    (confirm [_ session-id] (billing.stub/confirm session-id))
    (ledger [_ user-id] (billing.stub/ledger user-id))))

(defmethod make-provider "payphone" [_]
  (reify billing.provider.protocol/BillingProvider
    (prepare [_ user-id plan-id] (billing.payphone/prepare user-id plan-id))
    (redirect [_ session-id] (billing.payphone/redirect session-id))
    (confirm [_ session-id] (billing.payphone/confirm session-id))
    (ledger [_ user-id] (billing.payphone/ledger user-id))))

(defmethod make-provider "stripe" [_]
  (reify billing.provider.protocol/BillingProvider
    (prepare [_ user-id plan-id] (billing.stripe-passthrough/prepare user-id plan-id))
    (redirect [_ session-id] (billing.stripe-passthrough/redirect session-id))
    (confirm [_ session-id] (billing.stripe-passthrough/confirm session-id))
    (ledger [_ user-id] (billing.stripe-passthrough/ledger user-id))))

(def ^:private provider-instance (delay (make-provider)))

(defn provider []
  @provider-instance)

;; Interfaces
(defprotocol BillingProvider
  (prepare [this user-id plan-id]
    "Inicia sesión de pago. Devuelve {:session-id \"...\" :redirect-url \"...\"}")
  (redirect [this session-id]
    "Redirige al usuario al portal del provider.")
  (confirm [this session-id]
    "Confirma el estado de un pago. Devuelve {:status :pending|:confirmed|:failed :invoice-id \"...\"}")
  (ledger [this user-id]
    "Devuelve el historial de pagos del usuario. Devuelve [{:invoice-id :amount :date :status}]"))