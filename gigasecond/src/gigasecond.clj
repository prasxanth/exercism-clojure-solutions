(ns gigasecond)

(def ^:const giga 1000000000)

(defn from [year month day]
  (-> (java.time.LocalDate/of year month day)
      (.atStartOfDay)
      (.plusSeconds giga)
      bean
      ((juxt :year :monthValue :dayOfMonth))))
