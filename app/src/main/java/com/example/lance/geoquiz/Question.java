package com.example.lance.geoquiz;

/**
 * Created by lance on 2016/12/26.
 */

public class Question {
    private int showQuestion;
    private boolean chooseAnswer;
    public Question(int showQuestion,boolean chooseAnswer){
        this.showQuestion = showQuestion;
        this.chooseAnswer = chooseAnswer;
    }

    public int getShowQuestion() {
        return showQuestion;
    }

    public void setShowQuestion(int showQuestion) {
        this.showQuestion = showQuestion;
    }

    public boolean getChooseAnswer() {
        return chooseAnswer;
    }

    public void setChooseAnswer(boolean chooseAnswer) {
        this.chooseAnswer = chooseAnswer;
    }
}
