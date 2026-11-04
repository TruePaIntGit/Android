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

public class MainActivity extends AppCompatActivity {
        private EditText urlEditText;
        private Button openButton;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            urlEditText = findViewById(R.id.urlEditText);
            openButton = findViewById(R.id.openButton);

            openButton.setOnClickListener(v -> openUrl());
        }

        private void openUrl() {
            String input = urlEditText.getText().toString().trim();

            if (TextUtils.isEmpty(input)) {
                Toast.makeText(this, "Please enter a URL", Toast.LENGTH_SHORT).show();
                return;
            }

            String url = validateAndFormatUrl(input);

            if (url == null) {
                Toast.makeText(this, "Invalid URL", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));

            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            } else {
                Toast.makeText(this, "No browser found", Toast.LENGTH_SHORT).show();
            }
        }

        private String validateAndFormatUrl(String url) {
            if (!url.contains("://")) {
                url = "https://" + url;
            }

            if (!android.util.Patterns.WEB_URL.matcher(url).matches()) {
                return null;
            }

            return url;
        }
    }
}