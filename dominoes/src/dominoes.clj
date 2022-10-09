(ns dominoes)

(defn linkable [[_ b1] [a2 b2]]
  (cond
    (= b1 a2) [a2 b2]
    (= b1 b2) [b2 a2]
    :else nil))

;; https://stackoverflow.com/questions/7662447/what-is-idiomatic-clojure-to-remove-a-single-instance-from-many-in-a-list
(defn remove-domino [dominoes d]
  (let [[n m] (split-with #(not= (sort d) (sort %)) dominoes)] (concat n (rest m))))

(defn chainify [dominoes chains]
  (for [c chains
        :let [ds (->> (map sort c) (into #{}) (reduce remove-domino dominoes))] ; to handle duplicates
        :let [ls (keep #(when-let [l (linkable (last c) %)] l) ds)]]
    (if
      (or (empty? ds) (empty? ls)) (partition-by #(= (first %)) c)
      (apply concat (chainify ds (map #(conj c %) ls))))))

#_(def chains (chainify dominoes [[[1 2]]]))

(defn can-chain? [[[l r] & _ :as dominoes]]
  (cond
    (empty? dominoes) true
    (= 1 (count dominoes)) (= l r)
    :else (->> (chainify dominoes [[(first dominoes)]])
               first
               (map count)
               (some #(= (count dominoes) %)))))


;; Most community solutions fail on tests that contain repeated stones such as those below
;; (def dominoes [[1 2] [2 3] [3 1] [2 4] [2 4] [5 1] [1 2] [1 2]])
;; (def dominoes [[1 2] [5 3] [3 1] [1 2] [2 4] [1 6] [2 3] [3 4] [5 6] [1 2]])
;; (def dominoes [[1 2] [1 2] [1 2]])
