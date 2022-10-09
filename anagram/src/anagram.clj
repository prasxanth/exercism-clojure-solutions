(ns anagram
  (:require [clojure.string :as str]))

(defn anagram? [word candidate]
  (let [x (str/lower-case word)
        y (str/lower-case candidate)]
    (and (not= x y) (= (sort x) (sort y)))))

(defn anagrams-for [word candidates]
  (filter (partial anagram? word) candidates))
