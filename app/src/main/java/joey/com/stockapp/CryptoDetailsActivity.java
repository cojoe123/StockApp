package joey.com.stockapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class CryptoDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crypto_details);
//        IEXStockAPI service = RetrofitClient.getRetrofitInstance().create(IEXStockAPI.class);

        Intent intent = getIntent();
        String name = intent.getStringExtra("company");
        double delta = intent.getDoubleExtra("delta", 0);
        double latestPrice = intent.getDoubleExtra("latest", 0);

        TextView stockTextView = findViewById(R.id.stock_title);
        stockTextView.setText(String.format("Details for %s", name));

        TextView mopenTextView = findViewById(R.id.crypto_open_view);
        mopenTextView.setText(String.format("Latest Price: %s", latestPrice));

        TextView priceChangeView = findViewById(R.id.change_price_view);
        priceChangeView.setText(String.format("Change: %s", delta));


    }
}
