package com.example.streetartgallery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {

    private ArrayList<artist_data> artistDataArrayList;
    private Context context;
    private View.OnClickListener RecycleAdapterListener;

    public RecycleAdapter(ArrayList<artist_data> trainDataList, Context context) {
        this.artistDataArrayList = artistDataArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecycleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.artist_list_items, parent, false);

        return new RecycleAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.artistId.setText("Artist ID: "+artistDataArrayList.get(position).getArtist_id());
        holder.artistFname.setText("First Name: "+artistDataArrayList.get(position).getArtist_fname());
        holder.artistLname.setText("Last Name: "+artistDataArrayList.get(position).getArtist_lname());


    }

    public void setOnClickListener(View.OnClickListener clickListener) {

        RecycleAdapterListener = clickListener;

    }

    @Override
    public int getItemCount() {
        return artistDataArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        TextView artistId,artistFname,artistLname;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            artistId = itemView.findViewById(R.id.artist_id);
            artistFname = itemView.findViewById(R.id.fname);
            artistLname = itemView.findViewById(R.id.lname);


            itemView.setTag(this);

            itemView.setOnClickListener(RecycleAdapterListener);

        }
    }


}
