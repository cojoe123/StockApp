package joey.com.stockapp.joey.com.stockapp.api;

import java.util.List;

import joey.com.stockapp.joey.com.stockapp.model.CompanyChart;
import joey.com.stockapp.joey.com.stockapp.model.CompanyInfo;
import joey.com.stockapp.joey.com.stockapp.model.CompanyStock;
import joey.com.stockapp.joey.com.stockapp.model.Stocks;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IEXStockAPI {

    // call individual company info used for user search option
    @GET("stock/{ticker}/book")
    Call<CompanyStock> getCompany(@Path("ticker") String company);

    // call other company info
    @GET("stock/{ticker}/company")
    Call<CompanyInfo> getExtraCompanyInfo(@Path("ticker") String company);

    // call prices of a company to form a chart
    @GET("stock/{ticker}/chart/1d")
    Call<List<CompanyChart>> getCompanyChart(@Path("ticker") String company);

    // call the top 10 most active of the day
    @GET("stock/market/list/mostactive")
    Call<List<Stocks>> getMostActive();

    // call the top 10 gainers of the day
    @GET("stock/market/list/gainers")
    Call<List<Stocks>> getGainers();

    // call the top 10 losers of the day
    @GET("stock/market/list/losers")
    Call<List<Stocks>> getLosers();

    // call crypto info
    @GET("stock/market/crypto")
    Call<List<Stocks>> getCryptoMarket();

}
