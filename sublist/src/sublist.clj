(ns sublist)

(defn sublist? [xs ys]
  {:pre (<= (count xs) (count ys))}
  (->> (partition (count xs) 1 ys)
       (map (partial = xs))
       (some true?)))

(defn classify [xs ys]
    (cond
      (= xs ys) :equal
      (sublist? xs ys) :sublist
      (sublist? ys xs) :superlist
      :else :unequal))
