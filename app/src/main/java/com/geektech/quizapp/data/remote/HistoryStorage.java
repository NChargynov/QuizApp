package com.geektech.quizapp.data.remote;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.geektech.quizapp.data.remote.IHistoryStorage;
import com.geektech.quizapp.db.QuizDao;
import com.geektech.quizapp.models.History;
import com.geektech.quizapp.models.QuizResult;

import java.util.ArrayList;
import java.util.List;

public class HistoryStorage implements IHistoryStorage {
    private QuizDao dao;

    public HistoryStorage(QuizDao dao) {
        this.dao = dao;
    }

    @Override
    public QuizResult getQuizResult(int id) {
        return dao.getById(id);
    }

    @Override
    public void delete(int id) {
        dao.deleteById(id);
    }

    @Override
    public int saveQuestionResult(QuizResult quizResult) {
        return (int) dao.insert(quizResult);
    }

    @Override
    public void deleteAll() {
        dao.deleteAll();
    }

    @Override
    public LiveData<List<QuizResult>> getAll() {
        return dao.getAll();
    }

    @Override
    public LiveData<List<History>> getAllHistory() {
        return Transformations.map(getAll(), new Function<List<QuizResult>, List<History>>() {
            @Override
            public List<History> apply(List<QuizResult> quizResult) {
                ArrayList<History> histories = new ArrayList<>();
                if (quizResult.size() > 0){
                    for (int i = 0; i < quizResult.size(); i++) {
                        histories.add(i, new History(
                                quizResult.get(i).getId(),
                                quizResult.get(i).getCategory(),
                                quizResult.get(i).getDifficulty(),
                                quizResult.get(i).getCorrectAnswerResult(),
                                quizResult.get(i).getQuestions().size(),
                                quizResult.get(i).getCreatedAt()));
                    }
                }
                return histories;
            }
        });
    }
}
