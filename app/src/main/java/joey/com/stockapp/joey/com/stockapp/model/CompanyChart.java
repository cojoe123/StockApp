package joey.com.stockapp.joey.com.stockapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CompanyChart {

    @SerializedName("close")
    @Expose
    private Float close;

    @SerializedName("changeOverTime")
    @Expose
    private Float changeOverTime;

    @SerializedName("label")
    @Expose
    private String date;

    public CompanyChart(Float average, Float changeOverTime) {
        this.close = average;
        this.changeOverTime = changeOverTime;
    }

    public Float getChangeOverTime() {
        return changeOverTime;
    }

    public String getDate() {
        return date;
    }

    public Float getClose() {
        return close;
    }

}
