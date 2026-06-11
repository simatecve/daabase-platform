(ns billing.stub
  "Implementación stub para desarrollo y tests.
   No conecta a ningún proveedor real; simula respuestas.")

(def plans
  {::free  {:id "free"  :name "Free"        :amount 0}
   ::starter {:id "starter" :name "Starter"  :amount 500}
   ::pro    {:id "pro"    :name "Pro"        :amount 1500}})

(defn prepare [user-id plan-id]
  {:session-id (str "stub-session-" user-id "-" plan-id "-" (System/currentTimeMillis))
   :redirect-url (str "/billing/stub/success?session=stub-session-" userId "-" plan-id)
   :status :pending})

(defn redirect [session-id]
  {:redirect-url (str "/billing/stub/success?session=" session-id)})

(defn confirm [session-id]
  {:status :confirmed
   :invoice-id (str "stub-invoice-" session-id)
   :amount 0
   :date (java.time.Instant/now)})

(defn ledger [user-id]
  [{:invoice-id (str "stub-invoice-" user-id "-free")
    :amount 0
    :date (java.time.Instant/now)
    :status :confirmed
    :plan-id "free"}])