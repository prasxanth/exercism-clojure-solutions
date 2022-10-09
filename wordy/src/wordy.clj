(ns wordy
  (:require [clojure.string :as str]
            [clojure.pprint :as pp]))

(def ops {"plus" "+" "minus" "-" "multiplied by" "*" "divided by" "/"})
(def expr-regex (->> ops keys (cons "-?\\d+") (str/join "|") re-pattern))

(defn parse-expr-string [expr-string]
  (->> expr-string (re-seq expr-regex) (map #(ops % %))))

(defn expr->sexp [[x & xs :as parsed-expr-string]]
  (pp/cl-format nil "(-> ~a ~{ ~a~})" x (partition 2 xs)))

(defn evaluate [expr-string]
  (let [expr (parse-expr-string expr-string)]
    (if (and (some? expr) (> (count expr) 1))
      (-> expr expr->sexp read-string eval)
      (throw (IllegalArgumentException. "Invalid input!")))))
