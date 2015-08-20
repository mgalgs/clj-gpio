(ns gpio.impl.protocols)

(defprotocol FileWatcher
  (start! [this on-change-fn])
  (stop! [this]))
