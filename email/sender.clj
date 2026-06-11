(ns email.sender
  "Abstracción de envío de email.
   Usa (System/getenv \"EMAIL_PROVIDER\") para seleccionar implementación."
  (:require [email.stdout]
            [email.resend]))

(defmulti make-sender
  (fn [] (or (System/getenv "EMAIL_PROVIDER") "stdout")))

(defmethod make-sender "stdout" [_]
  (reify email.sender.protocol/EmailSender
    (send! [_ opts] (email.stdout/send! opts))
    (send-magic-code! [_ email code] (email.stdout/send-magic-code! email code))
    (send-waitlist-confirmation! [_ email] (email.stdout/send-waitlist-confirmation! email))))

(defmethod make-sender "resend" [_]
  (reify email.sender.protocol/EmailSender
    (send! [_ opts] (email.resend/send! opts))
    (send-magic-code! [_ email code] (email.resend/send-magic-code! email code))
    (send-waitlist-confirmation! [_ email] (email.resend/send-waitlist-confirmation! email))))

(def ^:private sender-instance (delay (make-sender)))

(defn sender []
  @sender-instance)

;; Protocolo
(defprotocol EmailSender
  (send! [this opts]
    "Envía un email. opts = {:to :subject :html :from}")
  (send-magic-code! [this email code]
    "Envía magic code OTP al email del usuario")
  (send-waitlist-confirmation! [this email]
    "Envía confirmación de inscripción al waitlist"))