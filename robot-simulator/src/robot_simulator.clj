(ns robot-simulator)

(defn robot [coordinates bearing]
  (hash-map :coordinates coordinates :bearing bearing))

(def ^:const turn-right {:north :east :east :south :south :west :west :north})

(def ^:const turn-left (zipmap (vals turn-right) (keys turn-right)))

;; https://exercism.org/tracks/clojure/exercises/robot-simulator/solutions/pzvyagin
(def displace {:north #(update % :y inc)
               :east  #(update % :x inc)
               :south #(update % :y dec)
               :west  #(update % :x dec)})

(defn move [droid instruction]
  (case instruction
    \L (update droid :bearing turn-left)
    \R (update droid :bearing turn-right)
    \A (update droid :coordinates ((:bearing droid) displace))
    (throw (IllegalArgumentException. "Invalid instruction!"))))

(defn simulate [commands droid]
  (reduce move droid commands))
