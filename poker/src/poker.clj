(ns poker
  (:require [clojure.string :as str]))

(defn parse-card [card]
  (let [faces->numstr {"J" "11" "Q" "12" "K" "13" "A" "14"}]
    (->> (str/replace card #"J|Q|K|A" faces->numstr)
         (re-matches #"(\d+)(.*)")
         rest
         (mapv read-string))))

(defn ace-low? [rank]
  (= [2 3 4 5 14] (sort rank)))

(defn parse-hand [hand]
  (let [[r s] (->> (str/split hand #" ")
                   (mapv parse-card)
                   (apply mapv vector))]
    (hash-map :rank (if (ace-low? r) [1 2 3 4 5] r)
              :suit (set s))))

(defn flush? [suit]
  (= 1 (count suit)))

(defn straight? [rank]
  (and (= 5 (count (distinct rank)))
       (= 4 (- (apply max rank) (apply min rank)))))

(defn of-kind [rank]
  (-> rank frequencies vals sort reverse vec))

(defn sort-rank [rank]
  "Sorts by frequencies, then by individual ranks"
  (->> rank
       (sort >)
       (partition-by identity)
       (group-by count)
       (into (sorted-map-by >))
       vals
       flatten
       vec))

(defn classify-hand [{:keys [rank suit]}]
  (cond
    (and (flush? suit) (straight? rank)) 8
    (= [4 1] (of-kind rank)) 7
    (= [3 2] (of-kind rank)) 6
    (flush? suit) 5
    (straight? rank) 4
    (= [3 1 1] (of-kind rank)) 3
    (= [2 2 1] (of-kind rank)) 2
    (= [2 1 1 1] (of-kind rank)) 1
    :else 0))

(defn score-hand [hand]
  (-> hand
      parse-hand
      (update :rank sort-rank)
      (#(into [(classify-hand %)] (:rank %)))))

(defn best-hands [hands]
  (as-> (mapv #(hash-map :hand % :score (score-hand %)) hands) it
    (sort-by :score #(compare %2 %1) it)
    (filter #(= (:score (first it)) (:score %)) it)
    (mapv :hand it)))

