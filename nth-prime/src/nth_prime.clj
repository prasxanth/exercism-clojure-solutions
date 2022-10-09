(ns nth-prime)

(defn divides? [m n] (zero? (mod m n)))

(defn prime? [n]
 (->> (range 2 n)
  (not-any? #(divides? n %))))

(defn nth-prime [n]
  (if (zero? n) (throw (IllegalArgumentException. "There is no zeroth prime"))
    (nth (->> (range 2 (* 10 n)) (filter prime?)) (dec n))))
