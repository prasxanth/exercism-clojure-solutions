(ns series)

(defn slices [string length]
  (->> (partition length 1 string) (map #(apply str %)) distinct))
