(defproject clj-gpio "0.2.0-SNAPSHOT"
    :description "A lightweight Clojure library for Raspberry PI GPIO"
    :url "http://peterschwarz.github.io/clj-gpio"
    :license  {:name "Eclipse Public License"
               :url "http://www.eclipse.org/legal/epl-v10.html"}
    :min-lein-version  "2.0.0"
    :source-paths      ["src/main/cljc"]
    :test-paths        ["src/test/cljc"]
    :java-source-paths ["src/main/java"]

    :dependencies [[org.clojure/clojure "1.7.0"]
                   [org.clojure/clojurescript "1.7.48"]
                   [org.clojure/core.async "0.1.346.0-17112a-alpha"] 
                   [net.java.dev.jna/jna "4.1.0"]]

    :aliases {"cljs:test" ["trampoline" "run" "-m" "clojure.main/main" "build-tests.clj"]})

