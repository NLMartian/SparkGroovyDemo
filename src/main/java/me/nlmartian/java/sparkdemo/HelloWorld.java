package me.nlmartian.java.sparkdemo;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.nlmartian.java.sparkdemo.model.Greet;
import me.nlmartian.java.sparkdemo.model.LoginPayload;

import static spark.Spark.*;

/**
 * Created by nlmartian on 4/3/15.
 */
public class HelloWorld {
  public static final int HTTP_BAD_REQUEST = 400;


  public static void main(String[] args) {

    /*staticFileLocation("/public");

    get("/hw",
        "application/json",
        (req, res) -> {
          return new Greet(req.queryParams("name"));
        },
        new JsonTransformer()
    );

    post("/login",
        (req, res) -> {
          try {
            ObjectMapper mapper = new ObjectMapper();
            LoginPayload loginInfo = mapper.readValue(req.body(), LoginPayload.class);
            if (!loginInfo.isValid()) {
              res.status(HTTP_BAD_REQUEST);
              return "";
            }

            String email = loginInfo.getEmail();
            res.status(200);
            return String.format("Welcome %s!", email);
          } catch (JsonParseException e) {
            res.status(HTTP_BAD_REQUEST);
            return "";
          }
        });
*/
  }
}
