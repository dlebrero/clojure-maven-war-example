(ns simple.dev.start-jetty-server
  (:require simple.core)
  (:use ring.adapter.jetty
        ring.middleware.reload-modified
        ring.middleware.stacktrace))

(defonce server (run-jetty (wrap-stacktrace (wrap-reload-modified #'simple.core/app ["src/main/clojure"])) {:port 8080 :join? false}))

(defn stop []
  (.stop server))

(defn start []
  (.start server))