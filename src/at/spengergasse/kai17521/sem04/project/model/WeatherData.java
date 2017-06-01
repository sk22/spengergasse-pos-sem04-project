package at.spengergasse.kai17521.sem04.project.model;

import at.spengergasse.kai17521.sem04.project.AppController;
import at.spengergasse.kai17521.sem04.project.WeatherViewController;
import at.spengergasse.kai17521.sem04.project.model.wunderground.API;
import at.spengergasse.kai17521.sem04.project.model.wunderground.conditions.ConditionsResponse;
import at.spengergasse.kai17521.sem04.project.model.wunderground.forecast.ForecastResponse;
import com.google.gson.Gson;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.concurrent.Task;
import javafx.event.EventHandler;

import java.io.*;
import java.util.function.Consumer;

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
    load(null);
  }

  public void load(Consumer<WeatherData> consumer) throws IOException {
    final Task<WeatherData> task = new Task<WeatherData>() {
      @Override
      protected WeatherData call() throws IOException {
        final Gson gson = new Gson();
        conditions.set(gson.fromJson(
          new InputStreamReader(api.conditions(place)),
          ConditionsResponse.class
        ));
        forecast.set(gson.fromJson(
          new InputStreamReader(api.forecast(place)),
          ForecastResponse.class
        ));
        return WeatherData.this;
      }
    };
    if (consumer != null) {
      task.setOnSucceeded(event -> consumer.accept(task.getValue()));
    }
    new Thread(task).start();
  }

  public ConditionsResponse getConditions() {
    return conditions.get();
  }

  public ForecastResponse getForecast() {
    return forecast.get();
  }

  public ObjectProperty<ForecastResponse> forecastProperty() {
    return forecast;
  }

  public ObjectProperty<ConditionsResponse> conditionsProperty() {
    return conditions;
  }
}
