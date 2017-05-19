package at.spengergasse.kai17521.sem04.project.model;

import at.spengergasse.kai17521.sem04.project.model.wunderground.API;
import at.spengergasse.kai17521.sem04.project.model.wunderground.conditions.Conditions;
import com.google.gson.Gson;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

import java.io.*;

/**
 * @author Samuel Kaiser
 * @since 5/16/2017
 */
public class WeatherData {
  private String place;
  private API api;

  private ObjectProperty<Conditions> conditions = new SimpleObjectProperty<>();
  private StringProperty city = new SimpleStringProperty();

  public WeatherData(String place, API api) {
    this.place = place;
    this.api = api;
  }

  public void load() throws IOException {
    applyConditions(fetchData());
  }

  private Conditions fetchData() throws IOException {
    return new Gson().fromJson(
      new InputStreamReader(api.conditions(place)),
      Conditions.class
    );
  }

  private void applyConditions(Conditions conditions) {
    this.conditions.set(conditions);
    city.set(conditions.getCurrentObservation().getDisplayLocation().getCity());
  }

  public Conditions getConditions() {
    return conditions.get();
  }

  public ObjectProperty<Conditions> conditionsProperty() {
    return conditions;
  }
}
