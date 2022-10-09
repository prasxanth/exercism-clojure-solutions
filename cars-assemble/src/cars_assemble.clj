(ns cars-assemble)

(defn success-rate 
  "Returns success rate for given speed"
  [speed]
  (cond 
    (zero? speed) 0.0
    (and (>= speed 1) (<= speed 4)) 1.0
    (and (>= speed 5) (<= speed 8)) 0.9
    (= speed 9) 0.8
    :else 0.77))

(defn production-rate
  "Returns the assembly line's production rate per hour,
   taking into account its success rate"
  [speed]
  (* 221.0 speed (success-rate speed)))

(defn working-items
  "Calculates how many working cars are produced per minute"
  [speed]
  (-> speed
    production-rate
    (/ 60)
    int))
