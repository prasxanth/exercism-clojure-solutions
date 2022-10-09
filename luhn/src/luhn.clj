(ns luhn
  (:require [clojure.string :as str]))

(defn algo [n]
  (let [x (* n 2)]
    (if (> x 9) (- x 9) x)))

(defn valid? [instr]
  (let [xs (str/replace instr #" " "")]
    (if (and (= xs (re-find #"\d+" xs)) (not= "0" xs))
      (->> xs
           (map #(Character/digit % 10))
           reverse
           (map-indexed (fn [i x] (if (odd? i) (algo x) x)))
           (reduce +)
           (#(zero? (rem % 10))))
      false)))
