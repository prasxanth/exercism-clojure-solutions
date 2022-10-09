(ns perfect-numbers)

(defn factors [n]
  (->> (/ n 2) int inc (range 1) (remove #(pos? (rem n %)))))

(defn classify [n]
  (if (pos? n)
    (case (compare n (->> n factors (reduce +)))
     -1 :abundant
      0 :perfect
      1 :deficient)
    (throw (IllegalArgumentException. "Number cannot be negative!"))))
