package joey.com.stockapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import joey.com.stockapp.joey.com.stockapp.api.IEXStockAPI;
import joey.com.stockapp.joey.com.stockapp.api.RetrofitClient;

public class CryptoDetailsActivity extends AppCompatActivity {


    private TextView mStockTextView;
    private TextView mopenTextView;
    private TextView mPriceChangeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crypto_details);
//        IEXStockAPI service = RetrofitClient.getRetrofitInstance().create(IEXStockAPI.class);

        Intent intent = getIntent();
        String name = intent.getStringExtra("company");
        double delta = intent.getDoubleExtra("delta", 0);
        double latestPrice = intent.getDoubleExtra("latest", 0);

        mStockTextView = findViewById(R.id.stock_title);
        mStockTextView.setText(String.format("Details for %s", name));

        mopenTextView = findViewById(R.id.crypto_open_view);
        mopenTextView.setText(String.format("Latest Price: %s", latestPrice));

        mPriceChangeView = findViewById(R.id.change_price_view);
        mPriceChangeView.setText(String.format("Change: %s", delta));


    }
}
