package joey.com.stockapp.joey.com.stockapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CompanyChart {

    @SerializedName("average")
    @Expose
    private Double average;

    @SerializedName("changeOverTime")
    @Expose
    private Float changeOverTime;

    public CompanyChart(Double average, Float changeOverTime) {
        this.average = average;
        this.changeOverTime = changeOverTime;
    }

    public Float getChangeOvertime() { return changeOverTime; }

    public Double getAverage() {
        return average;
    }

}
