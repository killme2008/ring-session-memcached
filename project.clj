(defproject ring-session-memcached "0.0.1-SNAPSHOT"
  :description "Ring sessions stored in memcached"
  :dependencies [[ring/ring-core "1.0.1"]
                 [clj-xmemcached "0.1.1"]]
  :dev-dependencies [
					 [log4j/log4j "1.2.16"]
					 [org.slf4j/slf4j-log4j12 "1.5.6"]
					 [lein-clojars "0.6.0"]])


