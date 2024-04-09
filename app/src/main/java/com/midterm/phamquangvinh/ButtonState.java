package com.midterm.phamquangvinh;

import android.graphics.Color;

import java.io.Serializable;

public class ButtonState implements Serializable {
    private int buttonTrueColor;
    private int buttonFalseColor;

    public ButtonState() {
        this.buttonTrueColor = Color.WHITE;
        this.buttonFalseColor = Color.WHITE;
    }

    public int getButtonTrueColor() {
        return buttonTrueColor;
    }

    public void setButtonTrueColor(int buttonTrueColor) {
        this.buttonTrueColor = buttonTrueColor;
    }

    public int getButtonFalseColor() {
        return buttonFalseColor;
    }

    public void setButtonFalseColor(int buttonFalseColor) {
        this.buttonFalseColor = buttonFalseColor;
    }
}
