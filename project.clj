(defproject clj-gpio "0.2.0-SNAPSHOT"
    :description "A lightweight Clojure library for Raspberry PI GPIO"
    :url "http://peterschwarz.github.io/clj-gpio"
    :license  {:name "Eclipse Public License"
               :url "http://www.eclipse.org/legal/epl-v10.html"}
    :min-lein-version  "2.0.0"
    :source-paths      ["src/main/cljc" "src/main/clj"]
    :test-paths        ["src/test/clj"]
    :java-source-paths ["src/main/java"]

    :plugins [[lein-cljsbuild "1.0.6"]]

    :dependencies [[org.clojure/clojure "1.7.0"]
                   [org.clojure/clojurescript "1.7.48"]
                   [org.clojure/core.async "0.1.346.0-17112a-alpha"] 
                   [net.java.dev.jna/jna "4.1.0"]]

    :cljsbuild {:builds [{:id "test"
                          :source-paths ["src/main/cljc"
                                         "src/main/cljs"
                                         "src/test/cljc"]
                          :compiler {:output-to   "target/testable.js"
                                     :output-dir  "target/test-js"
                                     :target :nodejs
                                     :optimizations :none
                                     :hashbang false}}]
                :test-commands {"unit-tests" ["node" :node-runner "target/testable.js"]}})

