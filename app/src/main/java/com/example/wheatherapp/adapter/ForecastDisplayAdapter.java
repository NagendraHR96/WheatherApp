package com.example.wheatherapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wheatherapp.R;
import com.example.wheatherapp.databinding.ListIteamBinding;
import com.example.wheatherapp.model.DailyForecastReport;

import java.util.ArrayList;

public class ForecastDisplayAdapter extends RecyclerView.Adapter<ForecastDisplayAdapter.FhHolder> {
    ListIteamBinding binding;
    ArrayList<DailyForecastReport> dailyForecastReport;
    Context context;

    public ArrayList<DailyForecastReport> getDailyForecastReport() {
        return dailyForecastReport;
    }

    public void setDailyForecastReport(ArrayList<DailyForecastReport> dailyForecastReport) {
        this.dailyForecastReport = dailyForecastReport;
        notifyDataSetChanged();
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public FhHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate( LayoutInflater.from(parent.getContext()), R.layout.list_iteam,parent,false);
        return new FhHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull FhHolder holder, int position) {
        binding.setDaily(dailyForecastReport.get(position));
        binding.setDayhint(position);
        binding.executePendingBindings();

    }

    @Override
    public int getItemCount() {
        return dailyForecastReport==null?0: dailyForecastReport.size();
    }

    public class FhHolder extends RecyclerView.ViewHolder {
        public FhHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
