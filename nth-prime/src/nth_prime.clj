(ns nth-prime)

;; https://stackoverflow.com/questions/960980/fast-prime-number-generation-in-clojure
;; See https://otee.dev/2022/01/17/lazy-clojure.html for an excellent explanation!
(def primes
  (cons 1 (lazy-seq
            (filter (fn [i]
                      (not-any? (fn [p] (zero? (rem i p)))
                                (take-while #(<= % (Math/sqrt i)) (rest primes))))
                    (drop 2 (range))))))

(defn nth-prime [n]
  (if (zero? n)
    (throw (IllegalArgumentException. "There is no zeroth prime"))
    (nth primes n)))
