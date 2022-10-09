(ns robot-name)

(defn rand-char [n]
  (->> (rand-int 26) (+ n) char))

(defn rand-items [f n]
  (->> (repeatedly f) (take n)))

(defn make-robot-name []
 (->> (concat (rand-items #(rand-char 65) 2)
              (rand-items #(rand-int 9) 3))
      (apply str)))

;; This part of the solution is from
;; https://exercism.org/tracks/clojure/exercises/robot-name/solutions/nomonamo

(defn robot []
  (ref {:name (make-robot-name)}))

(defn robot-name [robot]
  (:name @robot))

(defn reset-name [robot]
  (dosync (alter robot assoc :name (make-robot-name))))
