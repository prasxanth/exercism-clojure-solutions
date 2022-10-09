(ns complex-numbers)

(defn proc [f1 f2 [a b] [c d]]
  [(f1 (* a c) (* b d))
   (f2 (* b c) (* a d))])

(defn sum-of-squares [a b]
  (+ (* a a) (* b b)))

(def real first)

(def imaginary second)

(defn abs [[a b]]
  (-> (sum-of-squares a b) Math/sqrt))

(defn conjugate [c] (update c 1 -))

(def add (partial map +))

(def sub (partial map -))

(def mul (partial proc - +))

(defn div [[a b] [c d]]
  (let [denom (sum-of-squares c d)]
    (->> (proc + - [a b] [c d])
         (mapv #(/ % denom 1.0)))))
