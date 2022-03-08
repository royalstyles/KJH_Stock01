package com.example.kjh_stock01.ui.dashboard.stock;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kjh_stock01.R;

import java.util.ArrayList;

public class StockListAdapter extends RecyclerView.Adapter<StockViewHolder> {

    ArrayList<Stock> item = new ArrayList<Stock>();
    private StockItemClicked listener;

    public StockListAdapter(StockItemClicked stockItemClicked) {
        this.listener = stockItemClicked;
    }

    @NonNull
    @Override
    public StockViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_stock02, parent, false);
        StockViewHolder viewHolder = new StockViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StockViewHolder holder, int position) {
        Stock currentItem = item.get(position);

        if (currentItem.getSymbol() == "null"){
            holder.Symbol.setText("Unkown Source");
        } else {
            holder.Symbol.setText(currentItem.getSymbol());
        }

//        if (currentItem.getName() == "null") {
//            holder.Name.setText("Unkown Source");
//        } else {
//            holder.Name.setText(currentItem.getName());
//        }
//
//        if (currentItem.getChange() == "null") {
//            holder.Change.setText("Unkown Source");
//        } else {
//            holder.Change.setText(currentItem.getChange());
//        }

        if (currentItem.getPrice() == "null") {
            holder.Price.setText("Unkown Source");
        } else {
            holder.Price.setText(currentItem.getPrice());
        }

        if (currentItem.getChangesPercentage() == "null") {
            holder.ChangesPercentage.setText("Unkown Source");
        } else {
            holder.ChangesPercentage.setText(currentItem.getChangesPercentage());

            Log.d(getClass().getName(), "onBindViewHolder");
            Log.d(getClass().getName(), currentItem.getChangesPercentage());

            if ((int)Math.round(Double.parseDouble(currentItem.getChangesPercentage())) > 0){
                holder.ChangesPercentage.setText("+" + currentItem.getChangesPercentage() + "%");
                holder.ChangesPercentage.setTextColor(Color.parseColor("#00994C"));
                holder.ChangesPercentage.setBackgroundColor(Color.parseColor("#E0E0E0"));
            } else {
                holder.ChangesPercentage.setText("-" + currentItem.getChangesPercentage() + "%");
                holder.ChangesPercentage.setTextColor(Color.parseColor("#990000"));
                holder.ChangesPercentage.setBackgroundColor(Color.parseColor("#E0E0E0"));
            }
//            holder.ChangesPercentage.setPadding(5,5,5,5);
        }
//        holder.ChangesPercentage.setBackgroundColor(Color.BLUE);
    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public void UpdateStock(ArrayList<Stock> updatestock) {
        item.clear();
        item.addAll(updatestock);

        notifyDataSetChanged();
    }
}

class StockViewHolder extends RecyclerView.ViewHolder {
    TextView Symbol, Name, Change, Price, ChangesPercentage;

    public StockViewHolder(@NonNull View view) {
        super(view);
        Symbol = view.findViewById(R.id.Stock_symbol);
//        Name = view.findViewById(R.id.Stock_name);
//        Change = view.findViewById(R.id.Stock_change);
        Price = view.findViewById(R.id.Stock_price);
        ChangesPercentage = view.findViewById(R.id.Stock_changesPercentage);
    }
}


