(ns flatten-array)

;; Source: Modified http://rosettacode.org/wiki/Flatten_a_list#Clojure to exclude nil(s)
(defn flatten [coll]
 (lazy-seq
   (let [s (seq coll)]
     (cond
       (nil? s) nil
       (nil? (first s)) (flatten (rest s))
       (coll? (first s)) (concat (flatten (first s)) (flatten (rest s)))
       :else (cons (first s) (flatten (rest s)))))))

