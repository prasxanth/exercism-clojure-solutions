(ns queen-attack
  (:require [clojure.pprint :as pp]))

(defn make-empty-board []
  (->> "_" (repeat 8) (repeat 8) (mapv vec)))

(defn place-queens [{:keys [w b]}  board]
  (if (some nil? [w b]) board (-> board (assoc-in w \W) (assoc-in b \B))))

(defn board-string [queens]
  (->> (make-empty-board)
       (place-queens queens)
       (map #(apply pp/cl-format nil "~@{~a~^ ~}\n" %))
       (apply str)))

(defn can-attack [{[xw yw] :w [xb yb] :b}]
  (or (= xw xb)
      (= yw yb)
      (= (Math/abs (- xw xb)) (Math/abs (- yw yb)))))
