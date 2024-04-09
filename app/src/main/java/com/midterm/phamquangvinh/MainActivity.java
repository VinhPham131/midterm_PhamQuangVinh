package com.midterm.phamquangvinh;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.midterm.phamquangvinh.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MyViewModel model;
    private ImageButton btnBack, btnForward;
    private Button btnTrue, btnFalse, btnSubmit;
    private ButtonState buttonState;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        btnBack = findViewById(R.id.btn_back);
        btnForward = findViewById(R.id.btn_forward);
        btnTrue = findViewById(R.id.btn_true);
        btnFalse = findViewById(R.id.btn_false);
        btnSubmit = findViewById(R.id.btn_submit);

        buttonState = new ButtonState();


        model = new ViewModelProvider(this).get(MyViewModel.class);

        model.getCurrentQuestion().observe(this, new Observer<Question>() {
            @Override
            public void onChanged(Question question) {
                binding.tvQuesttion.setText(question.getQuestionText());

                btnTrue.setBackgroundColor(question.getButtonState().getButtonTrueColor());
                btnFalse.setBackgroundColor(question.getButtonState().getButtonFalseColor());
            }
        });

        btnTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnTrue.setBackgroundColor(Color.GREEN);
                Question currentQuestion = model.getCurrentQuestion().getValue();
                currentQuestion.getButtonState().setButtonTrueColor(Color.GREEN);
                model.answerQuestion(true);
                model.addAnsweredQuestion();
                model.addAnsweredQuestionToDatabase(MainActivity.this);

            }
        });

        btnFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnFalse.setBackgroundColor(Color.RED);
                Question currentQuestion = model.getCurrentQuestion().getValue();
                currentQuestion.getButtonState().setButtonFalseColor(Color.RED);
                model.answerQuestion(false);
                model.addAnsweredQuestion();
                model.addAnsweredQuestionToDatabase(MainActivity.this);

            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.previousQuestion();
                btnTrue.setBackgroundColor(buttonState.getButtonTrueColor());
                btnFalse.setBackgroundColor(buttonState.getButtonFalseColor());
            }
        });

        btnForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.nextQuestion();
                btnTrue.setBackgroundColor(buttonState.getButtonTrueColor());
                btnFalse.setBackgroundColor(buttonState.getButtonFalseColor());
            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("questionList", new ArrayList<>(model.getAnsweredQuestions()));
                startActivity(intent);
            }
        });
    }

}