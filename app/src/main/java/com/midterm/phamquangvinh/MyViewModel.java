package com.midterm.phamquangvinh;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.net.HttpCookie;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyViewModel extends ViewModel {
    private List<Question> answeredQuestions = new ArrayList<>();

    private List<Question> questions;
    private MutableLiveData<Question> currentQuestion;

    public MyViewModel() {
        questions = Arrays.asList(
                new Question("Is DaNang a city in Vietnam?", true),
                new Question("Is SaiGon a city in Vietnam?",true),
                new Question("is HaNoi a city in Vietnam?",true),
                new Question("Is Hue a city in Vietnam?",true),
                new Question("Is BinhDinh a city in Vietnam?",true),
                new Question("Is QuangNam a city in Vietnam?",true),
                new Question("Is QuangNgai a city in Vietnam?",true),
                new Question("Is QuangTri a city in Vietnam?",true)
        );
        currentQuestion = new MutableLiveData<>();
        currentQuestion.setValue(questions.get(0));
    }

    public void addQuestionToList() {
        int currentIndex = questions.indexOf(currentQuestion.getValue());
        answeredQuestions.add(questions.get(currentIndex));
    }

    public List<Question> getAnsweredQuestions() {
        return answeredQuestions;
    }
    public LiveData<Question> getCurrentQuestion() {
        return currentQuestion;
    }

    public void nextQuestion() {
        int currentIndex = questions.indexOf(currentQuestion.getValue());
        if (currentIndex < questions.size() - 1) {
            currentQuestion.setValue(questions.get(currentIndex + 1));
        }
    }

    public void previousQuestion() {
        int currentIndex = questions.indexOf(currentQuestion.getValue());
        if (currentIndex > 0) {
            currentQuestion.setValue(questions.get(currentIndex - 1));
        }
    }

    public void answerQuestion(boolean answer) {
        int currentIndex = questions.indexOf(currentQuestion.getValue());
        questions.get(currentIndex).setAnswer(answer);
        currentQuestion.getValue().setAnswer(answer);
    }
    public void addAnsweredQuestion() {
        answeredQuestions.add(currentQuestion.getValue());
    }

    public List<Question> getQuestions() {
        return questions;
    }
    public void addAnsweredQuestionToDatabase(Context context) {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        dbHelper.addQuestion(currentQuestion.getValue());
    }
}