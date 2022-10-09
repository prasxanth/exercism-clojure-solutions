(ns meetup
  [:import [java.util Calendar]])

(def days-of-the-week {:sunday 1   :monday 2 :tuesday  3 :wednesday 4
                       :thursday 5 :friday 6 :saturday 7})

(defn date-time [year month day]
  (doto (Calendar/getInstance)
        (.set Calendar/YEAR year)
        (.set Calendar/MONTH (dec month))
        (.set Calendar/DATE day)))

(defn day-of-week [year month day]
  (-> (date-time year month day) (.get Calendar/DAY_OF_WEEK)))

(defn number-of-days-in-month [month]
  (let [cal (Calendar/getInstance)]
    (.set cal Calendar/MONTH (dec month))
    (.getActualMaximum cal Calendar/DATE)))

(defn dates-on-day [year month day-name]
  (-> (day-name days-of-the-week)
      (- (day-of-week year month 1))
      inc
      (#(if (pos? %) % (+ 7 %)))
      (range (inc (number-of-days-in-month month)) 7)))

(def day-ranks {:first 0 :second 1 :third 2 :fourth 3})

(defn meetup [month year day-name time-of-month]
  (let [dates (dates-on-day year month day-name)
        teens (set (range 13 20))]
    (->> (case time-of-month
           :teenth (->> dates (keep teens) first)
           :last (last dates)
           (nth dates (time-of-month day-ranks)))
         (conj [year month]))))
