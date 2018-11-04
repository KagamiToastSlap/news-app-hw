package com.example.rkjc.news_app_2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class NewsRecyclerViewAdapter  extends RecyclerView.Adapter<NewsRecyclerViewAdapter.NewsViewHolder> {

    private ArrayList<String> aTitle = new ArrayList<>();
    private ArrayList<String> aDescription = new ArrayList<>();
    private ArrayList<String> aDate = new ArrayList<>();
    private Context aContxt;

    public NewsRecyclerViewAdapter(ArrayList<String> aTitle, ArrayList<String> aDescription, ArrayList<String> aDate, Context aContxt) {
        this.aTitle = aTitle;
        this.aDescription = aDescription;
        this.aDate = aDate;
        this.aContxt = aContxt;
    }

    public NewsRecyclerViewAdapter (String[]newsData){
        newsData = newsData;
    }
    @Override
    public NewsRecyclerViewAdapter.NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        View viewing = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main,parent,false);
        NewsViewHolder newshold = new NewsViewHolder(viewing);
        return newshold;
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position)
    {
        holder.title.setText(aTitle.get(position));
        holder.description.setText(aDescription.get(position));
        holder.date.setText(aDate.get(position));
        holder.playout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.title.setText("testing");
    }
    @Override
    public int getItemCount()
    {
       return aTitle.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView description;
        TextView date;
        LinearLayout playout;
        public View mtxtview;

        public NewsViewHolder(View txt) {
            super(txt);
            title = itemView.findViewById(R.id.Title_txt);
            description = itemView.findViewById(R.id.Description_txt);
            date = itemView.findViewById(R.id.Date_txt);
            playout = itemView.findViewById(R.id.playout);
            mtxtview = txt;
        }
    }

}

