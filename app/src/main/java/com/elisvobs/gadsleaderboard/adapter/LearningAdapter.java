package com.elisvobs.gadsleaderboard.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.elisvobs.gadsleaderboard.R;
import com.elisvobs.gadsleaderboard.model.Learning;

import java.util.List;

public class LearningAdapter extends RecyclerView.Adapter<LearningAdapter.ViewHolder> {

    private Context context;
    private List<Learning> items;

    public LearningAdapter(Context context) {
        this.context = context;
    }

    public void setItems(List<Learning> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.learning_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Learning item = items.get(position);
        holder.learner.setText(item.getName());
        holder.description.setText(context.getString(R.string.learning_details, item.getHours(), item.getCountry()));
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView learner, description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            learner = itemView.findViewById(R.id.learner);
            description = itemView.findViewById(R.id.learner_desc);
        }
    }
}
