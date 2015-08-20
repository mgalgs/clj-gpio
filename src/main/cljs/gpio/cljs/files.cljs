(ns gpio.cljs.files                         
    (:require  [cljs.nodejs :as nodejs]        
               [gpio.impl.protocols :as impl]))

(def ^:private fs (nodejs/require "fs"))

(def ^:private BUFFER_SIZE 64)

(defn spit [filename content]
  (.writeFileSync fs filename content))

(defrecord Watcher [filename]
  impl/FileWatcher
  (impl/start! [this on-change-fn]
    (let [js-watcher (.watch fs filename on-change-fn)]
      (aset this "__watcher_impl" js-watcher)
      true))

  (impl/stop! [this]
    (.close (aget this "__watcher_impl"))))

(defn create-watcher [filename file context timeout]
  (Watcher. filename))

(defn random-access [filename]
  (.openSync fs path "r+"))

(defn raf-read [raf loc]
  (let [buffer (js/Buffer. BUFFER_SIZE)]
    (.readSync fs raf buffer 0 BUFFER_SIZE loc)
    (.toString buffer)))

(defn raf-write [raf loc b]
  (let [buffer (js/Buffer. #js [b])]
    (.writeSync fs raf buffer loc)))

(defn raf-close! [raf]
  (.closeSync fs raf))
