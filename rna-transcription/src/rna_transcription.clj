(ns rna-transcription)

(def dna->rna {\G \C \C \G \A \U \T \A})

(defn dna? [dna]
  (every? dna->rna dna))

(defn translate [dna]
  (->> dna (map dna->rna) (apply str)))

(defn to-rna [dna]
    (if (dna? dna) (translate dna) (throw (AssertionError. "Invalid Input"))))
