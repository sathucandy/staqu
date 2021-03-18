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

public class QuestionDetailsActivity extends AppCompatActivity {

    private final String QUESTION_LINK = "questionLink";
    private String questionLink;

    private WebView webView;
    private ProgressBar progressCircular;
    private TextView tvError;
    private Button btnRetry;
    private SwipeRefreshLayout swipeToRefresh;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_details);

        webView = findViewById(R.id.web_view);
        progressCircular = findViewById(R.id.progress_circular);
        tvError = findViewById(R.id.tv_error);
        btnRetry = findViewById(R.id.btn_retry);
        swipeToRefresh = findViewById(R.id.swipe_to_refresh);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        questionLink = getIntent().getExtras().getString(QUESTION_LINK);
        webView.loadUrl(questionLink);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                swipeToRefresh.setVisibility(View.GONE);
                webView.setVisibility(View.GONE);
                progressCircular.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                swipeToRefresh.setRefreshing(false);
                swipeToRefresh.setVisibility(View.VISIBLE);
                progressCircular.setVisibility(View.GONE);
                webView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                swipeToRefresh.setVisibility(View.GONE);
                webView.setVisibility(View.GONE);
                tvError.setVisibility(View.VISIBLE);
                btnRetry.setVisibility(View.VISIBLE);
            }
        });

        swipeToRefresh.setOnRefreshListener(() -> webView.loadUrl(questionLink));
        btnRetry.setOnClickListener(v -> {
            progressCircular.setVisibility(View.VISIBLE);
            tvError.setVisibility(View.GONE);
            btnRetry.setVisibility(View.GONE);
            webView.loadUrl(questionLink);
        });

    }
}