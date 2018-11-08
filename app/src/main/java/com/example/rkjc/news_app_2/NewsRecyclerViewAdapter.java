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
    private ArrayList<String > aURL = new ArrayList<>();
    private Context aContxt;

    public NewsRecyclerViewAdapter(ArrayList<String> aTitle, ArrayList<String> aDescription, ArrayList<String> aDate, ArrayList<String> aURL, Context aContxt) {
        this.aTitle = aTitle;
        this.aDescription = aDescription;
        this.aDate = aDate;
        this.aURL = aURL;
        this.aContxt = aContxt;
    }

    @Override
    public NewsRecyclerViewAdapter.NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        View viewing = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item,parent,false);
        NewsViewHolder newshold = new NewsViewHolder(viewing);
        return newshold;
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, final int position)
    {

        holder.title.setText("Title: " + aTitle.get(position));
        holder.description.setText("Description: "+aDescription.get(position));
        holder.date.setText("Date: "+aDate.get(position));
        holder.playout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent clicked = new Intent(Intent.ACTION_VIEW,Uri.parse(aURL.get(position)));
                aContxt.startActivity(clicked);
            }
        });

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

        public NewsViewHolder(View txt) {
            super(txt);
            title = itemView.findViewById(R.id.Title_txt);
            description = itemView.findViewById(R.id.Description_txt);
            date = itemView.findViewById(R.id.Date_txt);
            playout = itemView.findViewById(R.id.playout);
        }
    }
}

