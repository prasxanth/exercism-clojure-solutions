(ns clock)

(defn clock [hours minutes]
  (-> hours (* 60) (+ minutes) (mod 1440)))

(defn clock->string [minutes]
    (format "%02d:%02d" (quot minutes 60) (rem minutes 60)))

(defn add-time [minutes delta-minutes]
 (clock 0 (+ minutes delta-minutes)))
