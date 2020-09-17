package com.geektech.quizapp.ui.quiz;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.geektech.quizapp.QuizApp;
import com.geektech.quizapp.core.SingleLiveEvent;
import com.geektech.quizapp.data.remote.IQuizApiClient;
import com.geektech.quizapp.models.Question;

import java.util.List;

public class QuizViewModel extends ViewModel {

    MutableLiveData<List<Question>> questions = new MutableLiveData<>();
    MutableLiveData<Integer> currentQuestionPosition = new MutableLiveData<>();
    MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    SingleLiveEvent<Void> finishEvent = new SingleLiveEvent<>();
    private List<Question> mQuestions;
    private String resultCategory;
    private int count;

    public QuizViewModel(){
        currentQuestionPosition.setValue(0);
        count = 0;
    }

    void init(int progress, Integer category, String difficulty) {
        isLoading.setValue(true);
        QuizApp.repository.getQuestions(progress, category , difficulty, new IQuizApiClient.QuestionsCallBack() {
            @Override
            public void onSuccess(List<Question> result) {
                mQuestions = result;
                questions.setValue(mQuestions);
                currentQuestionPosition.setValue(0);
                isLoading.setValue(false);
                if (category != null && result.size() > 0){
                    resultCategory = mQuestions.get(0).getCategory();
                } else {
                    resultCategory = "Mixed";
                }
            }

            @Override
            public void onFailure(Exception e) {
            }
        });
    }

    void finishQuiz() {
    }

    void skipClick() {
        isLoading.setValue(true);
    }


    void onBackPressed() {
        if (currentQuestionPosition.getValue() != 0){
            currentQuestionPosition.setValue(--count);
        } else {
            finishEvent.call();
        }
    }

    public void onAnswerClick(int questionPosition, int answerPosition) {
        if (currentQuestionPosition.getValue() == null || mQuestions == null) {
            return;
        }
        Question question = mQuestions.get(questionPosition);
        question.setSelectAnswerPosition(answerPosition);
        mQuestions.set(questionPosition, question);
        questions.setValue(mQuestions);
        moveToQuestionsOrFinish(questionPosition + 1);
        currentQuestionPosition.setValue(++count);

//        if (mQuestions.size() > currentQuestionPosition.getValue()) {
//            if (position == mQuestions.size() - 1) {
//                finishQuiz();
//            } else {
//                currentQuestionPosition.setValue(currentQuestionPosition.getValue() + 1);
//            }
//        }
    }

    private void moveToQuestionsOrFinish(int position) {
        if (position == mQuestions.size()) {
            finishQuiz();
        } else {
            currentQuestionPosition.setValue(position);
        }
    }

    public void onSkipClick() {
        if (mQuestions.size() >= currentQuestionPosition.getValue()){
            mQuestions.get(currentQuestionPosition.getValue()).setAnswered(true);
            questions.setValue(mQuestions);
            currentQuestionPosition.setValue(++count);
            if (currentQuestionPosition.getValue() + 1 == mQuestions.size()){
                finishQuiz();
            }
        }
    }
}
