package com.geektech.quizapp.ui.quiz.recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geektech.quizapp.R;
import com.geektech.quizapp.models.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Question> questions;
    private QuestionViewHolder.Listener listener;


    public QuestionAdapter(List<Question> questions, QuestionViewHolder.Listener listener) {
        this.questions = questions;
        this.listener = listener;
    }

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_item, parent, false);
        return new QuestionViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof QuestionViewHolder) {
            ((QuestionViewHolder) holder).onBind(questions.get(position));
        }

    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public void setQuestions(List<Question> question){
        questions.clear();
        questions.addAll(question);
        notifyDataSetChanged();
    }
}
