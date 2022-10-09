(ns sieve)

(defn irange [start stop step]
  (range start (inc stop) step))

(defn sieve [limit]
  (loop [[x & xs] (irange 3 limit 2) primes [2]]
    (if (= 1 (count xs)) (concat primes [x] xs)
        (recur (remove (into #{} (irange x (last xs) x)) xs) (conj primes x)))))
