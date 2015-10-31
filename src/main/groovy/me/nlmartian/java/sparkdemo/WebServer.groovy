package me.nlmartian.java.sparkdemo

import org.apache.commons.validator.routines.UrlValidator
import redis.clients.util.Hashing
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

        websocket '/echo', EchoWsHandler.class

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

        get '/link/:id', { Request req, Response res ->
            def id = req.params 'id'
            def url = RedisClient.getString(id)
            if (url == null) {
                res.status 404
                return ''
            } else {
                res.redirect(url)
            }
        }

        post '/link', { Request req, Response res ->
            def url = req.queryParams('url');
            def urlValidator = new UrlValidator(["http", "https"] as String[])
            if (urlValidator.isValid(url)) {
                def id = Math.abs(Hashing.MURMUR_HASH.hash(url)).toString()
                RedisClient.putString(id, url)
                return "http://localhost:4567/link/${id}"
            } else {
                res.status(400)
                return ''
            }
        }
// ttt

    }

}
