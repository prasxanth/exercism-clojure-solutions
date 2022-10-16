(ns largest-series-product)

(defn str->digits [digits-str]
  (->> digits-str (map #(Character/digit % 10)) (map bigint)))

(defn largest-product [span digits-str]
  (let [ds (str->digits digits-str)]
    (cond
      (zero? span) 1
      (empty? digits-str) (throw (Exception. "Number string cannot be empty"))
      (neg? span) (throw (Exception. "Span cannot be negative"))
      (some neg? ds) (throw (Exception. "Number string should only contain digits"))
      :else (->> ds (partition span 1) (map #(reduce * %)) (apply max)))))

