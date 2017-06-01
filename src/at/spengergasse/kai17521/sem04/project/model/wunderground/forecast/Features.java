
package at.spengergasse.kai17521.sem04.project.model.wunderground.forecast;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Features {

    @SerializedName("forecast")
    @Expose
    private int forecast;

    @SerializedName("forecast10day")
    @Expose
    private int forecast10day;

    public int getForecast10Day() {
        return forecast10day;
    }

    public void setForecast10Day(int forecast10day) {
        this.forecast10day = forecast10day;
    }

}
