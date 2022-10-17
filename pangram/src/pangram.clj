(ns pangram
  (:require [clojure.string :as str]))

(defn pangram? [sentence]
  (->> sentence
       str/lower-case
       (map int)
       set
       (#(every? % (range 97 123)))))

