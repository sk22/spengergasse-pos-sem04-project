package at.spengergasse.kai17521.sem04.project.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author Samuel Kaiser
 * @since 5/19/2017
 */
public class Configuration {
  @Expose @SerializedName("selected")
  private String selected;

  @Expose @SerializedName("places")
  private List<String> places;

  public Configuration(String selected, List<String> places) {
    this.selected = selected;
    this.places = places;
  }

  public String getSelected() {
    return selected;
  }

  public List<String> getPlaces() {
    return places;
  }
}
