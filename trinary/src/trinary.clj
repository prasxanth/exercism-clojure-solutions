(ns trinary)

;; Inspired by
;; https://exercism.org/tracks/clojure/exercises/trinary/solutions/leetwinski :)

(def digits {\0 0 \1 1 \2 2})

(defn to-decimal [num-str]
    (if (every? digits num-str)
      (->> num-str seq (reduce #(+ (* 3 %1) (digits %2)) 0))
      0))

