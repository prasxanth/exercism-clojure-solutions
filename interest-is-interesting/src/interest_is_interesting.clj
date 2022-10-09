(ns interest-is-interesting)

(defn interest-rate
  "TODO: add docstring"
  [balance]
  (cond
    (neg? balance) -3.213
    (< balance 1000.0M) 0.5
    (< balance 5000.0M) 1.621
    :else 2.475))

(defn annual-balance-update
  "TODO: add docstring"
  [balance]
  (-> balance
    interest-rate
    (/ 100)
    abs
    bigdec
    (* balance)
    (+ balance)))

(defn amount-to-donate
  "TODO: add docstring"
  [balance tax-free-percentage]
  (if
    (pos? balance) (-> balance
                      (* (* tax-free-percentage 2.0))
                     (/ 100.0)
                     int)
    0))