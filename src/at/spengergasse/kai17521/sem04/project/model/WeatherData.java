package at.spengergasse.kai17521.sem04.project.model;

import at.spengergasse.kai17521.sem04.project.model.wunderground.API;
import at.spengergasse.kai17521.sem04.project.model.wunderground.conditions.ConditionsResponse;
import at.spengergasse.kai17521.sem04.project.model.wunderground.forecast.ForecastResponse;
import com.google.gson.Gson;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.*;

/**
 * @author Samuel Kaiser
 * @since 5/16/2017
 */
public class WeatherData {
  private String place;
  private API api;

  private ObjectProperty<ConditionsResponse> conditions =
    new SimpleObjectProperty<>();
  private ObjectProperty<ForecastResponse> forecast =
    new SimpleObjectProperty<>();
  private StringProperty city = new SimpleStringProperty();

  public WeatherData(String place, API api) {
    this.place = place;
    this.api = api;
  }

  public void load() throws IOException {
    Gson gson = new Gson();
    conditions.set(gson.fromJson(
      new InputStreamReader(api.conditions(place)),
      ConditionsResponse.class
    ));
    forecast.set(gson.fromJson(
      new InputStreamReader(api.forecast(place)),
      ForecastResponse.class
    ));
  }

  public ConditionsResponse getConditions() {
    return conditions.get();
  }

  public ForecastResponse getForecast() {
    return forecast.get();
  }

  public ObjectProperty<ConditionsResponse> conditionsProperty() {
    return conditions;
  }
}
