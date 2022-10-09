(ns minesweeper
  (:require [clojure.string :as str]))

(defn parse-board [board-str]
  (->> board-str str/split-lines (mapv (comp vec seq))))

(def directions (->> (for [x [-1 0 1] y [-1 0 1]] [x y]) (remove #{[0 0]})))

(defn neighbors [board [x y]]
  (map (fn [[r c]] (get-in board [(+ r x) (+ c y)])) directions))

(defn count-neighbors [board [x y]]
  (->> (neighbors board [x y])
       (keep #{\*})
       count
       (#(if (zero? %) " " (str %)))))

(defn count-mines [board]
  (for [[i x] (map-indexed vector board)
        [j y] (map-indexed vector x)]
    (if (= \* y) "*" (count-neighbors board [i j]))))

(defn draw [board-str]
  (let [board (parse-board board-str)
        ncol (->> board first count)]
    (->> board count-mines (partition ncol) (map str/join) (str/join "\n"))))

