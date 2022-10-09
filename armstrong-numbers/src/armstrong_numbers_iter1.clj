(ns armstrong-numbers)

(defn pow [x n]
  (reduce * (repeat n x)))

(defn digits [num]
  (->> num str (map (comp read-string str))))

(defn digits-power-sum [num]
  (let [ds (digits num)]
    (->> ds
         (map #(pow % (count ds)))
         (reduce +))))

(defn armstrong? [num]
  (= num (digits-power-sum num)))
