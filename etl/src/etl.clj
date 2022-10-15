(ns etl
  (:require [clojure.string :as str]))

(defn transform [scores]
  (reduce (fn [m [k vs]]
              (into m (map #(assoc m (str/lower-case %) k) vs)))
          {} scores))

