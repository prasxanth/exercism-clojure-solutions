(ns collatz-conjecture)

(defn rules [n]
  (if (even? n) (/ n 2) (-> n (* 3) inc)))

(defn collatz [n]
  (cond
    (< n 1) (throw (IllegalArgumentException. "Number cannot be less than 1"))
    (= n 1) 0
    :else (->> (iterate rules n) (take-while #(not= 1 %)) count)))
