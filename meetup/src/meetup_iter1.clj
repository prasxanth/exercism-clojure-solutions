(ns meetup
  [:require [clj-time.core :as tm]])

(def days-of-the-week {1 :monday 2 :tuesday 3 :wednesday 4 :thursday
                       5 :friday 6 :saturday 7 :sunday})

(defn day-of-week [month year day]
  (-> (tm/date-time month year day) tm/day-of-week days-of-the-week))

(defn get-days-by-name [year month day-name]
  (->> (tm/number-of-days-in-the-month year month)
       inc
       (range 1)
       (keep #(when (= day-name (day-of-week year month %)) %))))

(def day-ranks {:first 0 :second 1 :third 2 :fourth 3})

(defn meetup [month year day-name time-of-month]
  (let [days-by-name (get-days-by-name year month day-name)
        teens (set (range 13 20))]
     (->> (case time-of-month
            :teenth (->> days-by-name (keep teens) first)
            :last (last days-by-name)
            (nth days-by-name (time-of-month day-ranks)))
          (conj [year month]))))


