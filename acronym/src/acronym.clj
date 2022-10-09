(ns acronym)
(require '[clojure.string :as str])

(defn acronym [long-name] ;; <- arglist goes here
  ;; your code goes here
  (->> (str/split long-name #" |(?<=[a-z])(?=[A-Z])|-")
    (map (comp first str/upper-case))
    (str/join)))
