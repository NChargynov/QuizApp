package com.geektech.quizapp.ui.history.recycler;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geektech.quizapp.R;
import com.geektech.quizapp.models.History;

public class HistoryViewHolder extends RecyclerView.ViewHolder {

    public TextView tvCategoryData, tvCorrectAnswers, tvDifficulty, tvDate;

    public HistoryViewHolder(@NonNull View itemView) {
        super(itemView);
        initViews(itemView);
    }

    private void initViews(View itemView) {
        tvCategoryData = itemView.findViewById(R.id.tv_cat_data);
        tvCorrectAnswers = itemView.findViewById(R.id.tv_cor_ans);
        tvDifficulty = itemView.findViewById(R.id.tv_diff_hist);
        tvDate = itemView.findViewById(R.id.tv_date);
    }

    public void onBind(History history) {
        tvCategoryData.setText(history.getCategory());
        tvCorrectAnswers.setText(history.getCorrectAnswer() + "/" + history.getAmount());
        tvDifficulty.setText(history.getDifficulty());
        tvDate.setText(history.getCreatedAt().toLocaleString());
    }
}
