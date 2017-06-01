package at.spengergasse.kai17521.sem04.project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author Samuel Kaiser
 * @since 5/15/2017
 */
public class Main extends Application {
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource(
      "/at/spengergasse/kai17521/sem04/project/App.fxml"
    ));
    BorderPane root = loader.load();
    loader.<AppController>getController().setWindow(primaryStage);
    Scene scene = new Scene(root, 1000, 800);
    primaryStage.setScene(scene);
    primaryStage.getIcons().addAll(
      new Image(getClass().getResourceAsStream("/sun.ico")),
      new Image(getClass().getResourceAsStream("/sun.png"))
    );
    primaryStage.setTitle("Weather");
    primaryStage.show();
  }
}
