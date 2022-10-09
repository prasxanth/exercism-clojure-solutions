(ns change
  #_(:require [clojure.math.combinatorics :as comb]))

;; This solution uses brute force and scales as O(N!) where N is the number of coins
;; For a more efficient solution see the note at the end

(defn permutations [coll]
  (if (= 1 (count coll))
    (list coll)
    (for [head coll
          tail (permutations (disj (set coll) head))]
      (cons head tail))))

(defn make-change [coins amount]
  (->> coins
       (filter #(<= % amount))
       (reduce #(vector (rem (first %1) %2)
                        (assoc (second %1) %2 (quot (first %1) %2)))
               [amount {}])
       second))

(defn find-minimum-coins [coins amount]
  (->> coins
       (apply sorted-set)
       permutations
       (mapv #(make-change % amount))
       (apply min-key #(reduce + (vals %)))
       (mapcat (fn [[k v]] (repeat v k)))
       sort))

(defn issue [amount coins]
  (let [min-coins (find-minimum-coins coins amount)]
    (if (or (neg? amount)
            (and (pos? amount) (every? #(< amount %) coins))
            (not= (reduce + min-coins) amount))
      (throw (IllegalArgumentException. "cannot change"))
      min-coins)))

;; leetwinksi provides a more scalable and efficient solution
;; based on Dynamic Programming
#_(defn issue [sum coins]
   (when (or (neg? sum) (and (pos? sum) (every? #(< sum %) coins)))
     (throw (IllegalArgumentException. "cannot change")))
   (let [coins (sort coins)
         all-amounts (reduce (fn [cached-amounts amount]
                               (->> coins
                                    (filter #(<= % amount))
                                    (map #(conj (cached-amounts (- amount %) []) %))
                                    (apply min-key count)
                                    (assoc cached-amounts amount)))
                             {} (range 1 (inc sum)))]
     (all-amounts sum)))
