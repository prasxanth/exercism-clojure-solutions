(ns hamming)

(defn distance
  "Calculates the Hamming Distance between two DNA strands"
  [strand1 strand2]
  (when (= (count strand1) (count strand2))
    (->> (map not= strand1 strand2)
         (filter true?)
         count)))
