(defproject meep "0.1.0-SNAPSHOT"
  :description "A Clojure Twitter GUI Client"
  :url "https://github.com/oubiwann/meep"
  :license {:name "Apache License, Version 2.0"
            :url "https://opensource.org/licenses/Apache-2.0"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/tools.logging "0.3.1"]
                 [twitter-api "0.7.8"]
                 [seesaw "1.4.5"]
                 [ch.qos.logback/logback-classic "1.1.3"]
                 [me.raynes/fs "1.4.6"]]
  :main meep.core)
