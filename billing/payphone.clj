(ns billing.payphone
  "Implementación PayPhone para sandbox y producción.")

(def ^:private base-url
  (or (System/getenv "PAYPHONE_BASE_URL")
      "https://paypayl.io/launch"))

(def ^:private token
  (System/getenv "PAYPHONE_TOKEN"))

(def ^:private store-id
  (System/getenv "PAYPHONE_STORE_ID"))

(defn- headers []
  {:Authorization (str "Bearer " token)
   :Content-Type "application/json"})

(defn prepare [user-id plan-id]
  (let [session-id (str "pp-" user-id "-" plan-id "-" (System/currentTimeMillis))
        redirect-url (str base-url "?session=" session-id)]
    {:session-id session-id
     :redirect-url redirect-url
     :status :pending}))

(defn redirect [session-id]
  {:redirect-url (str base-url "?session=" session-id)})

(defn confirm [session-id]
  (let [resp (clj-http.client/post
               (str base-url "/confirm")
               {:headers (headers)
                :form-params {:sessionId session-id :storeId store-id}
                :as :json})]
    {:status (if (= "success" (get-in resp [:body :status])) :confirmed :pending)
     :invoice-id (get-in resp [:body :transactionId] session-id)
     :amount (get-in resp [:body :amount] 0)
     :date (java.time.Instant/now)}))

(defn ledger [user-id]
  (let [resp (clj-http.client/get
               (str base-url "/ledger/" user-id)
               {:headers (headers)
                :as :json})]
    (mapv (fn [tx]
            {:invoice-id (:transactionId tx)
             :amount (:amount tx)
             :date (java.time.Instant/parse (:date tx))
             :status (keyword (:status tx))
             :plan-id (:planId tx)})
          (get-in resp [:body :transactions]))))