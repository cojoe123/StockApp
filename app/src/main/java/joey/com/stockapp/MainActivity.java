package joey.com.stockapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IEXStockAPI service = RetrofitClient.getRetrofitInstance().create(IEXStockAPI.class);

        mSearchView = findViewById(R.id.ticker_search);


        Button button = findViewById(R.id.crypto_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CryptoActivity.class);
                startActivity(intent);
            }
        });

        mRecyclerView = findViewById(R.id.stock_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        mostActiveUI(service);

        mGainersRecyclerView = findViewById(R.id.gainer_recyclerview);
        mGainersRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        gainersUI(service);

        mLosersRecyclerView = findViewById(R.id.losers_recyclerview);
        mLosersRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        losersUI(service);

    }

    protected void mostActiveUI(IEXStockAPI service) {
        Call<List<Stocks>> call = service.getMostActive();

        call.enqueue(new Callback<List<Stocks>>() {
            @Override
            public void onResponse(@NonNull Call<List<Stocks>> call, @NonNull Response<List<Stocks>> response) {
                List<Stocks> data = response.body();

                StockAdapter adapter = new StockAdapter(data);
                mRecyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(@NonNull Call<List<Stocks>> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, "ERROR Displaying Stocks Most Active", Toast.LENGTH_SHORT).show();
            }
        });
    }


    protected void gainersUI(IEXStockAPI service) {
        Call<List<Stocks>> call = service.getGainers();

        call.enqueue(new Callback<List<Stocks>>() {
            @Override
            public void onResponse(@NonNull Call<List<Stocks>> call, @NonNull Response<List<Stocks>> response) {
                List<Stocks> data = response.body();

                StockAdapter adapter = new StockAdapter(data);
                mGainersRecyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(@NonNull Call<List<Stocks>> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, "ERROR Displaying Stocks Gainers", Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void losersUI(IEXStockAPI service) {
        Call<List<Stocks>> call = service.getLosers();

        call.enqueue(new Callback<List<Stocks>>() {
            @Override
            public void onResponse(@NonNull Call<List<Stocks>> call, @NonNull Response<List<Stocks>> response) {
                List<Stocks> data = response.body();

                StockAdapter adapter = new StockAdapter(data);
                mLosersRecyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(@NonNull Call<List<Stocks>> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, "ERROR Displaying Stocks Losers", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
