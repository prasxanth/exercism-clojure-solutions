(ns say
  (:require [clojure.pprint :as pp]
            [clojure.string :as str]))

(defn number [num]
  (if (<= 0 num 999999999999)
    (->> num (pp/cl-format nil "~r") (#(str/replace % #"," "")))
    (throw (IllegalArgumentException. "Invalid number"))))
