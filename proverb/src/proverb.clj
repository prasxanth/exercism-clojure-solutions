(ns proverb
  (:require [clojure.string :as str]))

(def say-line #(format "For want of a %s the %s was lost." %1 %2))
(def say-last-line #(format "And all for the want of a %s." %))

(defn recite [inputs]
    (if (empty? inputs)
        ""
        (-> (mapv say-line inputs (rest inputs))
            (conj (say-last-line (first inputs)))
            (#(str/join "\n" %)))))


