package com.geektech.quizapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.geektech.quizapp.QuizApp;
import com.geektech.quizapp.R;
import com.geektech.quizapp.data.remote.IQuizApiClient;
import com.geektech.quizapp.models.Question;

import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private int progressSeekBar;
    private String categoryDataSpinner;
    private String difficultyDataSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        progressSeekBar = getIntent().getIntExtra("progress", 1);
        categoryDataSpinner = getIntent().getStringExtra("category");
        difficultyDataSpinner = getIntent().getStringExtra("difficulty");

        QuizApp.iQuizApiClient.getQuestions(
                progressSeekBar,
                categoryDataSpinner,
                difficultyDataSpinner,
                new IQuizApiClient.QuestionsCallBack() {
            @Override
            public void onSuccess(List<Question> questions) {
                Log.d("ololo", questions.toString());
            }

            @Override
            public void onFailure(Exception e) {

            }
        });

    }
}