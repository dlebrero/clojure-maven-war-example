(ns simple.dev.start-jetty-server
  (:require simple.core
            [lein-reload.util.tracker :as tracker]
            [clojure.java.io :as io]
            [robert.hooke :as hooke]
            cemerick.pomegranate.aether)
  (:use ring.adapter.jetty
        ring.middleware.reload-modified
        ring.middleware.stacktrace
        clojure.test
        [cemerick.pomegranate :only (add-dependencies)]))

(defn add-dep [dep]
  (add-dependencies :coordinates dep
    :repositories (merge cemerick.pomegranate.aether/maven-central {"clojars" "http://clojars.org/repo"})))

(def log-file (.getAbsolutePath (io/file (io/resource "src/main/resources/log4j-config.xml"))))

(org.springframework.util.Log4jConfigurer/initLogging log-file 20000)

(defonce server (run-jetty (wrap-stacktrace (wrap-reload-modified #'simple.core/app ["src/main/clojure"])) {:port 8080 :join? false}))

(defn stop []
  (.stop server))

(defn start []
  (.start server))

(defonce changed-ns (tracker/tracker [(io/file "src/test/clojure") (io/file "src/main/clojure")] (System/currentTimeMillis)))

(defn reload-changed-files []
  (doseq [ns (changed-ns)] (do (println "reloading" ns) (require ns :reload ))))

(defn reload-tests [f & args]
  (reload-changed-files)
  (apply f args))

(hooke/add-hook #'clojure.test/run-tests #'reload-tests)
(hooke/add-hook #'clojure.test/run-all-tests #'reload-tests)