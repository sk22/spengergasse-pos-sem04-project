package at.spengergasse.kai17521.sem04.project.model.wunderground;

import java.io.*;
import java.net.URL;

/**
 * @author Samuel Kaiser
 * @since 5/17/2017
 */
public class API {
  private String baseUrl;

  public API(String baseUrl) {
    this.baseUrl = baseUrl;
  }

  public InputStream conditions(String query) throws IOException {
    String url = "/conditions/q/" + query.replaceAll("\\s", "_") + ".json";
    return fetch(url);
  }

  public InputStream fetch(String param) throws IOException {
    System.out.println("Fetching " + param);
    return new URL(baseUrl + param).openStream();
  }
}
