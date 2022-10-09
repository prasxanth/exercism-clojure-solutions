(ns kindergarten-garden
  (:require [clojure.string :as str]))

(def plants {\R :radishes \V :violets \G :grass \C :clover})

(def default-students ["Alice" "Bob" "Charlie" "David" "Eve" "Fred" "Ginny"
                       "Harriet" "Ileana" "Joseph" "Kincaid" "Larry"])

(def keywordize (comp keyword str/lower-case))

(defn assign-plants [studentoj plantoj]
  (->> plantoj (map plants) (partition 2) (map vec) (zipmap studentoj)))

(defn garden
  ([gardeno] (garden gardeno default-students))
  ([gardeno studentoj]
   (let [lernantoj (->> studentoj sort (map keywordize))]
        (->> gardeno
             str/split-lines
             (map #(assign-plants lernantoj %))
             (apply merge-with into)))))
