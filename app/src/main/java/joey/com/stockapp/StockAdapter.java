package joey.com.stockapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import joey.com.stockapp.joey.com.stockapp.model.Stocks;

public class StockAdapter extends RecyclerView.Adapter<StockAdapter.StockHolder> {

    private List<Stocks> values;

    public StockAdapter(List<Stocks> values) {
        this.values = values;
    }

    @Override
    public StockHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View stockView = inflater.inflate(R.layout.list_item_stock, parent, false);

        StockHolder stockHolder = new StockHolder(stockView);

        return stockHolder;
    }

    @Override
    public void onBindViewHolder(StockAdapter.StockHolder stockHolder, int position) {
        Stocks stock = values.get(position);

        TextView titleTextView = stockHolder.stockTextView;
        titleTextView.setText(stock.getSymbol());

        TextView priceTitleTextView = stockHolder.priceTextView;
        priceTitleTextView.setText("Latest Price: " + String.format("%.2f", stock.getLatestPrice()));
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    public class StockHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView stockTextView;
        public TextView priceTextView;

        public StockHolder(View itemView) {
            super(itemView);

            stockTextView = (TextView)itemView.findViewById(R.id.list_item_stock_text);
            priceTextView = (TextView)itemView.findViewById(R.id.list_item_price_text);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }

}
