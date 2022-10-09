(ns go-counting
  (:require [clojure.set :as cset]))

(defn transpose [xs]
  (apply mapv vector xs))

(defn parse-grid [grid]
  (->> grid (map seq) (mapv #(vec %)) transpose))

(def ordinals [[-1 0] [0 -1] [1 0] [0 1]])

(defn neighbors [[x y]]
  (mapv (partial mapv + [x y]) ordinals))

(defn point->territory [grid [x y]]
  (loop [[p & ps] [[x y]] nodes {:boundary #{} :region #{}}]
    (if (nil? p)
      nodes
      (let [el (get-in grid p)]
        (cond
          (or (nil? el) (contains? (:region nodes) p)) (recur ps nodes)
          (not= \space el) (recur ps (update nodes :boundary conj {:pos p :stone el}))
          :else (recur (apply conj ps (neighbors p)) (update nodes :region conj p)))))))

(defn territory [grid [x y]]
  (let [board (parse-grid grid)]
    (if (and (<= 0 x (dec (count board))) (<= 0 y (dec (count (first board)))))
      (as-> (point->territory board [x y]) it
        (hash-map :stones (:region it)
                  :owner (some->> (when (seq (:region it)) (:boundary it))
                                  (map :stone)
                                  set
                                  (get {#{\B} :black #{\W} :white}))))
      (throw (Exception. "Coordinates must be within board!")))))

(defn collect-territories [color territories]
  (->> territories
       (filter #(and (= color (:owner %)) (seq (:stones %))))
       (map :stones)
       (apply cset/union)
       (hash-map (keyword (str (if color (name color) "null") "-" "territory")))))

(defn territories [grid]
  (let [board (->> grid parse-grid transpose)]
    (as-> (for [i (range (count board))
                j (range (count (first board)))] ;; brute force, ideally the visited regions must be skipped during iteration!
            (territory board [j i])) it
      (map #(collect-territories % it) [:black :white nil])
      (into {} it))))
