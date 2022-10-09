(ns reverse-string)
(require '[clojure.string :as str])

(defn reverse-string [s] ;; <- arglist goes here
  ;; your code goes here
(reduce #(str %2 %1) "" s))
