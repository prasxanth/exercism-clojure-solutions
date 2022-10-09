(ns accumulate)

(defn accumulate [f coll]
  (loop [[x & xs] coll res []]
    (if (nil? x)
      res
      (recur xs (conj res (f x))))))
