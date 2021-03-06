package joey.com.stockapp.joey.com.stockapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CompanyInfo {

    @SerializedName("industry")
    @Expose
    private String industry;

    @SerializedName("description")
    @Expose
    private String description;


    public CompanyInfo(String industry, String description) {
        this.industry = industry;
        this.description = description;
    }

    public String getIndustry() {
        return industry;
    }

    public String getDescription() {
        return description;
    }

}
