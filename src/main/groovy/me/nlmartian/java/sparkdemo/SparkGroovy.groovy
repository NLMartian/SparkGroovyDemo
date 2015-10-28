package me.nlmartian.java.sparkdemo

import de.neuland.jade4j.JadeConfiguration
import de.neuland.jade4j.template.FileTemplateLoader
import groovy.json.JsonBuilder
import org.slf4j.LoggerFactory
import spark.Filter
import spark.Request
import spark.Response
import spark.Route

class SparkGroovy {

    static logger = LoggerFactory.getLogger(SparkGroovy.class)
    static config = new JadeConfiguration()
    static {
        def loader = new FileTemplateLoader('src/main/reources/views/', 'UTF-8')
        config.setTemplateLoader loader
    }

    private Route createClosureBasedRouteForPath(String path, Closure ... closures) {
        new Route() {
            def handle(Request req, Response res) {
                logger.info "[${req.requestMethod()}] ${req.url()}"
                closures*.delegate = this
                return closures*.call(req, res).findAll { it }.join()
            }
        }
    }

    def before(final Closure closure) {
        spark.Spark.before(new Filter() {
            void handle(Request request, Response response) {
                logger.info "before ${request.url()}"
                closure.delegate = this
                closure request, response
            }
        })
    }

    def websocket(String path, Class<?> handler) {
        spark.Spark.webSocket(path, handler)
    }

    def json(Object obj) {
        return new JsonBuilder(obj)
    }

    def jade(String template, Object obj) {
        def jadeTemplate = config.getTemplate template
        return config.renderTemplate(jadeTemplate, obj)
    }

    def get(String path, Closure ... closures) {
        spark.Spark.get path, createClosureBasedRouteForPath(path, closures)
    }

    def post(String path, Closure... closures) {
        spark.Spark.post(path, createClosureBasedRouteForPath(path, closures))
    }

    def hello() {
        println "Hello"
    }


}