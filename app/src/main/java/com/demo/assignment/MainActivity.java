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
import com.demo.assignment.databinding.ActivityMainBinding;
import com.demo.assignment.listeners.OnItemClickListener;
import com.demo.assignment.models.ItemsResponseModel;
import com.demo.assignment.viewmodels.StackOverflowViewModel;
import com.demo.assignment.viewmodels.StackOverflowViewModelFactory;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {

    private final String QUESTION_LINK = "questionLink";

    private ActivityMainBinding activityMainBinding;
    private StackOverflowViewModel stackOverflowViewModel;
    private QuestionsAdapter questionsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = activityMainBinding.getRoot();
        setContentView(view);

        questionsAdapter = new QuestionsAdapter(this);
        activityMainBinding.rvQuestions.setLayoutManager(new LinearLayoutManager(this));
        activityMainBinding.rvQuestions.setAdapter(questionsAdapter);

        stackOverflowViewModel = new ViewModelProvider(this, new StackOverflowViewModelFactory()).get(StackOverflowViewModel.class);

        activityMainBinding.progressCircular.setVisibility(View.VISIBLE);
        activityMainBinding.swipeToRefresh.setVisibility(View.GONE);
        activityMainBinding.rvQuestions.setVisibility(View.GONE);

        activityMainBinding.btnRetry.setOnClickListener(v -> {
            activityMainBinding.progressCircular.setVisibility(View.VISIBLE);
            activityMainBinding.tvError.setVisibility(View.GONE);
            activityMainBinding.btnRetry.setVisibility(View.GONE);
            stackOverflowViewModel.getQuestions();
        });

        activityMainBinding.swipeToRefresh.setOnRefreshListener(() -> stackOverflowViewModel.getQuestions());

        stackOverflowViewModel.getQuestions();
        stackOverflowViewModel.getQuestionsResult().observe(this, questionsResponseModelApiResult -> {
            activityMainBinding.swipeToRefresh.setRefreshing(false);
            activityMainBinding.progressCircular.setVisibility(View.GONE);
            if (questionsResponseModelApiResult.getSuccess() != null) {
                activityMainBinding.swipeToRefresh.setVisibility(View.VISIBLE);
                activityMainBinding.rvQuestions.setVisibility(View.VISIBLE);
                activityMainBinding.tvError.setVisibility(View.GONE);
                activityMainBinding.btnRetry.setVisibility(View.GONE);
                questionsAdapter.clear();
                questionsAdapter.addQuestionsList(questionsResponseModelApiResult.getSuccess().getItemsList());
            } else {
                activityMainBinding.swipeToRefresh.setVisibility(View.GONE);
                activityMainBinding.rvQuestions.setVisibility(View.GONE);
                activityMainBinding.tvError.setVisibility(View.VISIBLE);
                activityMainBinding.btnRetry.setVisibility(View.VISIBLE);
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