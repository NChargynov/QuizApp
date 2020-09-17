package com.geektech.quizapp.ui.history.recycler;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geektech.quizapp.R;

public class HistoryViewHolder extends RecyclerView.ViewHolder {

    public TextView textView;

    public HistoryViewHolder(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.tv_category);
    }
}
