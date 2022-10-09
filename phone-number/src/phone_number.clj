(ns phone-number)

;; Solution closely follows:
;; https://exercism.org/tracks/clojure/exercises/phone-number/solutions/Testare

(defn number [num-string]
  (or (->> num-string
           (re-seq #"\d")
           (apply str)
           (re-matches #"1?([2-9]\d\d[2-9]\d{6})")
           last)
      "0000000000"))

(defn area-code [num-string]
  (-> num-string number (subs 0 3)))
 
(defn pretty-print [num-string]
  (->> num-string
       number
       (re-matches #"(\d{3})(\d{3})(\d+)")
       rest
       (apply format "(%s) %s-%s")))
