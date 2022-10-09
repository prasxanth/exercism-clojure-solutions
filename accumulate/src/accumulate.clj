(ns accumulate)

(defn accumulate [func coll]
  (loop [coll (reverse coll) res '[]]
    (if (empty? coll)
      res
      (recur (rest coll) (cons (-> coll first func) res)))))
