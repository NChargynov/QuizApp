package com.geektech.quizapp.ui.result;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.geektech.quizapp.R;
import com.geektech.quizapp.models.QuizResult;

public class ResultActivity extends AppCompatActivity {

    private static final String EXTRA_QUIZ_ID = "result_id";
    private TextView tvCategoryData, tvDifficultyData, tvCorrectAnswerData, tvResultData;
    private ResultViewModel mResultViewModel;
    private Integer id;
    private ImageView imageCheck, imageFail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        id = getIntent().getIntExtra(EXTRA_QUIZ_ID, 0);
        initViews();
        mResultViewModel.getResult(id);
        setViewData();

    }

    private void setViewData() {
        mResultViewModel.quizResultMutableLiveData.observe(this, new Observer<QuizResult>() {
            @Override
            public void onChanged(QuizResult quizResult) {
                tvCategoryData.setText("Category: " + quizResult.getCategory());
                tvDifficultyData.setText(quizResult.getDifficulty());
                tvCorrectAnswerData.setText(quizResult.getCorrectAnswerResult() + "/" + quizResult.getQuestions().size());
                int correctAnswerPercent = (int) ((double) quizResult.getCorrectAnswerResult() / quizResult.getQuestions().size() * 100);
                tvResultData.setText(correctAnswerPercent + " %");
                if (correctAnswerPercent < 80) {
                    imageCheck.setVisibility(View.GONE);
                    imageFail.setVisibility(View.VISIBLE);
                } else {
                    imageCheck.setVisibility(View.VISIBLE);
                    imageFail.setVisibility(View.GONE);
                }
            }
        });
    }

    private void initViews() {
        mResultViewModel = ViewModelProviders.of(this).get(ResultViewModel.class);
        tvCategoryData = findViewById(R.id.tv_category_result_act);
        tvDifficultyData = findViewById(R.id.tv_diffic_data_result_act);
        tvCorrectAnswerData = findViewById(R.id.tv_correct_answer_data);
        tvResultData = findViewById(R.id.tv_result_data);
        imageCheck = findViewById(R.id.image_check);
        imageFail = findViewById(R.id.image_fail);
    }

    public static void start(Context context, Integer resultId) {
        context.startActivity(new Intent(context, ResultActivity.class).putExtra(EXTRA_QUIZ_ID, resultId));
    }

    public void onBtnFinishClick(View view) {
        finish();
    }
}