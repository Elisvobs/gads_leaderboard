package com.elisvobs.gadsleaderboard.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.elisvobs.gadsleaderboard.R;
import com.elisvobs.gadsleaderboard.model.Skill;

import java.util.List;

public class SkillAdapter extends RecyclerView.Adapter<SkillAdapter.ViewHolder> {

    private Context context;
    private List<Skill> items;

    public SkillAdapter(Context context) {
        this.context = context;
    }

    public void setItems(List<Skill> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.skill_iq_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Skill item = items.get(position);
        holder.student.setText(item.getName());
        holder.skill_card.setText(context.getString(R.string.skill_details, item.getScore(), item.getCountry()));
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView student, skill_card;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            student = itemView.findViewById(R.id.skill_student);
            skill_card = itemView.findViewById(R.id.skill_desc);
        }
    }
}
