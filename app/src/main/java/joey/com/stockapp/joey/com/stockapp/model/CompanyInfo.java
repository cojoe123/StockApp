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

    @SerializedName("ceo")
    @Expose
    private String ceo;


    public CompanyInfo(String industry, String description, String ceo) {
        this.industry = industry;
        this.description = description;
        this.ceo = ceo;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCeo() {
        return ceo;
    }

    public void setCeo(String ceo) {
        this.ceo = ceo;
    }
}
