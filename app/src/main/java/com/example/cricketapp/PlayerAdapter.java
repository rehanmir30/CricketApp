package com.example.cricketapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cricketapp.Model.Old_Matches_Detail;
import com.example.cricketapp.Model.Player;

import java.util.ArrayList;


public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.ExampleViewHolder> {
    private ArrayList<Player> mExampleList;
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

    public PlayerAdapter(ArrayList<Player> exampleList) {
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
        Player currentItem = mExampleList.get(position);


        String name = currentItem.getName();

        String full_name = currentItem.getFull_name();


        holder.m_versis.setText(name);
        holder.m_type.setText(full_name);


    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}
