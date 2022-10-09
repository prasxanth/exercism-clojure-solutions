(ns largest-series-product)

(defn largest-product [n digits]
  (let [ds (->> digits char-array seq (map #(Character/digit % 10)) (map bigint))]
    (cond
      (and (pos? n) (empty? digits)) (throw (Exception. "Number string cannot be empty"))
      (empty? digits) 1
      (neg? n) (throw (Exception. "Partition cannot be negative"))
      (some neg? ds) (throw (Exception. "Number string should only contain digits"))
      :else (->> ds (partition n 1) (map #(reduce * %)) (apply max)))))
;;
