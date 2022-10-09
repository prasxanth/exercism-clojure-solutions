(ns secret-handshake)

(def handshake {0 "wink" 1 "double blink" 2 "close your eyes" 3 "jump"})

(defn commands [n]
  (->> (keys handshake)
       (filter (partial bit-test n))
       (map handshake)
       (#(if (bit-test n 4) (reverse %) %))))
