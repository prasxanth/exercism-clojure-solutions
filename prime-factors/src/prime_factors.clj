(ns prime-factors)

;; Modified version of https://rosettacode.org/wiki/Prime_decomposition#Clojure
(defn of [number]
  (loop [n number k 2 factors []]
    (cond
      (= 1 n) factors
      (zero? (rem n k)) (recur (quot n k) k (conj factors k))
      :else (recur n (inc k) factors))))
