package joey.com.stockapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
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

    private TextView mSectorTextView;
    private TextView mDescTextView;
    private TextView mopenTextView;
    private TextView mcloseTextView;
    private TextView m52HighTextView;
    private TextView m52LowTextView;
//    private LineGraphSeries<DataPoint> series;
//    private GraphView mGraphView;
//    private LineChart mLineChart;
    private BarChart mBarChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        IEXStockAPI service = RetrofitClient.getRetrofitInstance().create(IEXStockAPI.class);

        Intent intent = getIntent();
        String symbol = intent.getStringExtra("ticker");

        TextView stockTextView = findViewById(R.id.stock_title);
        stockTextView.setText(String.format("Details for %s", symbol));

        mSectorTextView = findViewById(R.id.sector_view);
        mDescTextView = findViewById(R.id.desc_view);

        mopenTextView = findViewById(R.id.open_view);
        mcloseTextView = findViewById(R.id.close_view);
        m52HighTextView = findViewById(R.id.year_high_view);
        m52LowTextView = findViewById(R.id.year_low_view);

        mBarChart = findViewById(R.id.stock_graph);
//        mBarChart.getDescription().setEnabled(true);

        stockChartUI(service, symbol);
        stockInfoUI(service, symbol);
        extraStockInfoUI(service, symbol);

    }

    protected void stockChartUI(IEXStockAPI service, String symbol) {
        Call<List<CompanyChart>> call = service.getCompanyChart(symbol);

        call.enqueue(new Callback<List<CompanyChart>>() {
            @Override
            public void onResponse(@NonNull Call<List<CompanyChart>> call, @NonNull Response<List<CompanyChart>> response) {
                List<CompanyChart> data = response.body();

                assert data != null;
                List<BarEntry> entries = new ArrayList<>();
                for(int i = 0; i < data.size(); i++) {
                    float value = data.get(i).getClose();
                    entries.add(new BarEntry(i, value));
                }


                List<String> labels = new ArrayList<>();
                for(CompanyChart cc : data) {
                    labels.add(cc.getDate());
                }

                BarDataSet set = new BarDataSet(entries, "Daily Closing Stock Prices");

                BarData barData = new BarData(set);
                barData.setBarWidth(0.9f);

                Description msg = new Description();
                msg.setText("");

                mBarChart.setData(barData);
                mBarChart.setDescription(msg);
                mBarChart.getAxisLeft().setDrawLabels(false);
                mBarChart.getAxisRight().setDrawLabels(false);
                mBarChart.getXAxis().setDrawLabels(false);
                mBarChart.setFitBars(true);
                mBarChart.invalidate();
                mBarChart.animateY(500);

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
            @SuppressLint("DefaultLocale")
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
                mcloseTextView.setText(String.format("Closing Price: %.2f", close));
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
