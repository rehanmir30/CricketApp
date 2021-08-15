package com.example.cricketapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cricketapp.Model.Match_details;
import com.example.cricketapp.Model.Old_Matches_Detail;

import java.util.ArrayList;


public class OldMatchesAdapter extends RecyclerView.Adapter<OldMatchesAdapter.ExampleViewHolder> {
    private ArrayList<Old_Matches_Detail> mExampleList;
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

    public OldMatchesAdapter(ArrayList<Old_Matches_Detail> exampleList) {
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
        Old_Matches_Detail currentItem = mExampleList.get(position);


        String team1 = currentItem.getId();
        String team2 = currentItem.getTitle();
        String date = currentItem.getDescription();


        holder.m_versis.setText(team2);
        holder.m_date.setText(team1);
        holder.m_type.setText(date);


    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}
