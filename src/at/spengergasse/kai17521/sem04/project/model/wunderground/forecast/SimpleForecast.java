
package at.spengergasse.kai17521.sem04.project.model.wunderground.forecast;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SimpleForecast {

    @SerializedName("forecastday")
    @Expose
    private List<SimpleForecastDay> forecastDay = null;

    public List<SimpleForecastDay> getForecastDay() {
        return forecastDay;
    }

    public void setForecastday(List<SimpleForecastDay> forecastDay) {
        this.forecastDay = forecastDay;
    }

}
