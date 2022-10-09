(ns atbash-cipher
  (:require [clojure.string :as str]))

(def cipher (as-> (map char (range 97 123)) alphabets
                  (zipmap alphabets (reverse alphabets))))

(defn remove-punctuation [sentence]
  (str/replace sentence #"[^\d\w]+" ""))

(defn encode [sentence]
  (->> sentence
       str/lower-case
       remove-punctuation
       (map #(cipher % %))
       (partition-all 5)
       (map str/join)
       (str/join " ")))
