(ns meetup
  [:import [java.time LocalDate DayOfWeek]])

(def days-of-the-week {:monday   1 :tuesday  2 :wednesday 3 :thursday 4
                       :friday   5 :saturday 6 :sunday    7})

(defn date-time [year month day]
  (. LocalDate of year month day))

(defn day-of-week [year month day]
  (.. DayOfWeek (from (date-time year month day)) (getValue)))

(defn number-of-days-in-month [year month]
  (.. LocalDate (of year month 1) (lengthOfMonth)))

(defn dates-on-day [year month day-name]
  (-> (day-name days-of-the-week)
      (- (day-of-week year month 1))
      inc
      (#(if (pos? %) % (+ 7 %)))
      (range (inc (number-of-days-in-month year month)) 7)))

(def day-ranks {:first 0 :second 1 :third 2 :fourth 3})

(defn meetup [month year day-name time-of-month]
  (let [dates (dates-on-day year month day-name)
        teens (set (range 13 20))]
    (->> (case time-of-month
           :teenth (->> dates (keep teens) first)
           :last (last dates)
           (nth dates (time-of-month day-ranks)))
         (conj [year month]))))
