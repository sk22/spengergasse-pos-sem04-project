package at.spengergasse.kai17521.sem04.project;

import at.spengergasse.kai17521.sem04.project.model.WeatherData;
import at.spengergasse.kai17521.sem04.project.model.wunderground.conditions.CurrentObservation;
import at.spengergasse.kai17521.sem04.project.model.wunderground.forecast.Forecast;
import at.spengergasse.kai17521.sem04.project.model.wunderground.forecast.SimpleForecastDay;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * @author Samuel Kaiser
 * @since 5/18/2017
 */
public class WeatherViewController extends Parent {
  @FXML private Text title;
  @FXML private Label temperature;
  @FXML private Label humidity;
  @FXML private Label wind;
  @FXML private AnchorPane chartPane;

  @FXML private GridPane forecastGrid;

  public void initialize() {
    emptyFields();
  }

  void applyData(WeatherData data) {
    if (
      data == null ||
      data.getConditions() == null ||
      data.getForecast() == null
    ) {
      emptyFields();
      return;
    }

    final CurrentObservation observation =
      data.getConditions().getCurrentObservation();
    final Forecast forecast =
      data.getForecast().getForecast();

    if (observation != null) applyObservation(observation);
    if (forecast != null) applyForecast(forecast);
  }

  private void applyForecast(Forecast forecast) {
    forecastGrid.getChildren().clear();
    forecastGrid.add(new Label("Max"), 0, 2);
    forecastGrid.add(new Label("Min"), 0, 3);
    forecast.getSimpleForecast().getForecastDay()
      .forEach(this::applyForecastDay);
    plotGraph(forecast);
  }

  private void plotGraph(Forecast forecast) {
    final CategoryAxis days = new CategoryAxis();
    final NumberAxis values = new NumberAxis();
    final LineChart<String, Number> chart = new LineChart<>(days, values);
    final XYChart.Series<String, Number> high = new XYChart.Series<>();
    final XYChart.Series<String, Number> low = new XYChart.Series<>();

    forecast.getSimpleForecast().getForecastDay().forEach(day -> {
      high.getData().add(new XYChart.Data<String, Number>(
        day.getDate().getWeekdayShort() + " " + day.getDate().getDay(),
        Double.valueOf(day.getHigh().getCelsius())
      ));
      low.getData().add(new XYChart.Data<String, Number>(
        day.getDate().getWeekdayShort() + " " + day.getDate().getDay(),
        Double.valueOf(day.getLow().getCelsius())
      ));
    });

    days.setTickMarkVisible(false);
    values.setForceZeroInRange(false);
    chart.getData().add(high);
    chart.getData().add(low);
    chart.setLegendVisible(false);
    chartPane.getChildren().clear();
    chartPane.getChildren().add(chart);
  }

  private void applyForecastDay(SimpleForecastDay simpleForecastDay) {
    final int col = simpleForecastDay.getPeriod() + 1;
    forecastGrid.add(new ImageView(
      new Image("/" + simpleForecastDay.getIcon() + ".gif")
    ), col, 0);
    forecastGrid.add(
      new Label(simpleForecastDay.getDate().getWeekday()), col, 1
    );
    forecastGrid.add(
      new Label(simpleForecastDay.getHigh().getCelsius() + " °C"),
      col, 2
    );
    forecastGrid.add(
      new Label(simpleForecastDay.getLow().getCelsius() + " °C"),
      col, 3
    );
  }

  private void applyObservation(CurrentObservation observation) {
    title.setText(observation.getDisplayLocation().getCity());
    temperature.setText(String.format("%s °C", observation.getTempC()));
    humidity.setText(observation.getRelativeHumidity());
    wind.setText(String.format(
      "%s km/h, %s",
      observation.getWindKph(),
      observation.getWindDir()
    ));
  }

  private void emptyFields() {
    title.setText("None");
    temperature.setText("N/A");
    humidity.setText("N/A");
    wind.setText("N/A");
  }
}
