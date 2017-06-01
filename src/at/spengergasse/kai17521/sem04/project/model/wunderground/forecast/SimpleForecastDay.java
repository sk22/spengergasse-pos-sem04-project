
package at.spengergasse.kai17521.sem04.project.model.wunderground.forecast;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SimpleForecastDay {

    @SerializedName("date")
    @Expose
    private Date date;
    @SerializedName("period")
    @Expose
    private int period;
    @SerializedName("high")
    @Expose
    private High high;
    @SerializedName("low")
    @Expose
    private Low low;
    @SerializedName("conditions")
    @Expose
    private String conditions;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("icon_url")
    @Expose
    private String iconUrl;
    @SerializedName("skyicon")
    @Expose
    private String skyicon;
    @SerializedName("pop")
    @Expose
    private int pop;
    @SerializedName("qpf_allday")
    @Expose
    private QpfAllday qpfAllday;
    @SerializedName("qpf_day")
    @Expose
    private QpfDay qpfDay;
    @SerializedName("qpf_night")
    @Expose
    private QpfNight qpfNight;
    @SerializedName("snow_allday")
    @Expose
    private SnowAllday snowAllday;
    @SerializedName("snow_day")
    @Expose
    private SnowDay snowDay;
    @SerializedName("snow_night")
    @Expose
    private SnowNight snowNight;
    @SerializedName("maxwind")
    @Expose
    private Maxwind maxwind;
    @SerializedName("avewind")
    @Expose
    private Avewind avewind;
    @SerializedName("avehumidity")
    @Expose
    private int avehumidity;
    @SerializedName("maxhumidity")
    @Expose
    private int maxhumidity;
    @SerializedName("minhumidity")
    @Expose
    private int minhumidity;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public High getHigh() {
        return high;
    }

    public void setHigh(High high) {
        this.high = high;
    }

    public Low getLow() {
        return low;
    }

    public void setLow(Low low) {
        this.low = low;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getSkyicon() {
        return skyicon;
    }

    public void setSkyicon(String skyicon) {
        this.skyicon = skyicon;
    }

    public int getPop() {
        return pop;
    }

    public void setPop(int pop) {
        this.pop = pop;
    }

    public QpfAllday getQpfAllday() {
        return qpfAllday;
    }

    public void setQpfAllday(QpfAllday qpfAllday) {
        this.qpfAllday = qpfAllday;
    }

    public QpfDay getQpfDay() {
        return qpfDay;
    }

    public void setQpfDay(QpfDay qpfDay) {
        this.qpfDay = qpfDay;
    }

    public QpfNight getQpfNight() {
        return qpfNight;
    }

    public void setQpfNight(QpfNight qpfNight) {
        this.qpfNight = qpfNight;
    }

    public SnowAllday getSnowAllday() {
        return snowAllday;
    }

    public void setSnowAllday(SnowAllday snowAllday) {
        this.snowAllday = snowAllday;
    }

    public SnowDay getSnowDay() {
        return snowDay;
    }

    public void setSnowDay(SnowDay snowDay) {
        this.snowDay = snowDay;
    }

    public SnowNight getSnowNight() {
        return snowNight;
    }

    public void setSnowNight(SnowNight snowNight) {
        this.snowNight = snowNight;
    }

    public Maxwind getMaxwind() {
        return maxwind;
    }

    public void setMaxwind(Maxwind maxwind) {
        this.maxwind = maxwind;
    }

    public Avewind getAvewind() {
        return avewind;
    }

    public void setAvewind(Avewind avewind) {
        this.avewind = avewind;
    }

    public int getAvehumidity() {
        return avehumidity;
    }

    public void setAvehumidity(int avehumidity) {
        this.avehumidity = avehumidity;
    }

    public int getMaxhumidity() {
        return maxhumidity;
    }

    public void setMaxhumidity(int maxhumidity) {
        this.maxhumidity = maxhumidity;
    }

    public int getMinhumidity() {
        return minhumidity;
    }

    public void setMinhumidity(int minhumidity) {
        this.minhumidity = minhumidity;
    }

}
