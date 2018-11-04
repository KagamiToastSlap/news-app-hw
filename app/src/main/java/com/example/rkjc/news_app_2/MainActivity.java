package com.example.rkjc.news_app_2;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {
    NetworkUtils urlTest = new NetworkUtils();
    private TextView txtviews;
    private RecyclerView recviews;
    private ArrayList<String> aTitle = new ArrayList<>();
    private ArrayList<String> aDescription = new ArrayList<>();
    private ArrayList<String> aDate = new ArrayList<>();
    String urlText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_item);
        txtviews = findViewById(R.id.textView);

        NewsQueryTask viewing = new NewsQueryTask();
        viewing.execute();
      //  insertJSON();
        insertinfo();
    }

    private void insertinfo()
    {
        aTitle.add("testing");
        aDescription.add("d testing");
        aDate.add("da testing");

        recyclercall();
    }

    public void recyclercall(){
        recviews = (RecyclerView) findViewById(R.id.news_recyclerview);
        NewsRecyclerViewAdapter news_adp = new NewsRecyclerViewAdapter(aTitle, aDescription, aDate,this);
        recviews.setAdapter(news_adp);
        recviews.setLayoutManager(new LinearLayoutManager(this));
    }



    class NewsQueryTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                txtviews.setText("loading...");
                urlText= NetworkUtils.getResponseFromHttpUrl(NetworkUtils.buildURL());
                JSONObject news = new JSONObject();
                news = new JSONObject(NetworkUtils.getResponseFromHttpUrl(NetworkUtils.buildURL()));
                JsonUtils arts = new JsonUtils();
                ArrayList<NewsItem> newsarray = new ArrayList<>();
                newsarray =arts.parseNews(news);
                Log.e("url things",urlText);
                Log.e("testing",newsarray.get(0).get_Aurthor());

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            txtviews.setText(urlText);


        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem news) {
        int itemThatWasClickedId = news.getItemId();
        if (itemThatWasClickedId == R.id.action_search) {
            NewsQueryTask refresh = new NewsQueryTask();
            refresh.execute();
           // insertJSON();
            return true;
        }
        return super.onOptionsItemSelected(news);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.get_news, menu);
        return true;
    }




}
