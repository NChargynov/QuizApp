package com.geektech.quizapp.data.remote;

import com.geektech.quizapp.core.IBaseCallBack;
import com.geektech.quizapp.models.Question;

import java.util.List;

public interface IQuizApiClient {

    void getQuestions(
            int amount,
            Integer category,
            String difficulty,
            QuestionsCallBack callBack);

    interface QuestionsCallBack extends IBaseCallBack<List<Question>> {

        @Override
        void onSuccess(List<Question> result);

        @Override
        void onFailure(Exception e);
    }


//    interface QuestionsCallBack{
//        void onSuccess(List<Question> questions);
//        void onFailure(Exception e);
//    }

}
