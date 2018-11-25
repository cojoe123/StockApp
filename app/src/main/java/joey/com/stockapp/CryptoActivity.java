package joey.com.stockapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

import joey.com.stockapp.joey.com.stockapp.api.IEXStockAPI;
import joey.com.stockapp.joey.com.stockapp.api.RetrofitClient;
import joey.com.stockapp.joey.com.stockapp.model.Stocks;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CryptoActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crypto);

        IEXStockAPI service = RetrofitClient.getRetrofitInstance().create(IEXStockAPI.class);

        mRecyclerView = findViewById(R.id.crypto_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(CryptoActivity.this));

        cryptoUI(service);
    }

    protected void cryptoUI(IEXStockAPI service) {
        Call<List<Stocks>> call = service.getCryptoMarket();

        call.enqueue(new Callback<List<Stocks>>() {
            @Override
            public void onResponse(@NonNull Call<List<Stocks>> call, @NonNull Response<List<Stocks>> response) {
                List<Stocks> cryptoData = response.body();

                CryptoAdapter adapter = new CryptoAdapter(cryptoData);
                mRecyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(@NonNull Call<List<Stocks>> call, @NonNull Throwable t) {
                Toast.makeText(CryptoActivity.this, "ERROR Displaying Stocks Most Active", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
