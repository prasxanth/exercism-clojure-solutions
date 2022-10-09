(ns rotational-cipher)

(defn alphabets [start]
  (->> (range start (+ start 26)) (map char)))

;; source: https://groups.google.com/g/clojure/c/SjmevTjZPcQ
(defn ↻ [n s]
   (let [r (mod n (count s))]
      (concat (drop r s) (take r s))))

(defn make-cipher [rot seqs]
  (->> (mapcat #(zipmap % (↻ rot %)) seqs) (into {})))

(defn rotate [msg rot]
  (let [cipher (->> (map alphabets [65 97]) (make-cipher rot))]
    (->> msg (map #(cipher % %)) (apply str))))
