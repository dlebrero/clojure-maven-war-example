# Clojure-Maven-WAR-Example

Example of a clojure maven project that generates a war using the [maven-clojure-plugin](https://github.com/talios/clojure-maven-plugin)

It includes:

* A repl with autoreload for development
* [Midje](https://github.com/marick/Midje/) for testing
* web.xml configuration using [ring-java-servlet](https://github.com/laurentpetit/ring-java-servlet) to avoid aot compilation

## Basic Usage

Run `mvn package` to generate the war

Run `mvn clojure:repl` to start a repl with a Jetty server with autorefresh