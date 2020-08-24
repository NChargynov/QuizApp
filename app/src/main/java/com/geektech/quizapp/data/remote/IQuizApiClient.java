package com.geektech.quizapp.data.remote;

import com.geektech.quizapp.models.Question;

import java.util.List;

public interface IQuizApiClient {

    void getQuestions(
            int amount,
            String category,
            String difficulty,
            QuestionsCallBack callBack);

    interface QuestionsCallBack{
        void onSuccess(List<Question> questions);
        void onFailure(Exception e);
    }

}
