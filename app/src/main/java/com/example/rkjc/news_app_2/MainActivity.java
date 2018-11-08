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
    private RecyclerView recviews;
    private ArrayList<String> aTitle = new ArrayList<>();
    private ArrayList<String> aDescription = new ArrayList<>();
    private ArrayList<String> aDate = new ArrayList<>();
    private ArrayList<String> aURL = new ArrayList<>();
    private JSONObject news = new JSONObject();
    private JsonUtils arts = new JsonUtils();
    private  ArrayList<NewsItem> newsarray = new ArrayList<>();
    String urlText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NewsQueryTask viewing = new NewsQueryTask();
        viewing.execute();
        insertinfo();
    }

    private void insertinfo()
    {
        for(int i = 0; i < newsarray.size(); i++)
        {
            aTitle.add(newsarray.get(i).getTitle());
            aDescription.add(newsarray.get(i).getDescription());
            aDate.add(newsarray.get(i).get_published());
            aURL.add(newsarray.get(i).getUrl());
        }

        recyclercall();
    }

    public void recyclercall(){
        recviews = (RecyclerView) findViewById(R.id.news_recyclerview);
        NewsRecyclerViewAdapter news_adp = new NewsRecyclerViewAdapter(aTitle, aDescription, aDate, aURL,this);
        recviews.setAdapter(news_adp);
        recviews.setLayoutManager(new LinearLayoutManager(this));
    }



    class NewsQueryTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                urlText= NetworkUtils.getResponseFromHttpUrl(NetworkUtils.buildURL());
                insertJSON();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem news) {
        int itemThatWasClickedId = news.getItemId();
        if (itemThatWasClickedId == R.id.action_search) {
            NewsQueryTask refresh = new NewsQueryTask();
            insertinfo();
            refresh.execute();
            return true;
        }
        return super.onOptionsItemSelected(news);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.get_news, menu);
        return true;
    }

    public void insertJSON()
    {
        try {
            news = new JSONObject(NetworkUtils.getResponseFromHttpUrl(NetworkUtils.buildURL()));
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        newsarray =arts.parseNews(news.toString());
    }


}
