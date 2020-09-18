package com.geektech.quizapp.ui.result;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.geektech.quizapp.QuizApp;
import com.geektech.quizapp.models.QuizResult;

public class ResultViewModel extends ViewModel {

    MutableLiveData<QuizResult> quizResultMutableLiveData = new MutableLiveData<>();

    public void getResult(Integer id){
        quizResultMutableLiveData.setValue(QuizApp.quizDataBase.quizDao().getById(id));
    }
}
