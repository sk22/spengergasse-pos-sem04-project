
package at.spengergasse.kai17521.sem04.project.model.wunderground.forecast;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Forecast {

    @SerializedName("txt_forecast")
    @Expose
    private TxtForecast txtForecast;
    @SerializedName("simpleforecast")
    @Expose
    private SimpleForecast simpleForecast;

    public TxtForecast getTxtForecast() {
        return txtForecast;
    }

    public void setTxtForecast(TxtForecast txtForecast) {
        this.txtForecast = txtForecast;
    }

    public SimpleForecast getSimpleForecast() {
        return simpleForecast;
    }

    public void setSimpleForecast(SimpleForecast simpleForecast) {
        this.simpleForecast = simpleForecast;
    }

}
