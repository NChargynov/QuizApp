package com.geektech.quizapp.ui.quiz;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.geektech.quizapp.QuizApp;
import com.geektech.quizapp.core.SingleLiveEvent;
import com.geektech.quizapp.data.remote.IQuizApiClient;
import com.geektech.quizapp.models.Question;
import com.geektech.quizapp.models.QuizResult;

import java.util.Date;
import java.util.List;

public class QuizViewModel extends ViewModel {

    MutableLiveData<List<Question>> questions = new MutableLiveData<>();
    MutableLiveData<Integer> currentQuestionPosition = new MutableLiveData<>();
    MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    SingleLiveEvent<Void> finishEvent = new SingleLiveEvent<>();
    SingleLiveEvent<Integer> openResultEvent = new SingleLiveEvent<>();
    private List<Question> mQuestions;
    private String resultCategory;
    private int count;
    private int id;
    private String resultDifficulty;

    public QuizViewModel() {
        currentQuestionPosition.setValue(0);
        count = 0;
    }

    void init(int progress, Integer category, String difficulty) {
        isLoading.setValue(true);
        QuizApp.repository.getQuestions(progress, category, difficulty, new IQuizApiClient.QuestionsCallBack() {
            @Override
            public void onSuccess(List<Question> result) {
                mQuestions = result;
                questions.setValue(mQuestions);
                currentQuestionPosition.setValue(0);
                isLoading.setValue(false);
                if (category != null && result.size() > 0) {
                    resultCategory = mQuestions.get(0).getCategory();
                } else {
                    resultCategory = "All";
                }
                if (difficulty != null && result.size() > 0) {
                    resultDifficulty = mQuestions.get(0).getDifficulty();
                } else {
                    resultDifficulty = "Any";
                }
            }

            @Override
            public void onFailure(Exception e) {
            }
        });
    }

    void finishQuiz() {
        QuizResult quizResult = new QuizResult(
                id,
                resultCategory,
                resultDifficulty,
                getCorrectAnswersAmount(),
                mQuestions,
                new Date());
        int resultId = QuizApp.iHistoryStorage.saveQuestionResult(quizResult);
        finishEvent.call();
        openResultEvent.setValue(resultId);
    }

    public int getCorrectAnswersAmount() {
        int correctAnswerAmount = 0;
        for (int i = 0; i <= mQuestions.size() - 1; i++) {
            if (mQuestions.get(i).getSelectAnswerPosition() != null) {
                String correctAnswer = mQuestions.get(i).getCorrectAnswer();
                String selectedAnswer = mQuestions.get(i).getAllAnswers()
                        .get(mQuestions.get(i).getSelectAnswerPosition());
                if (correctAnswer.equals(selectedAnswer)) {
                    correctAnswerAmount++;
                }
            }
        }
        return correctAnswerAmount;
    }

    void onBackPressed() {
        if (currentQuestionPosition.getValue() != 0) {
            currentQuestionPosition.setValue(--count);
        } else {
            finishEvent.call();
        }
    }

    public void onAnswerClick(int questionPosition, int answerPosition) {
        if (mQuestions.size() > questionPosition && questionPosition >= 0) {
            mQuestions.get(questionPosition).setSelectAnswerPosition(answerPosition);
            mQuestions.get(questionPosition).setAnswered(true);
            questions.setValue(mQuestions);
            if (questionPosition + 1 == mQuestions.size()) {
                finishQuiz();
            } else {
                currentQuestionPosition.setValue(++count);
            }

//        if (mQuestions.size() > currentQuestionPosition.getValue()) {
//            if (position == mQuestions.size() - 1) {
//                finishQuiz();
//            } else {
//                currentQuestionPosition.setValue(currentQuestionPosition.getValue() + 1);
//            }
//        }
        }
    }

//        private void moveToQuestionsOrFinish ( int position){
//            if (position == mQuestions.size()) {
//                finishQuiz();
//            } else {
//                currentQuestionPosition.setValue(position);
//            }
//        }

        public void onSkipClick () {
            if (mQuestions.size() >= currentQuestionPosition.getValue()) {
                mQuestions.get(currentQuestionPosition.getValue()).setAnswered(true);
                questions.setValue(mQuestions);
                currentQuestionPosition.setValue(++count);
                if (currentQuestionPosition.getValue()  == mQuestions.size()) {
                    finishQuiz();
                }
            }
        }
    }
