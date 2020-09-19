package com.geektech.quizapp.ui.quiz.recycler;

import android.graphics.Color;
import android.text.Html;
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
        multiplyContainer = itemView.findViewById(R.id.multiply_container);
        booleanContainer = itemView.findViewById(R.id.boolean_container);
    }

    public void setButtonsEnabled(boolean enabled){
        btnAnswerOne.setEnabled(enabled);
        btnAnswerTwo.setEnabled(enabled);
        btnAnswerThree.setEnabled(enabled);
        btnAnswerFour.setEnabled(enabled);
        btnAnswerTrue.setEnabled(enabled);
        btnAnswerFalse.setEnabled(enabled);
    }

    void onBind(Question question) {
        clearHolder();
        if (question.isAnswered()){
            setButtonsEnabled(false);
        } else {
            setButtonsEnabled(true);
        }
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
        if (question.isAnswered()){
            btnState(question);
        }

    }

    private void clearHolder() {
        btnAnswerOne.setBackgroundResource(R.drawable.bg_button_answer_quiz_item);
        btnAnswerTwo.setBackgroundResource(R.drawable.bg_button_answer_quiz_item);
        btnAnswerThree.setBackgroundResource(R.drawable.bg_button_answer_quiz_item);
        btnAnswerFour.setBackgroundResource(R.drawable.bg_button_answer_quiz_item);
        btnAnswerTrue.setBackgroundResource(R.drawable.bg_button_answer_quiz_item);
        btnAnswerFalse.setBackgroundResource(R.drawable.bg_button_answer_quiz_item);
        btnAnswerOne.setTextColor(itemView.getResources().getColor(R.color.btn_text_color));
        btnAnswerTwo.setTextColor(itemView.getResources().getColor(R.color.btn_text_color));
        btnAnswerThree.setTextColor(itemView.getResources().getColor(R.color.btn_text_color));
        btnAnswerFour.setTextColor(itemView.getResources().getColor(R.color.btn_text_color));
        btnAnswerTrue.setTextColor(itemView.getResources().getColor(R.color.btn_text_color));
        btnAnswerFalse.setTextColor(itemView.getResources().getColor(R.color.btn_text_color));
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

    public void btnState(Question question) {
        if (question.getSelectAnswerPosition() != null) {
            switch (question.getSelectAnswerPosition()) {
                case 0:
                    if (question.getCorrectAnswer().equals(question.getAllAnswers().get(0))) {
                        btnAnswerOne.setBackgroundResource(R.drawable.btn_correct_state);
                        btnAnswerTrue.setBackgroundResource(R.drawable.btn_correct_state);
                    } else {
                        btnAnswerOne.setBackgroundResource(R.drawable.btn_wrong_state);
                        btnAnswerTrue.setBackgroundResource(R.drawable.btn_wrong_state);
                    }
                    btnAnswerOne.setTextColor(Color.WHITE);
                    btnAnswerTrue.setTextColor(Color.WHITE);
                    break;
                case 1:
                    if (question.getCorrectAnswer().equals(question.getAllAnswers().get(1))) {
                        btnAnswerTwo.setBackgroundResource(R.drawable.btn_correct_state);
                        btnAnswerFalse.setBackgroundResource(R.drawable.btn_correct_state);
                    } else {
                        btnAnswerTwo.setBackgroundResource(R.drawable.btn_wrong_state);
                        btnAnswerFalse.setBackgroundResource(R.drawable.btn_wrong_state);
                    }
                    btnAnswerTwo.setTextColor(Color.WHITE);
                    btnAnswerFalse.setTextColor(Color.WHITE);
                    break;
                case 2:
                    if (question.getCorrectAnswer().equals(question.getAllAnswers().get(2))) {
                        btnAnswerThree.setBackgroundResource(R.drawable.btn_correct_state);
                    } else {
                        btnAnswerThree.setBackgroundResource(R.drawable.btn_wrong_state);
                    }
                    btnAnswerThree.setTextColor(Color.WHITE);
                    break;
                case 3:
                    if (question.getCorrectAnswer().equals(question.getAllAnswers().get(3))) {
                        btnAnswerFour.setBackgroundResource(R.drawable.btn_correct_state);
                    } else {
                        btnAnswerFour.setBackgroundResource(R.drawable.btn_wrong_state);
                    }
                    btnAnswerFour.setTextColor(Color.WHITE);
                    break;
            }
        }
    }

    public interface Listener {
        void onAnswerClick(int position, int selectAnswerPosition);
    }
}
