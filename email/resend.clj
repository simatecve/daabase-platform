(ns email.resend
  "Implementación de producción via Resend API.")

(def ^:private api-key
  (System/getenv "RESEND_API_KEY"))

(def ^:private from-hola
  (or (System/getenv "RESEND_FROM_HOLA") "hola@daabase.click"))

(def ^:private from-verify
  (or (System/getenv "RESEND_FROM_VERIFY") "verify@daabase.click"))

(def ^:private from-teams
  (or (System/getenv "RESEND_FROM_TEAMS") "teams@daabase.click"))

(def ^:private from-billing
  (or (System/getenv "RESEND_FROM_BILLING") "billing@daabase.click"))

(defn- resend-req [payload]
  (clj-http.client/post
    "https://api.resend.com/emails"
    {:headers {:Authorization (str "Bearer " api-key)
               :Content-Type "application/json"}
     :body (cheshire.core/generate-string payload)
     :as :json}))

(defn send! [{:keys [to subject html from]}]
  (let [from-addr (or from from-hola)]
    (resend-req {:from from-addr
                 :to [to]
                 :subject subject
                 :html html})))

(defn send-magic-code! [email code]
  (send! {:to email
          :from from-verify
          :subject "Tu código de acceso a daabase"
          :html (format "<p>Tu código de acceso es: <strong>%s</strong></p><p>Expira en 10 minutos.</p>"
                        code)}))

(defn send-waitlist-confirmation! [email]
  (send! {:to email
          :from from-hola
          :subject "Bienvenido a daabase — estás en la lista"
          :html "<p>Gracias por unirte a daabase. Te avisaremos cuando lancemos.</p>"}))