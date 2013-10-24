(ns noises.core)

(use 'overtone.live)
(use 'overtone.inst.drum)
(use 'overtone.inst.sampled-piano)
(demo (sin-osc))

(demo 7 (lpf (mix (saw [50 (line 100 1600 5) 101 100.5]))
             (lin-lin (lf-tri (line 2 20 5)) -1 1 400 4000)))


(sampled-piano)
(sampled-piano 65)
(sampled-piano 67)
(sampled-piano 64)
(kick)

(defn my-chord [root]
  (doseq [i (range 0 12 3)]
    (sampled-piano (+ root i))))

(my-chord 63)
(my-chord 66)
(my-chord 69)

(my-chord (note :c4))
(sampled-piano (note :d4))

(def kick-s (sample (freesound-path 777)))
(def click-s (sample (freesound-path 406)))
(def boom-s (sample (freesound-path 33637)))
(def subby-s (sample (freesound-path 25649)))
