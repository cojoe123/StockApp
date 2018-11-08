package joey.com.stockapp;

import joey.com.stockapp.joey.com.stockapp.model.CompanyChart;
import joey.com.stockapp.joey.com.stockapp.model.CompanyInfo;
import joey.com.stockapp.joey.com.stockapp.model.CompanyStock;
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
    Call<CompanyChart> getCompanyChart(@Path("ticker") String company);

}
