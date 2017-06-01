package at.spengergasse.kai17521.sem04.project;

import at.spengergasse.kai17521.sem04.project.model.Configuration;
import at.spengergasse.kai17521.sem04.project.model.WeatherData;
import at.spengergasse.kai17521.sem04.project.model.wunderground.API;
import com.google.gson.Gson;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Window;

import java.io.*;
import java.util.ArrayList;

import static javafx.collections.FXCollections.observableArrayList;
import static javafx.collections.FXCollections.observableHashMap;
import static javafx.scene.control.Alert.AlertType.CONFIRMATION;
import static javafx.scene.control.Alert.AlertType.ERROR;
import static javafx.scene.control.ButtonType.CANCEL;
import static javafx.scene.control.ButtonType.OK;

/**
 * @author Samuel Kaiser
 * @since 5/15/2017
 */
public class AppController {
  private static final ExtensionFilter JSON_EXTENSION_FILTER =
    new ExtensionFilter("JSON", "*.json");
  @FXML private ListView<String> placesListView;
  @FXML private ObservableList<String> places = observableArrayList();
  @FXML private ObservableMap<String, WeatherData> weather = observableHashMap();
  @FXML private BorderPane root;

  private API api;
  private FXMLLoader loader = new FXMLLoader(getClass().getResource(
    "/at/spengergasse/kai17521/sem04/project/WeatherView.fxml"
  ));
  private File file = new File("config.json");
  private Window window;

  private StringProperty selected = new SimpleStringProperty();

  public AppController() {
    this.api = new API("http://api.wunderground.com/api/272d686d04ba90f0");
    places.addListener((ListChangeListener<String>) change -> {
      change.next();
      handleSave();
      if (change.wasAdded() || change.wasReplaced()) {
        System.out.println("Added " + change.getAddedSubList());
        change.getAddedSubList()
          .forEach(place -> {
            final WeatherData newWeatherData = new WeatherData(place, api);
            try {
              weather.put(place, newWeatherData);
              newWeatherData.load(this::refreshSelected);
            } catch (IOException ioe) {
              final Alert error = new Alert(ERROR, ioe.toString());
              error.setHeaderText("Weather data could not be fetched");
              error.show();
            }
          });
      } else if (change.wasRemoved()) {
        System.out.println("Removed " + change.getRemoved());
        change.getRemoved().forEach(weather::remove);
      }
    });
  }

  private void refreshSelected(WeatherData weatherData) {
    if (selected.get() != null
      && selected.get().equals(weatherData.getPlace())) {
      applyToWeatherView(weatherData);
    }
  }

  private void doLoad(File file) throws IOException {
    final FileReader reader = new FileReader(file);
    final Configuration config =
      new Gson().fromJson(reader, Configuration.class);

    selected.set(config.getSelected());
    places.setAll(config.getPlaces());
  }

  @FXML
  private void initialize() {
    try {
      root.setCenter(loader.load());
    } catch (IOException e) {
      e.printStackTrace();
    }

    try {
      doLoad(file);
    } catch (IOException e) {
      e.printStackTrace();
    }

    placesListView.getSelectionModel().selectedItemProperty()
      .addListener((obs, prev, next) -> handleListChange(next));
  }

  public ObservableList<String> getPlaces() {
    return places;
  }

  void setWindow(Window window) {
    this.window = window;
  }

  @FXML
  private void handleImport() {
    final FileChooser fileChooser = new FileChooser();
    fileChooser.getExtensionFilters().add(JSON_EXTENSION_FILTER);
    File file = fileChooser.showOpenDialog(window);
    if (file == null) return;
    try {
      doLoad(file);
    } catch (IOException e) {
      final Alert alert = new Alert(ERROR, e.toString());
      alert.setHeaderText("Failed to load config");
      alert.show();
    }
  }

  @FXML
  private void handleSave() {
    doSave(file, true);
  }

  private void doSave(File file, boolean includeSelected) {
    final Configuration config = new Configuration(
      includeSelected ? selected.get() : null,
      new ArrayList<>(places)
    );

    try {
      final Writer writer = new FileWriter(file);
      new Gson().toJson(config, writer);
      writer.close();
    } catch (IOException e) {
      final Alert alert = new Alert(ERROR, e.toString());
      alert.setHeaderText("Could not save configuration");
    }
  }

  @FXML
  private void handleExport() {
    final FileChooser fileChooser = new FileChooser();
    fileChooser.getExtensionFilters().add(JSON_EXTENSION_FILTER);
    final File file = fileChooser.showSaveDialog(window);
    if (file == null) return;
    doSave(file, false);
  }

  @FXML
  private void handleExit() {
    System.exit(0);
  }

  @FXML
  private void handleAddPlace() {
    Dialog<String> dialog = new Dialog<>();
    dialog.setTitle("Add place");
    dialog.setHeaderText("Add a new place");

    GridPane grid = new GridPane();
    grid.setHgap(10);
    grid.setVgap(5);
    TextField state = new TextField();
    TextField city = new TextField();
    grid.addRow(0, new Label("State"), state);
    grid.addRow(1, new Label("City"), city);

    dialog.getDialogPane().setContent(grid);
    dialog.getDialogPane().getButtonTypes().addAll(OK, CANCEL);

    Platform.runLater(state::requestFocus);

    dialog.setResultConverter(button -> {
      if (button == OK) return state.getText() + "/" + city.getText();
      return null;
    });

    dialog.showAndWait().ifPresent(places::add);
  }

  @FXML
  private void handleRemovePlace() {
    final Alert alert = new Alert(CONFIRMATION);
    alert.setHeaderText("Are you sure?");
    alert.setContentText("This will remove the selected place from your list.");
    alert.showAndWait().filter(OK::equals).ifPresent(type -> {
      places.remove(placesListView.getSelectionModel().getSelectedItem());
    });
  }

  @FXML
  private void handleListChange(String next) {
    handleSave();
    selected.set(next);
    applyToWeatherView(weather.get(next));
  }

  public void handleRefresh() {
    weather.values().forEach(data -> {
      try {
        data.load(this::refreshSelected);
      } catch (IOException e) {
        final Alert alert = new Alert(ERROR, e.toString());
        alert.setHeaderText("Failed to refresh");
      }
    });
  }

  private void applyToWeatherView(WeatherData data) {
    loader.<WeatherViewController>getController()
      .applyData(data);
  }
}
