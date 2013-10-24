(ns noises.buses
  (:use overtone.live))

(defonce tri-bus (audio-bus))
(defonce sin-bus (audio-bus))


(defsynth tri-synth [out-bus 0 freq 5]
  (out:kr out-bus (lf-tri:kr freq)))

(defsynth sin-synth [out-bus 0 freq 5]
  (out:kr out-bus (sin-osc:kr freq)))

;; groups

(defonce main-g (group "get-on-the-bus main"))
(defonce early-g (group "early birds" :head main-g))
(defonce later-g (group "latecomers" :after early-g))



;; synths

(comment
  (def tri-synth-inst (tri-synth :tgt early-g tri-bus))
  (def sin-synth-inst (sin-synth :tgt early-g sin-bus))

  ; (def tri-synth-inst (tri-synth [:tail early-g] tri-bus))
  ; (def sin-synth-inst (sin-synth [:tail early-g] sin-bus))

  ; (def tri-synth-inst (tri-synth :pos :tail :target tri-bus))
  ; (def sin-synth-inst (sin-synth :pos :tail :target sin-bus))
  )

(pp-node-tree)


(defsynth modulated-vol-tri [vol-bus 0 freq 220]
  (out 0 (pan2 (* (in:kr vol-bus) (lf-tri freq)))))

(defsynth modulated-freq-tri [freq-bus 0 mid-freq 200 freq-amp 55]
  (let [freq (+ mid-freq (* (in:kr freq-bus) freq-amp))]
    (out 0 (pan2 (lf-tri freq)))))

(comment

  (def mvt (modulated-vol-tri :tgt later-g sin-bus))
  (def mft (modulated-freq-tri :tgt later-g sin-bus))

  (ctl mft :freq-bus tri-bus)

  )

