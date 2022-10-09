(ns binary)

(defn binary->decimal [digits]
  (reduce #(+ (* 2 %1) %2) 0 digits))

(defn to-decimal [numstr]
  (let [bs (map #(Character/digit % 10) (seq numstr))]
    (if (some neg? bs) 0 (binary->decimal bs))))
