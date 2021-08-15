package com.example.cricketapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cricketapp.Model.Match_details;


import java.util.ArrayList;


public class NewMatchesAdapter extends RecyclerView.Adapter<NewMatchesAdapter.ExampleViewHolder> {
    private ArrayList<Match_details> mExampleList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {

        public TextView m_versis;
        public TextView m_type;
        public TextView m_date;

        public ExampleViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            m_versis = itemView.findViewById(R.id.versis);
            m_type = itemView.findViewById(R.id.type);
            m_date = itemView.findViewById(R.id.date);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public NewMatchesAdapter(ArrayList<Match_details> exampleList) {
        mExampleList = exampleList;
    }

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.matches_layout_list, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v, mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        Match_details currentItem = mExampleList.get(position);


        String team1 = currentItem.getM_team1();
        String team2 = currentItem.getM_team2();
        String date = currentItem.getM_date();
        String[] separated = date.split("T");

        holder.m_versis.setText(team1 + "  VS  " + team2);
        holder.m_date.setText(separated[0]);
        if (!currentItem.getM_winner_team().isEmpty()) {
            holder.m_type.setText("Winner Team: " + currentItem.getM_winner_team());
        } else {
            holder.m_type.setText("Winner Team: Not Decided yet");
        }


    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}
