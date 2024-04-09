package com.midterm.phamquangvinh;

import java.io.Serializable;

public class Question implements Serializable {
    private String questionText;
    private boolean answer;
    private ButtonState buttonState;

    public Question(String questionText, boolean answer) {
        this.answer = answer;
        this.questionText = questionText;
        this.buttonState = new ButtonState();
    }

    public ButtonState getButtonState() {
        return buttonState;
    }

    public void setButtonState(ButtonState buttonState) {
        this.buttonState = buttonState;
    }

    public String getQuestionText() {
        return questionText;
    }
    public boolean isAnswer() {
        return answer;
    }
    public void setAnswer(boolean answer) {
        this.answer = answer;
    }

    public boolean getAnswer() {
        return answer;
    }
}
