package com.demo.assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.demo.assignment.viewmodels.StackOverflowViewModel;
import com.demo.assignment.viewmodels.StackOverflowViewModelFactory;

public class MainActivity extends AppCompatActivity {

    private StackOverflowViewModel stackOverflowViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stackOverflowViewModel = new ViewModelProvider(this, new StackOverflowViewModelFactory()).get(StackOverflowViewModel.class);

        stackOverflowViewModel.getQuestions();
        stackOverflowViewModel.getQuestionsResult().observe(this, questionsResponseModelApiResult -> {
            if (questionsResponseModelApiResult.getSuccess() != null) {

            }
        });

    }
}