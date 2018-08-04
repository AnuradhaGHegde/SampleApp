package com.android.app.sampleapp;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    void onSubmitBtnClick(View v)
    {
        ProgressBar probar = (ProgressBar)findViewById(R.id.ProgressBar);
        probar.setVisibility(View.VISIBLE);
        EditText weblinkedtTxt =(EditText) findViewById(R.id.webLinkEditTxtVw);
        String webLinkStr = weblinkedtTxt.getText().toString();

        FetchHeaderTask fetchTask = new FetchHeaderTask();
        fetchTask.execute(webLinkStr);
    }

    public class FetchHeaderTask extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... params) {

            try {
                URL url = new URL(params[0]);
                HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                Map<String, List<String>> headerlist = urlConnection.getHeaderFields();

                headerlist.size();
                StringBuilder finalString = new StringBuilder();

                for(Map.Entry<String,List<String>> entry: headerlist.entrySet())
                {
                    String valuelist = "";
                    for(String value :entry.getValue()) {
                        valuelist =valuelist+" "+value;
                    }
                    finalString.append(entry.getKey()+" : "+ valuelist+"\n");
                }

                return finalString.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "MalformedURLException!!";
            } catch (ProtocolException e) {
                return "ProtocolException!!";
            } catch (IOException e) {
                return "IOException!!";
            }

        }

        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            ProgressBar probar = (ProgressBar)findViewById(R.id.ProgressBar);
            probar.setVisibility(View.GONE);
            Intent webLinkIntent = new Intent(getApplicationContext(),WebPageHeaderActivity.class);
            webLinkIntent.putExtra("HEADER_DATA",result);
            startActivity(webLinkIntent);
        }
    }
}
