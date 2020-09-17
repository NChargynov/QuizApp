package com.geektech.quizapp.ui.quiz.recycler;

import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geektech.quizapp.R;
import com.geektech.quizapp.models.Question;


public class QuestionViewHolder extends RecyclerView.ViewHolder {

    public TextView tvQuestionText;
    private Button btnAnswerOne, btnAnswerTwo, btnAnswerThree, btnAnswerFour, btnAnswerTrue, btnAnswerFalse;
    private LinearLayout multiplyContainer, booleanContainer;
    private Listener listener;

    public QuestionViewHolder(@NonNull View itemView, Listener listener) {
        super(itemView);
        this.listener = listener;
        initViews();
        initListeners();
    }

    private void initViews() {
        tvQuestionText = itemView.findViewById(R.id.tv_question_text);
        btnAnswerOne = itemView.findViewById(R.id.btn_multiply_answer_1);
        btnAnswerTwo = itemView.findViewById(R.id.btn_multiply_answer_2);
        btnAnswerThree = itemView.findViewById(R.id.btn_multiply_answer_3);
        btnAnswerFour = itemView.findViewById(R.id.btn_multiply_answer_4);
        btnAnswerTrue = itemView.findViewById(R.id.btn_boolean_answer_true);
        btnAnswerFalse = itemView.findViewById(R.id.btn_boolean_answer_false);
        btnAnswerFalse = itemView.findViewById(R.id.btn_boolean_answer_false);
        multiplyContainer = itemView.findViewById(R.id.multiply_container);
        booleanContainer = itemView.findViewById(R.id.boolean_container);
    }

    void onBind(Question question) {
        tvQuestionText.setText(Html.fromHtml(question.getQuestion()));
        if (question.getType().equals("multiple")) {
            multiplyContainer.setVisibility(View.VISIBLE);
            booleanContainer.setVisibility(View.GONE);
            btnAnswerOne.setText(Html.fromHtml(question.getAllAnswers().get(0)));
            btnAnswerTwo.setText(Html.fromHtml(question.getAllAnswers().get(1)));
            btnAnswerThree.setText(Html.fromHtml(question.getAllAnswers().get(2)));
            btnAnswerFour.setText(Html.fromHtml(question.getAllAnswers().get(3)));
        } else if (question.getType().equals("boolean")) {
            multiplyContainer.setVisibility(View.GONE);
            booleanContainer.setVisibility(View.VISIBLE);
            btnAnswerTrue.setText(Html.fromHtml(question.getAllAnswers().get(0)));
            btnAnswerFalse.setText(Html.fromHtml(question.getAllAnswers().get(1)));
        }

    }

    void initListeners() {
        btnAnswerOne.setOnClickListener(v -> {
            listener.onAnswerClick(getAdapterPosition(), 0);
        });
        btnAnswerTwo.setOnClickListener(v -> {
            listener.onAnswerClick(getAdapterPosition(), 1);
        });
        btnAnswerThree.setOnClickListener(v -> {
            listener.onAnswerClick(getAdapterPosition(), 2);
        });
        btnAnswerFour.setOnClickListener(v -> {
            listener.onAnswerClick(getAdapterPosition(), 3);
        });
        btnAnswerTrue.setOnClickListener(v -> {
            listener.onAnswerClick(getAdapterPosition(), 0);
        });
        btnAnswerFalse.setOnClickListener(v -> {
            listener.onAnswerClick(getAdapterPosition(), 1);
        });
    }

    public interface Listener {
        void onAnswerClick(int position, int selectAnswerPosition);
    }
}
