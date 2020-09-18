package com.geektech.quizapp.ui.history;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.geektech.quizapp.QuizApp;
import com.geektech.quizapp.models.History;
import com.geektech.quizapp.models.QuizResult;

import java.util.List;

public class HistoryViewModel extends ViewModel {

    private List<QuizResult> mHistory;

    LiveData<List<History>> historyLiveData = QuizApp.iHistoryStorage.getAllHistory();
    LiveData<List<QuizResult>> quizResultData = QuizApp.iHistoryStorage.getAll();
}