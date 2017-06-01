
package at.spengergasse.kai17521.sem04.project.model.wunderground.forecast;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TxtForecast {

    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("forecastday")
    @Expose
    private List<TxtForecastDay> txtForecastDay = null;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<TxtForecastDay> getTxtForecastDay() {
        return txtForecastDay;
    }

    public void setTxtForecastDay(List<TxtForecastDay> txtForecastDay) {
        this.txtForecastDay = txtForecastDay;
    }

}
