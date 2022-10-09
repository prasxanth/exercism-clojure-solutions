(ns allergies)

(def items [:eggs :peanuts :shellfish :strawberries :tomatoes :chocolate :pollen :cats])

(defn ignore-score [score]
  (if (> score 255) (mod score 256) score))

(defn score->binary [score]
  (->> (Integer/toString (ignore-score score) 2) (map #(Character/digit % 2)) reverse))

(defn allergies [score]
  "Returns collection of allergies based on score"
 (mapcat repeat (score->binary score) items))

(defn allergic-to? [score item] 
  (some #{item} (allergies score)))


