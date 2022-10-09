(ns beer-song
  (:require [clojure.pprint :as pp]
            [clojure.string :as str]))

(def clfmt (partial pp/cl-format nil))

(defn verse [n]
  (cond-> ""
    (pos? n) (str (clfmt "~a bottle~:p of beer on the wall, ~:*~a bottle~:p of beer.\n" n))
    (> n 1) (str (clfmt "Take one down and pass it around, ~a bottle~:p of beer on the wall.\n" (dec n)))
    (= n 1) (str "Take it down and pass it around, no more bottles of beer on the wall.\n")
    (zero? n) (str "No more bottles of beer on the wall, no more bottles of beer.\n"
                   "Go to the store and buy some more, 99 bottles of beer on the wall.\n")))

(defn sing
  "Given a start and an optional end, returns all verses in this interval. If
  end is not given, the whole song from start is sung."
  ([start end] (->> (map verse (range start (dec end) -1)) (str/join "\n")))
  ([start] (sing start 0)))
