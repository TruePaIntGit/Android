package com.truepaint.androidapp;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText urlEditText;
    private Button goButton;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        urlEditText = findViewById(R.id.urlEditText);
        goButton = findViewById(R.id.goButton);
        webView = findViewById(R.id.webView);

        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);

        goButton.setOnClickListener(v -> loadUrl());
    }

    private void loadUrl() {
        String input = urlEditText.getText().toString().trim();

        if (input.isEmpty()) {
            Toast.makeText(this, "Please enter a URL", Toast.LENGTH_SHORT).show();
            return;
        }

        String url = formatUrl(input);
        webView.loadUrl(url);
    }

    private String formatUrl(String url) {
        if (!url.contains("://")) {
            url = "https://" + url;
        }
        return url;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}