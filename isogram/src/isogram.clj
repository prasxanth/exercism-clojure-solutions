(ns isogram
  (:require [clojure.string :as str]))

(defn isogram? [word]
  (->> word
       str/lower-case
       (#(str/replace % #"-| " ""))
       (apply distinct?)))
