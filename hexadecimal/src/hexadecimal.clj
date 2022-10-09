(ns hexadecimal)

(def hex->int (zipmap '(\0 \1 \2 \3 \4 \5 \6 \7 \8 \9 \a \b \c \d \e \f) (range 16)))

(defn hex-to-int [hex-str]
  (let [hs (->> hex-str seq (map hex->int))]
    (if (some nil? hs) 0 (reduce #(+ (* 16 %1) %2) 0 hs))))
