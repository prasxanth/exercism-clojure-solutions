(ns strain)

(defn retain [pred coll]
  (for [i coll :when (pred i)] i))

(defn discard [pred coll]
  (retain (complement pred) coll))
