package com.demo.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class QuestionDetailsActivity extends AppCompatActivity {

    private final String QUESTION_LINK = "questionLink";
    private String questionLink;

    private WebView webView;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_details);

        webView = findViewById(R.id.web_view);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        Bundle bundle = new Bundle();
        questionLink = bundle.getString(QUESTION_LINK);
        webView.loadUrl(questionLink);

    }
}