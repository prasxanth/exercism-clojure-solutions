(ns collatz-conjecture)

(defn- conditions [number]
  (cond 
   (> 1 number) (throw (IllegalArgumentException. "zero is an error"))
   (even? number) (quot number 2)
   (odd? number) (->> (* number 3) inc)
   (= 1 number) 1))

(defn- series [number]
 (if (= 1 number) [1]
  (cons number (->> number conditions series))))
 
(defn collatz [number]
 (->> number series count dec))


