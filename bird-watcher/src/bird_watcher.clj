(ns bird-watcher)

(def last-week 
  [0 2 5 3 7 8 4])

(defn today [birds]
  (last birds))

(defn inc-bird [birds]
  (->> birds 
    last 
    inc 
    (conj (pop birds))))

(defn day-without-birds? [birds]
   (->> birds
     (some zero?) 
     boolean))

(defn n-days-count [birds n]
  (->> birds
    (take n)
    (reduce +)))

(defn busy-days [birds]
  (->> birds
    (filter #(>= % 5)) 
    count))

(defn odd-week? [birds]
  (= birds [1 0 1 0 1 0 1]))
