(ns minesweeper
  (:require [clojure.string :as str]))

;; Approach: https://www.jsoftware.com/help/learning/31.htm

(defn emapv [f & colls]
  (apply mapv (partial mapv f) colls))

(defn board-str->board-bool [board-string]
  (->> board-string
       str/split-lines
       (map seq)
       (emapv {\space 0 \* 1})))

(defn shift [n xs ys]
  (if (>= n 0)
    (concat (drop n ys) (take n xs))
    (concat (take-last (- n) xs) (drop-last (- n) ys))))

(defn shift-board [board row col fill]
  (let [fill-vals (emapv (constantly fill) board)]
    (cond->> board
      (not= 0 col) (map (partial shift col) fill-vals)
      (not= 0 row) (shift row fill-vals))))

(def ordinals (for [x [-1 0 1] y [-1 0 1]] [x y]))

(defn count-mines [board]
    (->> (map (fn [[r c]] (shift-board board r c 0)) ordinals)
         (apply emapv +)))

(defn draw [board-string]
   (let [board (board-str->board-bool board-string)]
      (->> board
           count-mines
           (emapv (fn [x y] (cond (= 1 x) "*" (= 0 y) " " :else (str y))) board)
           (map str/join)
           (str/join "\n"))))
