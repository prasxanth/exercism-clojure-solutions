(ns octal)

(def oct->decimal (zipmap (seq "01234567") (range 8)))

(defn to-decimal [oct-str]
  (let [os (->> oct-str seq (map oct->decimal))]
    (if (some nil? os) 0 (reduce #(+ (* 8 %1) %2) 0 os))))
