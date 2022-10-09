(ns isbn-verifier
 (:require [clojure.string :as str]))

(defn isbn? [isbn]
  (boolean
    (some->> (str/replace isbn #"-" "")
             (re-matches #"^\d{9}[\d|X]$")
             (map #(if (= \X %) 10 (Character/digit % 10)))
             (map * (iterate dec 10))
             (reduce +)
             (#(zero? (mod % 11))))))
