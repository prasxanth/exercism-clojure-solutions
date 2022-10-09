(ns roman-numerals)

(def romans {1 "I" 2 "II" 3 "III" 4 "IV" 5 "V" 6 "VI" 7 "VII" 8 "VIII" 9 "IX" 10 "X"
             40 "XL" 50 "L" 90 "XC" 100 "C" 400 "CD" 500 "D" 900 "CM" 1000 "M"})
(def base (-> romans keys sort))

(defn min-diff-from-base [n]
  (when (pos? n)
    (->> base (map (partial - n)) (filter #(>= % 0)) (apply min))))

(defn ->roman-base [n]
  (->> (iterate min-diff-from-base n) (take-while (comp true? boolean))))

(defn numerals [n]
  (->> (->roman-base n)
       (#(map - % (rest %)))
       (map #(get romans %))
       (apply str)))
