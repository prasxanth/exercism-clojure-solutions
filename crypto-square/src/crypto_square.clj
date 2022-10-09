(ns crypto-square
  (:require [clojure.string :as str]))

(defn normalize-plaintext [text]
   (-> text str/lower-case (str/replace #"[^[a-z\d]]+" "")))

(defn square-size [text]
  (->> text count Math/sqrt Math/ceil int))

(defn plaintext-segments-with-space [text]
  (let [normtext (normalize-plaintext text)
        size (square-size normtext)]
    (->> normtext
         (partition size size (repeat \space))
         (mapv #(apply str %)))))

(defn plaintext-segments [text]
  (->> text plaintext-segments-with-space (mapv str/trim)))

(defn ciphertext-segments [text]
  (->> text plaintext-segments-with-space (apply map str)))

(defn ciphertext [text]
  (->> text ciphertext-segments (map str/trim) str/join))

(defn normalize-ciphertext [text]
  (->> text ciphertext-segments (str/join " ")))


