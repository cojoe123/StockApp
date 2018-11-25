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

public class CryptoAdapter extends RecyclerView.Adapter<CryptoAdapter.CryptoHolder> {

    private List<Stocks> values;

    CryptoAdapter(List<Stocks> values) {
        this.values = values;
    }

    @NonNull
    @Override
    public CryptoAdapter.CryptoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View stockView = inflater.inflate(R.layout.list_item_crypto, parent, false);

        return new CryptoAdapter.CryptoHolder(stockView);
    }

    @Override
    public void onBindViewHolder(@NonNull CryptoAdapter.CryptoHolder Holder, int position) {
        Stocks stock = values.get(position);

        TextView titleTextView = Holder.stockTextView;
        titleTextView.setText(stock.getSymbol());

        TextView priceTitleTextView = Holder.priceTextView;
        priceTitleTextView.setText("Latest Price: " + String.format("%.2f", stock.getLatestPrice()));

        TextView changePriceView = Holder.changeView;
        changePriceView.setText(String.format("%.2f", stock.getPriceChange()));
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    public class CryptoHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView stockTextView;
        TextView priceTextView;
        TextView changeView;

        CryptoHolder(View itemView) {
            super(itemView);

            stockTextView = itemView.findViewById(R.id.list_item_crypto_text);
            priceTextView = itemView.findViewById(R.id.list_item_crypto_price_text);
            changeView = itemView.findViewById(R.id.list_item_crypto_change_text);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Stocks stock = values.get(getLayoutPosition());

            Intent intent = new Intent(itemView.getContext(), CryptoDetailsActivity.class);
            Bundle extra = new Bundle();
            extra.putString("ticker", stock.getSymbol());
            extra.putString("company", stock.getName());
            extra.putDouble("delta", stock.getPriceChange());
            extra.putDouble("latest", stock.getLatestPrice());

            intent.putExtras(extra);
            itemView.getContext().startActivity(intent);

        }
    }

}
