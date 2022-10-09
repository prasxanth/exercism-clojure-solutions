(ns anagram)

(require '[clojure.string :as str])

(defn anagram?
 "Returns true if candidate is an anagram of word"
 [word candidate]
  (let [x (str/lower-case word)
        y (str/lower-case candidate)]
    (and (not= x y) (= (sort x) (sort y)))))

(defn anagrams-for 
 "Given a word and a list of candidates, select the sublist of anagrams of the given word"
 [word candidates]
 (filter (partial anagram? word) candidates))