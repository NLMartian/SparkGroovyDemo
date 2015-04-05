package me.nlmartian.java.sparkdemo

import spark.Request
import spark.Response

/**
 * Created by nlmartian on 4/4/15.
 */
class WebServer extends SparkGroovy {
    public static void main(String ... args) {
        new WebServer().init()
    }

    def jsonClosure = { Request req, Response res ->
        if (req.contentType() == 'application/json') {
            json([name: req.attribute("name")])
        }
    }

    def xmlClosure = { Request req, Response res ->
        if (req.contentType() == 'application/xml') {
            "<name>${req.attribute("name")}</name>"
        }
    }

    public void init() {

        spark.Spark.staticFileLocation '/public'


        before { req, res ->
            // check
            def authenticated = true;
            if (!authenticated) {
                spark.halt 401, 'You are not welcome!'
            }
        }

        get '/greet/:name', { req, res ->
            return "Hello ${req.params 'name'}"
        }

        get '/foo.json', { req, res ->
            json foo: 'bar'
        }

        get '/jade', { req, res ->
            jade 'index.jade', foo: 'bar', pageName: 'my name'
        }

        get '/format', { req, res ->
            req.attribute "name", "This is a cool name"
        }, jsonClosure, xmlClosure
    }

}
