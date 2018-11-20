package joey.com.stockapp.joey.com.stockapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CompanyStock {

    @SerializedName("quote")
    @Expose
    private Quote quotes;

    public Quote getQuotes() {
        return quotes;
    }


    public class Quote {
        @SerializedName("open")
        @Expose
        private Double open;

        @SerializedName("close")
        @Expose
        private Double close;

        @SerializedName("week52High")
        @Expose
        private Double week52High;

        @SerializedName("week52Low")
        @Expose
        private Double week52Low;

        public Double getOpen() {
            return open;
        }

        public Double getClose() {
            return close;
        }

        public Double getWeek52High() {
            return week52High;
        }

        public Double getWeek52Low() {
            return week52Low;
        }
    }
}
