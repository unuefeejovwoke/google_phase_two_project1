package com.example.googlephasetwo.models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.googlephasetwo.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class LeadersAdapter extends RecyclerView.Adapter<LeadersAdapter.ViewHolder> {
    ArrayList<LeadersObject> learnersList;
    Context context;

    public LeadersAdapter(ArrayList<LeadersObject> learnersList, Context context){
        this.learnersList = learnersList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LeadersObject learnersObject = learnersList.get(position);
        holder.name.setText(learnersObject.getName());
        holder.country.setText(learnersObject.getCountry());
        holder.learning_hours.setText(learnersObject.getHours());
        Picasso.with(getContext()).load(learnersObject.getBadge()).into(holder.badgeImage);
    }

    private Context getContext() {
        return context;
    }

    @Override
    public int getItemCount() {
        return learnersList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView country;
        public TextView learning_hours;
        public ImageView badgeImage;
        public ViewHolder(View view) {
            super(view);

            name = view.findViewById(R.id.name);
            country = view.findViewById(R.id.country);
            learning_hours = view.findViewById(R.id.learning_hours);
            badgeImage = view.findViewById(R.id.badgeImage);
        }
    }
}
