(ns all-your-base)

(defn to-base-10 [from-base digits]
  (reduce #(+ (* from-base %1) %2) 0 digits))

(defn from-base-10 [to-base number]
  (loop [n number digits []]
    (if (< n to-base)
      (rseq (conj digits n))
      (recur (quot n to-base) (conj digits (rem n to-base))))))

(defn convert [old-base digits new-base]
  (when-not (or (<= old-base 1)
                (<= new-base 1)
                (empty? digits)
                (some neg? digits)
                (some #(<= old-base %) digits))
    (->> digits (to-base-10 old-base) (from-base-10 new-base))))

