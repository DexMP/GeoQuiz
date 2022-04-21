package com.dexmp.geoquiz.data;

public class Question {
    protected int textResID;
    protected boolean answerTrue;

    public Question(int id, boolean ansTrue) {
        textResID = id;
        answerTrue = ansTrue;
    }

    public int getTextResID() {
        return textResID;
    }

    public void setTextResID(int textResID) {
        this.textResID = textResID;
    }

    public boolean isAnswerTrue() {
        return answerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        this.answerTrue = answerTrue;
    }
}
