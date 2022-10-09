(ns diamond)

(defn letters-upto [end-char]
  (map char (range (int \A) (inc (int end-char)))))

(defn reflect [xs]
  (->> xs reverse rest (concat xs)))

(defn make-row [before-spaces fill-char after-spaces]
  (->> (concat (repeat before-spaces \space) [fill-char] (repeat after-spaces \space))
       reflect
       (apply str)))

(defn diamond [end-char]
  (let [ls (letters-upto end-char)
        size (count ls)]
      (->> (map make-row (range (dec size) -1 -1) ls (range size)) reflect)))
