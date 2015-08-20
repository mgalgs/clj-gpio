(ns gpio.clojure.files
  (:require [clojure.core.async :refer [go]]
            [gpio.impl.protocols :as impl])
  (:import [java.io RandomAccessFile FileOutputStream PrintStream]
           [io.bicycle.epoll EventPolling EventPoller PollEvent]))

(def ^:private POLLING_CONFIG
  (bit-or EventPolling/EPOLLIN EventPolling/EPOLLET EventPolling/EPOLLPRI))

(defrecord Watcher [poller context timeout]
  impl/FileWatcher
  (impl/start! [_ on-change-fn]
    (go (loop []
          (when-let [events (.poll poller timeout)]
            (doseq [_ (filter #(=  context (.getData %)) events)]
              (on-change-fn))
            (recur)))))

  (impl/stop! [_]
    (.close poller)))

(defn create-watcher [filename file context timeout]
  (let [poller (EventPolling/create)]
    (.addFile poller file POLLING_CONFIG context)
    (Watcher. poller context timeout)))

(defn random-access [filename]
  (RandomAccessFile. filename "rw"))

(defn raf-read [raf loc]
  (.seek raf 0)
  (.read raf))

(defn raf-write [raf loc b]
  (.seek raf 0)
  (.writeByte raf b))

(defn raf-close! [raf]
  (.close raf))
