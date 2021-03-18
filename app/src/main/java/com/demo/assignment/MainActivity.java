package com.demo.assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.demo.assignment.adapters.QuestionsAdapter;
import com.demo.assignment.listeners.OnItemClickListener;
import com.demo.assignment.models.ItemsResponseModel;
import com.demo.assignment.viewmodels.StackOverflowViewModel;
import com.demo.assignment.viewmodels.StackOverflowViewModelFactory;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {

    private final String QUESTION_LINK = "questionLink";

    private StackOverflowViewModel stackOverflowViewModel;
    private QuestionsAdapter questionsAdapter;

    private RecyclerView rvQuestions;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvQuestions = findViewById(R.id.rv_questions);
        progressBar = findViewById(R.id.progress_circular);

        questionsAdapter = new QuestionsAdapter(this);
        rvQuestions.setLayoutManager(new LinearLayoutManager(this));
        rvQuestions.setAdapter(questionsAdapter);

        stackOverflowViewModel = new ViewModelProvider(this, new StackOverflowViewModelFactory()).get(StackOverflowViewModel.class);

        rvQuestions.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        stackOverflowViewModel.getQuestions();
        stackOverflowViewModel.getQuestionsResult().observe(this, questionsResponseModelApiResult -> {
            rvQuestions.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            if (questionsResponseModelApiResult.getSuccess() != null) {
                questionsAdapter.addQuestionsList(questionsResponseModelApiResult.getSuccess().getItemsList());
            }
        });

    }

    @Override
    public void onItemClick(int position, Object data) {
        ItemsResponseModel questionItem = (ItemsResponseModel) data;
        Intent intent = new Intent(this, QuestionDetailsActivity.class);
        intent.putExtra(QUESTION_LINK, questionItem.getUser().getLink());
        startActivity(intent);
    }
}