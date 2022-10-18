(ns space-age)

(def planet-years {"mercury" 0.2408467 "venus" 0.61519726  "earth" 1.0
                   "mars" 1.8808158    "jupiter" 11.862615 "saturn" 29.447498
                   "uranus" 84.016846  "neptune" 164.79132})

(def ^:const earth-year-in-seconds 31557600)

(defmacro on-planets [years arg]
  (let [planet-years# (eval years)]
    `(do ~@(for [p# (keys planet-years#)]
             `(defn ~(symbol (apply str "on-" p#)) [~arg]
                (->> ~p# ~planet-years# (* earth-year-in-seconds) (/ ~arg)))))))

(on-planets planet-years age)
