(ns etl
  (:require [clojure.string :as str]))

;; bmaddy's solution

(defn transform [m]
  (into {}
        (for [[score letters] m
              letter letters]
          [(str/lower-case letter) score])))