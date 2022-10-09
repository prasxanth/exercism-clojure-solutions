(ns acronym
  (:require [clojure.string :as str]))

(defn acronym [long-name]
  (->> (str/split long-name #" |(?<=[a-z])(?=[A-Z])|-")
       (map (comp first str/upper-case))
       (str/join)))
