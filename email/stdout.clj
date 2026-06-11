(ns email.stdout
  "Implementación de desarrollo. Imprime los emails en stdout del servidor.
   Réplica el comportamiento del upstream con Postmark en modo dev.")

(defn send! [{:keys [to subject html from]}]
  (println (format "\n=== EMAIL ===\nTo: %s\nFrom: %s\nSubject: %s\n%s\n==========\n"
                   to (or from "noreply@daabase.click") subject html)))

(defn send-magic-code! [email code]
  (send! {:to email
          :from (or (System/getenv "RESEND_FROM_VERIFY") "verify@daabase.click")
          :subject "Tu código de acceso a daabase"
          :html (format "<p>Tu código de acceso es: <strong>%s</strong></p><p>Expira en 10 minutos.</p>"
                        code)}))

(defn send-waitlist-confirmation! [email]
  (send! {:to email
          :from (or (System/getenv "RESEND_FROM_HOLA") "hola@daabase.click")
          :subject "Bienvenido a daabase — estás en la lista"
          :html "<p>Gracias por unirte a daabase. Te avisaremos cuando lancemos.</p>"}))