package com.geektech.quizapp.data;
import androidx.lifecycle.LiveData;
import com.geektech.quizapp.data.remote.IHistoryStorage;
import com.geektech.quizapp.data.remote.IQuizApiClient;
import com.geektech.quizapp.db.QuizDao;
import com.geektech.quizapp.models.History;
import com.geektech.quizapp.models.Question;
import com.geektech.quizapp.models.QuizResult;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuizRepository implements IHistoryStorage, IQuizApiClient {

    private IQuizApiClient iQuizApiClient;
    private IHistoryStorage iHistoryStorage;
    private QuizDao quizDao;

    public QuizRepository(IQuizApiClient iQuizApiClient, IHistoryStorage iHistoryStorage, QuizDao quizDao) {
        this.iQuizApiClient = iQuizApiClient;
        this.iHistoryStorage = iHistoryStorage;
        this.quizDao = quizDao;
    }

    public void getQuestions(int amount, Integer category, String difficulty, final IQuizApiClient.QuestionsCallBack callBack) {
        iQuizApiClient.getQuestions(amount, category, difficulty, new IQuizApiClient.QuestionsCallBack() {
            @Override
            public void onSuccess(List<Question> result) {
                for (int i = 0; i < result.size(); i++) {
                    result.set(i, shuffleAnswer(result.get(i)));
                }
                callBack.onSuccess(result);
            }

            @Override
            public void onFailure(Exception e) {
                callBack.onFailure(e);
            }
        });
    }

    private Question shuffleAnswer(Question question){
        ArrayList<String> answers = new ArrayList<>();
        answers.add(question.getCorrectAnswer());
        answers.addAll(question.getIncorrectAnswer());
        Collections.shuffle(answers);
        question.setAllAnswers(answers);
        return question;
    }

    @Override
    public QuizResult getQuizResult(int id) {
        return iHistoryStorage.getQuizResult(id);
    }

    @Override
    public void delete(int id) {
        iHistoryStorage.getQuizResult(id);
    }

    @Override
    public int saveQuestionResult(QuizResult quizResult) {
        return iHistoryStorage.saveQuestionResult(quizResult);
    }

    @Override
    public void deleteAll() {
        iHistoryStorage.deleteAll();
    }

    @Override
    public LiveData<List<QuizResult>> getAll() {
        return iHistoryStorage.getAll();
    }

    @Override
    public LiveData<List<History>> getAllHistory() {
        return iHistoryStorage.getAllHistory();
    }

//    @Override
//    public void getQuestions(int amount, String category, String difficulty, QuestionsCallBack callBack) {
//
//    }
}
