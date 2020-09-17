package com.geektech.quizapp.ui.history;

import androidx.lifecycle.LiveData;

import com.geektech.quizapp.data.remote.IHistoryStorage;
import com.geektech.quizapp.models.QuizResult;

import java.util.List;

public class HistoryStorage implements IHistoryStorage {

    @Override
    public QuizResult getQuizResult(int id) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public int saveQuestionResult(QuizResult quizResult) {
        return 0;
    }

    @Override
    public void deleteAll() {

    }

    @Override
    public LiveData<List<QuizResult>> getAll() {
        return null;
    }
}
