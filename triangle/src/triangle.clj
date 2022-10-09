(ns triangle)

(defn valid? [a b c]
  (and (every? pos? [a b c])
       (>= (+ a b) c)
       (>= (+ b c) a)
       (>= (+ c a) b)))

(defn type? [compare-op side-op a b c]
  (-> (compare-op true? (list (side-op a b)
                              (side-op b c)
                              (side-op c a)))
      boolean
      (and (valid? a b c))))

(def equilateral? (partial type? every? =))
(def isosceles?  (partial type? some =))
(def scalene? (partial type? every? not=))
