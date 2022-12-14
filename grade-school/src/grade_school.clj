(ns grade-school)

(defn grade [school grade]
  (school grade []))

(defn add [school name grade]
  (merge-with into school {grade [name]}))

(defn sorted [school]
  (->> school
       (mapcat (fn [[k v]] [k (sort v)]))
       (apply sorted-map)))
