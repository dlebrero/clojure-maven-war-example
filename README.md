# Clojure-Maven-WAR-Example

Example of a clojure maven project that generates a war using the [maven-clojure-plugin](https://github.com/talios/clojure-maven-plugin)

It includes:

* A repl with autoreload for development, both when running tests with clojure.test/run-test and before serving any request.
* [Midje](https://github.com/marick/Midje/) for testing.
* web.xml configuration using [ring-java-servlet](https://github.com/laurentpetit/ring-java-servlet) to avoid aot compilation.
* Start up and shutdown functions to be executed when webapp start and stops.
* [pomegranate](https://github.com/cemerick/pomegranate) to add new libraries without restarting the repl.
* Log4j configuration, including automatic refresh when the log4j config file changes.


## Basic Usage

Run `mvn package` to generate the war.

Run `mvn clojure:repl` to start a repl with a Jetty server with autorefresh. The application will be available at http://localhost:8080/war-example/.

Run unit tests with clojure.test (run-all-tests) or (run-test).

Add dependencies with `(add-dep '[[incanter "1.2.3"]])`

Reload all modified namespaces with `(reload-changed-files)`