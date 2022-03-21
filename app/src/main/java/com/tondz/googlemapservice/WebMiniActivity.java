package com.tondz.googlemapservice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.RenderProcessGoneDetail;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class WebMiniActivity extends AppCompatActivity {

    EditText edt_url;
    Button btn_go, btn_prev, btn_next;
    WebView webView;
    List<String> link;
    int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_mini);
        init();
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setAllowContentAccess(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        link = new ArrayList<>();
        webView.loadUrl("https://www.google.com/");
        link.add("https://www.google.com/");
        btn_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                link.add(edt_url.getText().toString());
                index++;
                webView.setWebViewClient(new WebViewClient() {
                    @Override
                    public void onPageFinished(WebView view, String url) {
                        webView.loadUrl("https://" + link.get(index));
                    }
                });
                webView.loadUrl("https://" + link.get(index));
            }
        });
        btn_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edt_url.setText(webView.getOriginalUrl());
                link.add(edt_url.getText().toString());
                index -= 2;
                if (index < 0) {
                    index = 0;
                }
               if(webView.canGoBack()){
                   webView.goBack();
               }

            }
        });
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edt_url.setText(webView.getUrl());
                link.add(edt_url.getText().toString());
                index++;
                if (index > link.size() - 1) {
                    index = link.size() - 1;
                }
                if(webView.canGoForward()){
                    webView.goForward();
                }

            }
        });
    }

    private void init() {
        edt_url = findViewById(R.id.edt_url);
        btn_go = findViewById(R.id.btn_go);
        btn_prev = findViewById(R.id.btn_prev);
        btn_next = findViewById(R.id.btn_next);
        webView = findViewById(R.id.webview);
    }
}