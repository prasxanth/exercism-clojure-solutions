(ns wordy
  (:require [clojure.string :as str]
            [clojure.pprint :as pp]))

(def ops {"plus" "+" "minus" "-" "multiplied by" "*" "divided by" "/"})
(def ops-regex (->> ops keys (str/join "|") re-pattern))

(defn parse-expr-string [expr-string]
  (some-> (re-find #"(-?\d.*\d+)(.*?$)" expr-string)
          (#(when (= "?" (last %)) %)) ;; to fail "What is 52 cubed?"
          second
          (str/replace ops-regex ops)
          (str/split #" ")))

(defn expr->sexp [[x & xs :as parsed-expr-string]]
  (pp/cl-format nil "(-> ~a ~{ ~a~})" x (partition 2 xs)))

(defn evaluate [expr-string]
  (if-let [expr (parse-expr-string expr-string)]
    (-> expr expr->sexp read-string eval)
    (throw (IllegalArgumentException. "Invalid input!"))))
