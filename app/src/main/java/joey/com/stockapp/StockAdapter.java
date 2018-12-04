package joey.com.stockapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

    StockAdapter(List<Stocks> values) {
        this.values = values;
    }

    @NonNull
    @Override
    public StockHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View stockView = inflater.inflate(R.layout.list_item_stock, parent, false);

        return new StockHolder(stockView);
    }

    @Override
    public void onBindViewHolder(@NonNull StockAdapter.StockHolder stockHolder, int position) {
        Stocks stock = values.get(position);

        TextView titleTextView = stockHolder.stockTextView;
        titleTextView.setText(stock.getSymbol());

        TextView priceTitleTextView = stockHolder.priceTextView;
        priceTitleTextView.setText("Latest Price: " + String.format("%.2f", stock.getLatestPrice()));

        TextView changeView = stockHolder.changeTextView;
        changeView.setText(String.format("%.2f", stock.getPriceChange()));

    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    public class StockHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView stockTextView;
        TextView priceTextView;
        TextView changeTextView;

        StockHolder(View itemView) {
            super(itemView);

            stockTextView = itemView.findViewById(R.id.list_item_stock_text);
            priceTextView = itemView.findViewById(R.id.list_item_price_text);
            changeTextView = itemView.findViewById(R.id.list_item_price_change_text);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Stocks stock = values.get(getLayoutPosition());

            Intent intent = new Intent(itemView.getContext(), DetailsActivity.class);
            Bundle extra = new Bundle();
            extra.putString("ticker", stock.getSymbol());

            intent.putExtras(extra);
            itemView.getContext().startActivity(intent);

        }
    }

}
