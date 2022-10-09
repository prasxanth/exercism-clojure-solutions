(ns nucleotide-count)

(def nucleotides #{\A \C \G \T})
(def init-counts #(zipmap % (repeat 0)))

(defn count-of-nucleotide-in-strand [nucleotide strand]
  {:pre [(nucleotides nucleotide)]}
  (-> strand frequencies (get nucleotide 0)))

(defn nucleotide-counts [strand] 
   (->> strand frequencies (merge (init-counts nucleotides))))

