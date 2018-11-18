package joey.com.stockapp.joey.com.stockapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Stocks {

    @SerializedName("symbol")
    @Expose
    private String symbol;

    @SerializedName("latestPrice")
    @Expose
    private double latestPrice;

    @SerializedName("change")
    private Double priceChange;

    public Stocks() {}

    public Stocks(String symbol, double latestPrice) {
        this.symbol = symbol;
        this.latestPrice = latestPrice;
    }

    public Double getPriceChange() {
        return priceChange;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getLatestPrice() {
        return latestPrice;
    }

}
