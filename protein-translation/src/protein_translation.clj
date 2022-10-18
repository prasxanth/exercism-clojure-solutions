(ns protein-translation
  (:require [clojure.string :as str]))

(def translate-codon {"AUG" "Methionine"
                      "UUU" "Phenylalanine"
                      "UUC" "Phenylalanine"
                      "UUA" "Leucine"
                      "UUG" "Leucine"
                      "UCU" "Serine"
                      "UCC" "Serine"
                      "UCA" "Serine"
                      "UCG" "Serine"
                      "UAU" "Tyrosine"
                      "UAC" "Tyrosine"
                      "UGU" "Cysteine"
                      "UGC" "Cysteine"
                      "UGG" "Tryptophan"
                      "UAA" "STOP"
                      "UAG" "STOP"
                      "UGA" "STOP"})

(defn translate-rna [rna]
  (->> rna
       (partition 3)
       (map (comp translate-codon str/join))
       (take-while #(not= "STOP" %))))
