package joey.com.stockapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.List;

import joey.com.stockapp.joey.com.stockapp.api.IEXStockAPI;
import joey.com.stockapp.joey.com.stockapp.api.RetrofitClient;
import joey.com.stockapp.joey.com.stockapp.model.CompanyChart;
import joey.com.stockapp.joey.com.stockapp.model.CompanyInfo;
import joey.com.stockapp.joey.com.stockapp.model.CompanyStock;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsActivity extends AppCompatActivity {

    private TextView mStockTextView;
    private TextView mSectorTextView;
    private TextView mDescTextView;
    private TextView mopenTextView;
    private TextView mcloseTextView;
    private TextView m52HighTextView;
    private TextView m52LowTextView;
    private LineGraphSeries<DataPoint> series;
    private GraphView mGraphView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        IEXStockAPI service = RetrofitClient.getRetrofitInstance().create(IEXStockAPI.class);

        Intent intent = getIntent();
        String symbol = intent.getStringExtra("ticker");

        mStockTextView = findViewById(R.id.stock_title);
        mStockTextView.setText("Details for " + symbol);

        mSectorTextView = findViewById(R.id.sector_view);
        mDescTextView = findViewById(R.id.desc_view);

        mopenTextView = findViewById(R.id.open_view);
        mcloseTextView = findViewById(R.id.close_view);
        m52HighTextView = findViewById(R.id.year_high_view);
        m52LowTextView = findViewById(R.id.year_low_view);

        mGraphView = findViewById(R.id.stock_graph);

//        stockChartUI(service, symbol);
        stockInfoUI(service, symbol);
        extraStockInfoUI(service, symbol);

    }

    protected void stockChartUI(IEXStockAPI service, String symbol) {
        Call<List<CompanyChart>> call = service.getCompanyChart(symbol);

        call.enqueue(new Callback<List<CompanyChart>>() {
            @Override
            public void onResponse(Call<List<CompanyChart>> call, Response<List<CompanyChart>> response) {
                List<CompanyChart> data = response.body();

                List<Double> xVals = new ArrayList<>();
                List<Float> yVals = new ArrayList<>();

                if (data != null) {
                    for (CompanyChart cc : data) {
                        xVals.add(cc.getAverage());
                        yVals.add(cc.getChangeOvertime());
                    }
                }

            }

            @Override
            public void onFailure(Call<List<CompanyChart>> call, Throwable t) {
                Toast.makeText(DetailsActivity.this, "ERROR Displaying Stocks Chart", Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void stockInfoUI(IEXStockAPI service, String symbol) {
        Call<CompanyStock> call = service.getCompany(symbol);

        call.enqueue(new Callback<CompanyStock>() {
            @Override
            public void onResponse(Call<CompanyStock> call, Response<CompanyStock> response) {
                CompanyStock cs = response.body();

                assert cs != null;
                CompanyStock.Quote q = cs.getQuotes();

                Double open = 0.0, close = 0.0, week52High = 0.0, week52Low = 0.0;
                open = q.getOpen();
                close = q.getClose();
                week52High = q.getWeek52High();
                week52Low = q.getWeek52Low();

                mopenTextView.setText(String.format("Opening Price: %.2f", open));
                mcloseTextView.setText(String.format("Closinging Price: %.2f", close));
                m52HighTextView.setText(String.format("Year High: %.2f", week52High));
                m52LowTextView.setText(String.format("Year Low: %.2f", week52Low));
            }

            @Override
            public void onFailure(Call<CompanyStock> call, Throwable t) {
                Log.e("ERROR", t.toString());
                Toast.makeText(DetailsActivity.this, "ERROR Displaying Stocks Information", Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void extraStockInfoUI(IEXStockAPI service, String symbol) {
        Call<CompanyInfo> call = service.getExtraCompanyInfo(symbol);

        call.enqueue(new Callback<CompanyInfo>() {
            @Override
            public void onResponse(Call<CompanyInfo> call, Response<CompanyInfo> response) {
                CompanyInfo ci = response.body();

                assert ci != null;
                mSectorTextView.setText(String.format("Industry: %s", ci.getIndustry()));
                mDescTextView.setText(String.format("Details: %s", ci.getDescription()));
            }

            @Override
            public void onFailure(Call<CompanyInfo> call, Throwable t) {
                Toast.makeText(DetailsActivity.this, "ERROR Displaying Stocks Extra Information", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
