package joey.com.stockapp.joey.com.stockapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CompanyStock {

    @SerializedName("quote")
    @Expose
    private List<Quote> quotes = null;

    public CompanyStock(List<Quote> quotes) {
        this.quotes = quotes;
    }

    public List<Quote> getQuotes() {
        return quotes;
    }

    public void setQuotes(List<Quote> quotes) {
        this.quotes = quotes;
    }


    public class Quote {
        @SerializedName("companyName")
        @Expose
        private String companyName;

        @SerializedName("sector")
        @Expose
        private String sector;

        @SerializedName("open")
        @Expose
        private double open;

        @SerializedName("close")
        @Expose
        private double close;

        @SerializedName("week52High")
        @Expose
        private double week52High;

        @SerializedName("week52Low")
        @Expose
        private double week52Low;

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getSector() {
            return sector;
        }

        public void setSector(String sector) {
            this.sector = sector;
        }

        public double getOpen() {
            return open;
        }

        public void setOpen(double open) {
            this.open = open;
        }

        public double getClose() {
            return close;
        }

        public void setClose(double close) {
            this.close = close;
        }

        public double getWeek52High() {
            return week52High;
        }

        public void setWeek52High(double week52High) {
            this.week52High = week52High;
        }

        public double getWeek52Low() {
            return week52Low;
        }

        public void setWeek52Low(double week52Low) {
            this.week52Low = week52Low;
        }
    }

}
