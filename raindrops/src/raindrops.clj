(ns raindrops)

;; This is written to be extensible to any number of factors not just the 3, 5 and 7 as given in the problem

(require '[clojure.string :as str])

(def factor->sound {3 "Pling" 5 "Plang" 7 "Plong"})

(defn divisible? [m n]
  (zero? (mod m n)))

(defn factors [n]
  (filter (partial divisible? n) (keys factor->sound)))

(defn convert [n] ;; <- arglist goes here
  (let [fs (factors n)]
    (if (empty? fs) (str n) (->> fs (map factor->sound) str/join))))
