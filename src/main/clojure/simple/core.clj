(ns simple.core
  (:use compojure.core
        [clojure.tools.logging :only [info]])
  (:require [compojure.route :as route]
            [compojure.handler :as handler]
            [org.lpetit.ring.servlet.util :as sc-util]))

(defn start [ctx]
  (info "Starting webapp with" (sc-util/context-params ctx)))

(defn stop [ctx]
  (info "Stopping webapp with" (sc-util/context-params ctx)))

(def app (handler/api
           (defroutes example
             (context "/war-example" []
               (GET "/" [] "<h1>Hello World Wide Web!</h1>")
               (GET "/t/:id" [id]
                 (str "<h1>Path with param: '" id "'</h1>"))
               (route/not-found "Page not found")))))
