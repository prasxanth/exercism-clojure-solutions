(ns pascals-triangle)

;; Modified version of https://rosettacode.org/wiki/Pascal%27s_triangle#Clojure
;; for big integers
(def triangle (iterate #(concat [1N] (map + % (rest %)) [1N]) [1N]))

(defn row [n]
  (->> n dec (nth triangle)))
