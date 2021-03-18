package com.demo.assignment.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.assignment.R;
import com.demo.assignment.listeners.OnItemClickListener;
import com.demo.assignment.models.ItemsResponseModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.QuestionsViewHolder> {

    private OnItemClickListener listener;
    private List<ItemsResponseModel> questionsList = new ArrayList<>();

    public QuestionsAdapter(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void addQuestionsList(List<ItemsResponseModel> questionsList) {
        this.questionsList.addAll(questionsList);
        notifyDataSetChanged();
    }

    public void clear() {
        this.questionsList.clear();
        notifyDataSetChanged();
    }

    public static class QuestionsViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView civUser;
        private TextView tvDescription;

        public QuestionsViewHolder(@NonNull View itemView) {
            super(itemView);
            civUser = itemView.findViewById(R.id.civ_user);
            tvDescription = itemView.findViewById(R.id.tv_description);
        }
    }

    @NonNull
    @Override
    public QuestionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_quesion_item, parent, false);
        return new QuestionsViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull QuestionsViewHolder holder, int position) {
        ItemsResponseModel questionItem = questionsList.get(position);
        Picasso.get().load(questionItem.getUser().getProfileImage()).fit().into(holder.civUser);
        holder.tvDescription.setText(questionItem.getTitle());
        holder.itemView.setOnClickListener(v -> listener.onItemClick(position, questionItem));
    }

    @Override
    public int getItemCount() {
        return this.questionsList.size();
    }

}
