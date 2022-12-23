package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import java.util.List;

public class Recycler_Adapter extends
        RecyclerView.Adapter<Recycler_Adapter.ViewHolder> {
        public class ViewHolder extends RecyclerView.ViewHolder {

            public TextView name;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);

                this.name = (TextView) itemView.findViewById(R.id.nameView);
                this.name = (TextView) itemView.findViewById(R.id.countryView);
            }
        }

    private List<Official> mOfficial;

    public Recycler_Adapter (List<Official> person) {mOfficial = person;}

    @NonNull
    @Override
    public Recycler_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_person, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Recycler_Adapter.ViewHolder holder, int position) {
        // Get the data model based on position
        Official c = mOfficial.get(position);

        // Set item views based on your views and data model
        TextView textView1 = holder.name;
        textView1.setText(c.position_name);

        TextView textView2 = holder.name;
        textView2.setText(c.people_name);


    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mOfficial.size();
    }
}
