(ns pangram)

(require '[clojure.string :as str])

(def lower-case-alphabet (->> (range 97 123) (map char) set))
(defn pangram? [sentence] ;; <- arglist goes here
  (-> sentence
    str/lower-case
    set
    (every? lower-case-alphabet)))
