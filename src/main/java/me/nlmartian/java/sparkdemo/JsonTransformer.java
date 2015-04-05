package me.nlmartian.java.sparkdemo;

import com.google.gson.Gson;
import spark.ResponseTransformer;

/**
 * Created by nlmartian on 4/4/15.
 */
public class JsonTransformer implements ResponseTransformer {

  private Gson gson = new Gson();

  @Override
  public String render(Object model) throws Exception {

    return gson.toJson(model);
  }
}
