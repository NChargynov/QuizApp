package com.geektech.quizapp.ui.history.recycler;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geektech.quizapp.R;
import com.geektech.quizapp.models.History;

public class HistoryViewHolder extends RecyclerView.ViewHolder {

    public TextView tvCategoryData, tvCorrectAnswers, tvDifficulty, tvDate;
    private ImageView dots_three;
    public HistoryListener historyListener;

    public HistoryViewHolder(@NonNull View itemView, HistoryListener historyListener) {
        super(itemView);
        this.historyListener = historyListener;
        initViews(itemView);

        dots_three.setOnClickListener(v -> {
            historyListener.onDotsClick(getAdapterPosition(), v);
        });

    }

    private void initViews(View itemView) {
        tvCategoryData = itemView.findViewById(R.id.tv_cat_data);
        tvCorrectAnswers = itemView.findViewById(R.id.tv_cor_ans);
        tvDifficulty = itemView.findViewById(R.id.tv_diff_hist);
        tvDate = itemView.findViewById(R.id.tv_date);
        dots_three = itemView.findViewById(R.id.dots_three);
    }

    public void onBind(History history) {
        tvCategoryData.setText(history.getCategory());
        tvCorrectAnswers.setText(history.getCorrectAnswer() + "/" + history.getAmount());
        tvDifficulty.setText(history.getDifficulty());
        tvDate.setText(history.getCreatedAt().toLocaleString());
    }

    public interface HistoryListener{
        void onDotsClick(int position, View view);
    }
}
