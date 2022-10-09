(ns sum-of-multiples)

(defn sum-of-multiples [ns x]
   (->> ns (mapcat #(range 0 x %)) distinct (reduce +)))
