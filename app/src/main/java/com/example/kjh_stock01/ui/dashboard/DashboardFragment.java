package com.example.kjh_stock01.ui.dashboard;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kjh_stock01.R;
import com.example.kjh_stock01.databinding.FragmentDashboardBinding;
import com.example.kjh_stock01.ui.dashboard.stock.JsonPlaceHolderStockApi;
import com.example.kjh_stock01.ui.dashboard.stock.Stock;
import com.example.kjh_stock01.ui.dashboard.stock.StockItemClicked;
import com.example.kjh_stock01.ui.dashboard.stock.StockListAdapter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DashboardFragment extends Fragment implements StockItemClicked {

    private FragmentDashboardBinding binding;
    RecyclerView recyclerView;
    StockListAdapter mAdapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        final TextView textView = binding.textDashboard;
//        dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        recyclerView = root.findViewById(R.id.dashboard_recylerView);
        fetch_date();

        mAdapter = new StockListAdapter(this);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return root;
    }

    private void fetch_date() {
        Log.d(getClass().getName(), "fetch_date()");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://financialmodelingprep.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderStockApi jsonPlaceHolderStockApi = retrofit.create(JsonPlaceHolderStockApi.class);

        Call<List<Stock>> call = jsonPlaceHolderStockApi.getMostGainer(Stock.getApikey());

        call.enqueue(new Callback<List<Stock>>() {
            @Override
            public void onResponse(Call<List<Stock>> call, Response<List<Stock>> response) {
                if (!response.isSuccessful()) {
                    Log.d(getClass().getName(), "Code : " + response.code());
                    return;
                }

                List<Stock> stocks = response.body();
                ArrayList<Stock> stockArrayList = new ArrayList<Stock>();

                for (Stock stock : stocks) {
                    String content = "";
                    content += "Symbol : " + stock.getSymbol() + "\n";
                    content += "Name : " + stock.getName() + "\n";
                    content += "Change : " + stock.getChange() + "\n";
                    content += "Price : " + stock.getPrice() + "\n";
                    content += "ChangesPercentage : " + stock.getChangesPercentage() + "\n";

//                    Log.d(getClass().getName(), content);

                    DecimalFormat df = new DecimalFormat("#.##");

                    Stock stockItem = new Stock(
                        stock.getSymbol(),
                        stock.getName(),
                        stock.getChange(),
                        "$" + String.format("%.2f", Double.parseDouble(stock.getPrice())) ,
                        String.format("%.2f", Double.parseDouble(stock.getChangesPercentage()))
                    );
                    stockArrayList.add(stockItem);
                }
                mAdapter.UpdateStock(stockArrayList);
            }

            @Override
            public void onFailure(Call<List<Stock>> call, Throwable t) {
                Log.d(getClass().getName(), t.getMessage().toString());
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onItemClicked(Stock stock) {

    }
}