(ns armstrong-numbers)

(defn armstrong? [n]
  (let [s (str n)
        e (count s)]
    (== n (reduce #(+ %1 (-> %2 (Character/digit 10) bigdec (.pow e))) 0N s))))

