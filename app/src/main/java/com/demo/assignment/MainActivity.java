package com.demo.assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

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
    private TextView tvError;
    private Button btnRetry;
    private SwipeRefreshLayout swipeToRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvQuestions = findViewById(R.id.rv_questions);
        progressBar = findViewById(R.id.progress_circular);
        tvError = findViewById(R.id.tv_error);
        btnRetry = findViewById(R.id.btn_retry);
        swipeToRefresh = findViewById(R.id.swipe_to_refresh);

        questionsAdapter = new QuestionsAdapter(this);
        rvQuestions.setLayoutManager(new LinearLayoutManager(this));
        rvQuestions.setAdapter(questionsAdapter);

        stackOverflowViewModel = new ViewModelProvider(this, new StackOverflowViewModelFactory()).get(StackOverflowViewModel.class);

        rvQuestions.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);

        btnRetry.setOnClickListener(v -> {
            progressBar.setVisibility(View.VISIBLE);
            tvError.setVisibility(View.GONE);
            btnRetry.setVisibility(View.GONE);
            stackOverflowViewModel.getQuestions();
        });

        swipeToRefresh.setOnRefreshListener(() -> stackOverflowViewModel.getQuestions());

        stackOverflowViewModel.getQuestions();
        stackOverflowViewModel.getQuestionsResult().observe(this, questionsResponseModelApiResult -> {
            swipeToRefresh.setRefreshing(false);
            progressBar.setVisibility(View.GONE);
            if (questionsResponseModelApiResult.getSuccess() != null) {
                swipeToRefresh.setVisibility(View.VISIBLE);
                rvQuestions.setVisibility(View.VISIBLE);
                tvError.setVisibility(View.GONE);
                btnRetry.setVisibility(View.GONE);
                questionsAdapter.clear();
                questionsAdapter.addQuestionsList(questionsResponseModelApiResult.getSuccess().getItemsList());
            } else {
                swipeToRefresh.setVisibility(View.GONE);
                rvQuestions.setVisibility(View.GONE);
                tvError.setVisibility(View.VISIBLE);
                btnRetry.setVisibility(View.VISIBLE);
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