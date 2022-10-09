(ns binary-search)

(defn middle [coll]
  (quot (count coll) 2))

;; Source: Rosetta code - https://rosettacode.org/wiki/Binary_search#Clojure
(defn search-for
  ([target coll] (search-for target coll 0 (dec (count coll))))
  ([target coll lower upper]
   (if (> lower upper)
    (throw (Exception. "not found"))
    (let [m (quot (+ lower upper) 2) mth (nth coll m)]
      (cond
        (> mth target) (recur target coll lower (dec upper))
        (< mth target) (recur target coll (inc lower) upper)
        :else m)))))
