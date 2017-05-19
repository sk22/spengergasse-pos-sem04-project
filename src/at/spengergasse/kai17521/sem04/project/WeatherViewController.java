package at.spengergasse.kai17521.sem04.project;

import at.spengergasse.kai17521.sem04.project.model.WeatherData;
import at.spengergasse.kai17521.sem04.project.model.wunderground.conditions.CurrentObservation;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

/**
 * @author Samuel Kaiser
 * @since 5/18/2017
 */
public class WeatherViewController extends Parent {
  @FXML private Label temparature;
  @FXML private Text city;

  public void initialize() {
    emptyFields();
  }

  void applyData(WeatherData data) {
    if (data == null) {
      emptyFields();
      city.setText("Failed to fetch");
      return;
    }

    final CurrentObservation observation =
      data.getConditions().getCurrentObservation();

    city.setText(observation.getDisplayLocation().getCity());
    temparature.setText(String.format("%s °C", observation.getTempC()));
  }

  private void emptyFields() {
    city.setText("None");
    temparature.setText("N/A");
  }
}
