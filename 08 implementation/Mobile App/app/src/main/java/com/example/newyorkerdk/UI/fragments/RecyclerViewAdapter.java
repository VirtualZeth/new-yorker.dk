package com.example.newyorkerdk.UI.fragments;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.newyorkerdk.R;
import com.example.newyorkerdk.entities.Wall;
import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Activity context;
    ArrayList<Wall> wallArrayList;
    private final OnWallListener listener;


    public RecyclerViewAdapter(Activity context, List<Wall> wallArrayList, OnWallListener listener) {
        this.context = context;
        this.wallArrayList = (ArrayList<Wall>) wallArrayList;
        this.listener = listener;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View rootView = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new RecyclerViewViewHolder(rootView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Wall wall = wallArrayList.get(position);
        RecyclerViewViewHolder viewHolder= (RecyclerViewViewHolder) holder;
        viewHolder.note_textfield.setText(String.valueOf(wall.getName()));
        viewHolder.price_textfield.setText(context.getString(R.string.price, String.valueOf(wall.getPrice())));

        viewHolder.note_textfield.setOnClickListener(v -> viewHolder.note_textfield.setText("hi"));
    }



    @Override
    public int getItemCount() {
        return wallArrayList.size();
    }
    public interface OnWallListener {
        void onClick(int position, String tag);
    }
    static class RecyclerViewViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView note_textfield;
        TextView price_textfield;
        ImageView delete_button;
        OnWallListener listener;


        public RecyclerViewViewHolder(@NonNull View itemView, OnWallListener listener) {
            super(itemView);
            this.listener = listener;
            note_textfield = (TextView) itemView.findViewById(R.id.wall_note_textview);
            price_textfield = (TextView) itemView.findViewById(R.id.wall_price_textview);
            delete_button = (ImageView) itemView.findViewById(R.id.recycler_delete);
            itemView.setTag("wallItem");
            delete_button.setTag("delete");
            price_textfield.setOnClickListener(this);
            itemView.setOnClickListener(this);
            delete_button.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            listener.onClick(getAdapterPosition(), v.getTag().toString());
        }
    }
}
