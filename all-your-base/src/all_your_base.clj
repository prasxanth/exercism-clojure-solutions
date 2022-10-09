(ns all-your-base)

(defn- to-base-10 [from-base digits]
  (reduce #(+ (* from-base %1) %2) 0 digits))

(defn- from-base-10
 "Converts decimal number to collection of digits in base"
 [to-base number]
 (if (< number to-base)
  [number]
  (conj (from-base-10 to-base (quot number to-base)) (rem number to-base))))

(defn convert
 "Convert a number, represented as a sequence of digits in one base, to any other base."
 [old-base digits new-base]
  (cond 
    (<= old-base 1) nil
    (<= new-base 1) nil
    (empty? digits) nil
    (->> digits (apply (some-fn neg? (partial = old-base)))) nil 
    :else (->> digits
              (to-base-10 old-base)
              (from-base-10 new-base))))
