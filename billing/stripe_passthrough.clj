(ns billing.stripe-passthrough
  "Adaptador que delega al stripe.clj del fork.
   Permite mantener Stripe como provider sin modificar el upstream.")

(defn prepare [user-id plan-id]
  (let [session (instant.stripe/create-checkout-session user-id plan-id)]
    {:session-id (:session-id session)
     :redirect-url (:url session)
     :status :pending}))

(defn redirect [session-id]
  {:redirect-url (format "https://billing.stripe.com/p/login/%s" session-id)})

(defn confirm [session-id]
  (let [subscription (instant.stripe/retrieve-subscription session-id)]
    {:status (if (:active subscription) :confirmed :pending)
     :invoice-id (:invoice-id subscription)
     :amount (:amount subscription)
     :date (java.time.Instant/now)}))

(defn ledger [user-id]
  (mapv (fn [inv]
          {:invoice-id (:id inv)
           :amount (:amount-due inv)
           :date (java.time.Instant/ofEpochSecond (:created inv))
           :status (if (= "paid" (:status inv)) :confirmed :pending)
           :plan-id (:plan-id inv)})
        (instant.stripe/list-invoices user-id)))