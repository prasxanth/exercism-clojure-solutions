(ns matching-brackets
  (:require [clojure.string :as str]))

(defn converge [x f] ;; see: https://code.jsoftware.com/wiki/Vocabulary/hatco#u.5E:_.28Converge.29
    (reduce (fn [y g] (if (= y (g y)) (reduced y) (g y))) x (repeat f)))

(defn remove-bracket-pairs [text]
     (str/replace text #"\[\]|\(\)|\{\}" ""))

(defn valid? [bracket-str]
  (-> (re-seq #"\[|\]|\(|\)|\{|\}" bracket-str)
      str/join
      (converge remove-bracket-pairs) ;; recursively remove all pairs
      empty?))
