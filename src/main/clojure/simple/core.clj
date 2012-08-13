(ns simple.core
  (:use compojure.core)
  (:require [compojure.route :as route]
            [compojure.handler :as handler]))

(def app (handler/api
           (defroutes example
             (context "/clojure-servlet" []
               (GET "/" [] "<h1>Hello World Wide Web!</h1>")
               (GET "/t/:id" [id]
                 (str "<h1>Path with param: '" id "'</h1>"))
               (route/not-found "Page not found")))))
