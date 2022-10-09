(ns run-length-encoding)

(defn run-length-encode
  "encodes a string with run-length-encoding"
  [plain-text]
  (->> (partition-by identity plain-text)
       (mapcat (juxt count first))
       (remove #(= 1 %))
       (apply str)))

(defn run-length-decode
  "decodes a run-length-encoded string"
  [cipher-text]
  (->> (re-seq #"(\d+)?([^\d])" cipher-text)
       (mapcat (fn [[_ n ch]] (if (nil? n) ch (repeat (Integer/parseInt n) ch))))
       (apply str)))
