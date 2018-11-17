package joey.com.stockapp;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.List;

import joey.com.stockapp.joey.com.stockapp.api.IEXStockAPI;
import joey.com.stockapp.joey.com.stockapp.api.RetrofitClient;
import joey.com.stockapp.joey.com.stockapp.model.Stocks;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView mGainersRecyclerView;
    private RecyclerView mLosersRecyclerView;
    private SearchView mSearchView;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IEXStockAPI service = RetrofitClient.getRetrofitInstance().create(IEXStockAPI.class);

        mSearchView = (SearchView) findViewById(R.id.ticker_search);

        mButton = (Button) findViewById(R.id.crypto_button);

        mRecyclerView = (RecyclerView) findViewById(R.id.stock_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        mostActiveUI(service);

        mGainersRecyclerView = (RecyclerView) findViewById(R.id.gainer_recyclerview);
        mGainersRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        gainersUI(service);

        mLosersRecyclerView = (RecyclerView) findViewById(R.id.losers_recyclerview);
        mLosersRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        losersUI(service);

    }

    protected void mostActiveUI(IEXStockAPI service) {
        Call<List<Stocks>> call = service.getMostActive();

        call.enqueue(new Callback<List<Stocks>>() {
            @Override
            public void onResponse(Call<List<Stocks>> call, Response<List<Stocks>> response) {
                List<Stocks> data = response.body();

                StockAdapter adapter = new StockAdapter(data);
                mRecyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Stocks>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "ERROR Displaying Stocks Most Active", Toast.LENGTH_SHORT).show();
            }
        });
    }


    protected void gainersUI(IEXStockAPI service) {
        Call<List<Stocks>> call = service.getGainers();

        call.enqueue(new Callback<List<Stocks>>() {
            @Override
            public void onResponse(Call<List<Stocks>> call, Response<List<Stocks>> response) {
                List<Stocks> data = response.body();

                StockAdapter adapter = new StockAdapter(data);
                mGainersRecyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Stocks>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "ERROR Displaying Stocks Gainers", Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void losersUI(IEXStockAPI service) {
        Call<List<Stocks>> call = service.getLosers();

        call.enqueue(new Callback<List<Stocks>>() {
            @Override
            public void onResponse(Call<List<Stocks>> call, Response<List<Stocks>> response) {
                List<Stocks> data = response.body();

                StockAdapter adapter = new StockAdapter(data);
                mLosersRecyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Stocks>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "ERROR Displaying Stocks Losers", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
