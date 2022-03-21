package com.tondz.googlemapservice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class LoadHtmlActivity extends AppCompatActivity {

    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_html);
        webView = findViewById(R.id.webview);
       webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("file:///android_asset/clumsy-bird/index.html");
    }
}