(ns matching-brackets
  (:require [clojure.string :as str]))

;; Values for each bracket are arbitrary (prime) numbers
(def scores {\( 2 \) -2 \[ 13 \] -13 \{ 29 \} -29})

(defn balanced? [xs]
  "Closing and opening brackets must be of same count"
  (let [rs (reductions + xs)]
    (and (zero? (last rs)) (not-any? neg? rs))))

(defn matched? [xs]
  "Closing bracket must match opening bracket when next to it => `([{]})`"
  (->> (map #(and (pos? %1) (neg? %2) (not= 0 (+ %1 %2))) xs (rest xs))
       (not-any? true?)))

(defn valid? [bracket-str]
    (->> bracket-str
         (keep (set (keys scores)))
         (map scores)
         ((every-pred matched? balanced?))))

