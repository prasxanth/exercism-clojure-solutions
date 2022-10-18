(ns raindrops)

(def factor->sound {3 "Pling" 5 "Plang" 7 "Plong"})

(defn divisible? [m n]
  (zero? (mod m n)))

(defn factors [n]
  (filter #(divisible? n %) (keys factor->sound)))

(defn convert [n]
  (if-let [fs (seq (factors n))]
    (->> fs (map factor->sound) (apply str))
    (str n)))
