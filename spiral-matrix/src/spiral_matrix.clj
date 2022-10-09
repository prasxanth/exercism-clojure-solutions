(ns spiral-matrix)

;; Based on: https://rosettacode.org/wiki/Talk:Spiral_matrix#J
;; Solution closely follows: https://rosettacode.org/wiki/Spiral_matrix#Clojure

(defn grade [xs]
  (let [n (count xs)]
    (->> xs (map vector (range 0 n)) (sort-by second) (map first))))

(defn make-spiral-series [n]
  (let [cyc (cycle [1 n -1 (- n)])]
    (->> (range (dec n) 0 -1)
         (mapcat #(repeat 2 %))
         (cons n)
         (mapcat #(repeat %2 %1) cyc))))

(defn spiral [n]
  (if (pos? n)
    (->> (make-spiral-series n)
         (reductions +)
         grade
         (map inc)
         (partition n))
    '()))
