package com.demo.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class QuestionDetailsActivity extends AppCompatActivity {

    private final String QUESTION_LINK = "questionLink";
    private String questionLink;

    private WebView webView;
    private ProgressBar progressCircular;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_details);

        webView = findViewById(R.id.web_view);
        progressCircular = findViewById(R.id.progress_circular);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        questionLink = getIntent().getExtras().getString(QUESTION_LINK);
        webView.loadUrl(questionLink);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressCircular.setVisibility(View.VISIBLE);
                webView.setVisibility(View.GONE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressCircular.setVisibility(View.GONE);
                webView.setVisibility(View.VISIBLE);
            }

        });

    }
}