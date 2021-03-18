package com.demo.assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.demo.assignment.databinding.ActivityQuestionDetailsBinding;

public class QuestionDetailsActivity extends AppCompatActivity {

    private final String QUESTION_LINK = "questionLink";
    private ActivityQuestionDetailsBinding activityQuestionDetailsBinding;
    private String questionLink;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_details);

        activityQuestionDetailsBinding = activityQuestionDetailsBinding.inflate(getLayoutInflater());
        View view = activityQuestionDetailsBinding.getRoot();
        setContentView(view);
        WebSettings webSettings = activityQuestionDetailsBinding.webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        questionLink = getIntent().getExtras().getString(QUESTION_LINK);
        activityQuestionDetailsBinding.webView.loadUrl(questionLink);
        activityQuestionDetailsBinding.webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                activityQuestionDetailsBinding.swipeToRefresh.setVisibility(View.GONE);
                activityQuestionDetailsBinding.webView.setVisibility(View.GONE);
                activityQuestionDetailsBinding.progressCircular.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                activityQuestionDetailsBinding.swipeToRefresh.setRefreshing(false);
                activityQuestionDetailsBinding.swipeToRefresh.setVisibility(View.VISIBLE);
                activityQuestionDetailsBinding.progressCircular.setVisibility(View.GONE);
                activityQuestionDetailsBinding.webView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                activityQuestionDetailsBinding.swipeToRefresh.setVisibility(View.GONE);
                activityQuestionDetailsBinding.webView.setVisibility(View.GONE);
                activityQuestionDetailsBinding.tvError.setVisibility(View.VISIBLE);
                activityQuestionDetailsBinding.btnRetry.setVisibility(View.VISIBLE);
            }
        });

        activityQuestionDetailsBinding.swipeToRefresh.setOnRefreshListener(() -> activityQuestionDetailsBinding.webView.loadUrl(questionLink));
        activityQuestionDetailsBinding.btnRetry.setOnClickListener(v -> {
            activityQuestionDetailsBinding.progressCircular.setVisibility(View.VISIBLE);
            activityQuestionDetailsBinding.tvError.setVisibility(View.GONE);
            activityQuestionDetailsBinding.btnRetry.setVisibility(View.GONE);
            activityQuestionDetailsBinding.webView.loadUrl(questionLink);
        });

    }
}