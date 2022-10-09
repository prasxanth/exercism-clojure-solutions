(ns space-age)

(def planet-years {"mercury" 0.2408467 "venus" 0.61519726 "earth" 1.0 "mars" 1.8808158 "jupiter" 11.862615
                   "saturn" 29.447498  "uranus" 84.016846 "neptune" 164.79132})

(def ^:const earth-year-in-seconds 31557600)

(defmacro on-planets [years arg]
  (let [planetarum# (eval years)]
   `(do ~@(for [p#  (keys planetarum#)]
            `(defn ~(symbol (apply str "on-" p#)) [~arg]
               (->> ~p# ~planetarum# (* earth-year-in-seconds) (/ ~arg)))))))

#_(macroexpand '(on-planets planet-years age))
#_(on-earth 2134835688)

(on-planets planet-years age)
