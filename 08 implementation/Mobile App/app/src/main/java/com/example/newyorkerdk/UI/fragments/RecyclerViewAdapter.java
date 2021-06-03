package com.example.newyorkerdk.UI.fragments;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.newyorkerdk.R;
import com.example.newyorkerdk.entities.Wall;
import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Activity context;
    ArrayList<Wall> wallArrayList;

    public RecyclerViewAdapter(Activity context, ArrayList<Wall> wallArrayList) {
        this.context = context;
        this.wallArrayList = wallArrayList;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View rootView = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new RecyclerViewViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Wall wall = wallArrayList.get(position);
        RecyclerViewViewHolder viewHolder= (RecyclerViewViewHolder) holder;
        viewHolder.note_textfield.setText(String.valueOf(wall.getName()));
        viewHolder.price_textfield.setText(context.getString(R.string.price, String.valueOf(wall.getPrice())));
    }

    @Override
    public int getItemCount() {
        return wallArrayList.size();
    }

    static class RecyclerViewViewHolder extends RecyclerView.ViewHolder {
        TextView note_textfield;
        TextView price_textfield;

        public RecyclerViewViewHolder(@NonNull View itemView) {
            super(itemView);
            note_textfield = itemView.findViewById(R.id.wall_note_textview);
            price_textfield = itemView.findViewById(R.id.wall_price_textview);
        }
    }
}
