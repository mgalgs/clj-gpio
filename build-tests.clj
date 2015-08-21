(require 'cljs.build.api)

(let [src [ "src/main/cljc"
           "src/test/cljc"]]
  (cljs.build.api/build
    (apply cljs.build.api/inputs src)
    {:output-dir "target/test-js"
     :output-to "target/run-tests.js"
     :target :nodejs
     :optimizations :none
     :verbose true }))
