(ns interest-is-interesting)

(defn interest-rate
  [balance]
  (cond
    (neg? balance) -3.213
    (< balance 1000.0M) 0.5
    (< balance 5000.0M) 1.621
    :else 2.475))

(defn annual-balance-update
  [balance]
  (-> balance
    interest-rate
    (/ 100)
    Math/abs
    bigdec
    (* balance)
    (+ balance)))

(defn amount-to-donate
  [balance tax-free-percentage]
  (if (pos? balance)
    (-> balance (* (* tax-free-percentage 2.0)) (/ 100.0) int)
    0))
