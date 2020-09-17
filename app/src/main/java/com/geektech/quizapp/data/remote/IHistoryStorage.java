package com.geektech.quizapp.data.remote;


import androidx.lifecycle.LiveData;

import com.geektech.quizapp.models.QuizResult;

import java.util.List;


public interface IHistoryStorage {

    QuizResult getQuizResult(int id);

    void delete(int id);

    int saveQuestionResult(QuizResult quizResult);

    void deleteAll();

    LiveData<List<QuizResult>> getAll();


}
