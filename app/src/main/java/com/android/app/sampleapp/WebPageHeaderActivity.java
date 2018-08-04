package com.android.app.sampleapp;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class WebPageHeaderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_page_header);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent webLnkIntent =getIntent();
        String headerData = webLnkIntent.getStringExtra("HEADER_DATA");
       // Uri weblinkUri= webLnkIntent.getData();
        //WebView weblinkWebVw = (WebView)findViewById(R.id.webLnkWebView);
       // weblinkWebVw.loadUrl(weblinkUri.toString());



        TextView webheaderTxtVw = (TextView) findViewById(R.id.webHeaderTxtVw);
        webheaderTxtVw.setText(headerData);

    }


}



