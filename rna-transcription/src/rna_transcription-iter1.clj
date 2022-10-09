(ns rna-transcription
  (:require [clojure.string :as str]
            [clojure.set :as cset]))

(def nucleotides #{\A \C \G \T})

(defn to-rna [dna]
  {:pre [(cset/subset? (set dna) nucleotides)]}
  (str/replace dna #"G|C|T|A" {"G" "C" "C" "G" "A" "U" "T" "A"}))
