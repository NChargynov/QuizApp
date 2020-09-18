package com.geektech.quizapp.ui.quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.geektech.quizapp.QuizApp;
import com.geektech.quizapp.R;
import com.geektech.quizapp.data.remote.IQuizApiClient;
import com.geektech.quizapp.models.Question;
import com.geektech.quizapp.ui.quiz.recycler.QuestionAdapter;
import com.geektech.quizapp.ui.quiz.recycler.QuestionViewHolder;
import com.geektech.quizapp.ui.result.ResultActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity implements QuestionViewHolder.Listener {

    private int progressSeekBar;
    private Integer categoryDataSpinner;
    private String difficultyDataSpinner;
    private QuizViewModel mViewModel;
    private ProgressBar progressBarAmount, progressBarLoading;
    private TextView tvAmountItem, tvCategoryToolBar;
    private Button skipBtn;
    private ImageView imageViewBack;
    private String categoryFromSpinner;

    private RecyclerView recyclerView;
    private QuestionAdapter adapter;
    private List<Question> questions;


    public static final String EXTRA_CATEGORY = "categoryData";
    public static final String EXTRA_DIFFICULTY = "difficulty";
    public static final String EXTRA_PROGRESS = "progress";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        mViewModel = ViewModelProviders.of(this).get(QuizViewModel.class);
        initViews();
        createRecycler();

        progressSeekBar = getIntent().getIntExtra(EXTRA_PROGRESS, 1);
        categoryDataSpinner = getIntent().getIntExtra(EXTRA_CATEGORY, 0);
        difficultyDataSpinner = getIntent().getStringExtra(EXTRA_DIFFICULTY);
        categoryFromSpinner = getIntent().getStringExtra("category");

        if (categoryDataSpinner == 8) {
            categoryDataSpinner = null;
        }

        if (difficultyDataSpinner.equals("Any")) {
            difficultyDataSpinner = null;
        }

        mViewModel.openResultEvent.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                ResultActivity.start(QuizActivity.this, integer);
            }
        });


        mViewModel.finishEvent.observe(this, aVoid -> {
            finish();
        });


        mViewModel.init(progressSeekBar, categoryDataSpinner, difficultyDataSpinner);

        mViewModel.questions.observe(this, new Observer<List<Question>>() {
            @Override
            public void onChanged(List<Question> questions) {
                adapter.setQuestions(questions);
            }
        });

        mViewModel.isLoading.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    progressBarLoading.setVisibility(View.VISIBLE);
                    progressBarAmount.setVisibility(View.GONE);
                    tvAmountItem.setVisibility(View.GONE);
                    tvCategoryToolBar.setVisibility(View.GONE);
                    skipBtn.setVisibility(View.GONE);
                } else {
                    progressBarLoading.setVisibility(View.GONE);
                    tvCategoryToolBar.setVisibility(View.VISIBLE);
                    tvAmountItem.setVisibility(View.VISIBLE);
                    skipBtn.setVisibility(View.VISIBLE);
                    progressBarAmount.setVisibility(View.VISIBLE);
                }
            }
        });

        mViewModel.currentQuestionPosition.observe(this, integer -> {
            recyclerView.smoothScrollToPosition(integer);
            progressBarAmount.setProgress(integer + 1);
            progressBarAmount.setMax(progressSeekBar);
            tvAmountItem.setText((integer + 1) + "/" + progressSeekBar);
        });

        tvCategoryToolBar.setText(categoryFromSpinner);
        imageViewBack.setOnClickListener(v -> {
            mViewModel.onBackPressed();
        });

    }

    public void initViews() {
        recyclerView = findViewById(R.id.question_recycler);
        progressBarAmount = findViewById(R.id.progress_bar_amount);
        tvAmountItem = findViewById(R.id.tv_amount_item);
        tvCategoryToolBar = findViewById(R.id.tv_category_tool_bar);
        progressBarLoading = findViewById(R.id.progress_bar_is_loading);
        skipBtn = findViewById(R.id.btn_skip);
        imageViewBack = findViewById(R.id.im_back);
    }

    @SuppressLint("ClickableViewAccessibility")
    public void createRecycler() {
        questions = new ArrayList<>();
        adapter = new QuestionAdapter(questions, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(
                this, RecyclerView.HORIZONTAL, false));

        recyclerView.setOnTouchListener((v, event) -> {
            return true;
        });

        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
    }

    public static void start(Context context, int progressSeekBar, int categoryDataSpinner, String difficultyDataSpinner, String categoryFromSpinner) {
        Intent intent = new Intent(context, QuizActivity.class);
        intent.putExtra(EXTRA_PROGRESS, progressSeekBar);
        intent.putExtra(EXTRA_CATEGORY, categoryDataSpinner);
        intent.putExtra(EXTRA_DIFFICULTY, difficultyDataSpinner);
        intent.putExtra("category", categoryFromSpinner);
        context.startActivity(intent);
    }

    @Override
    public void onAnswerClick(int position, int selectAnswerPosition) {
        mViewModel.onAnswerClick(position, selectAnswerPosition);
    }

    public void onSkipClick(View view) {
        mViewModel.onSkipClick();
    }

    @Override
    public void onBackPressed() {
        mViewModel.onBackPressed();
    }
}