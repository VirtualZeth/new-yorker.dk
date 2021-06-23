package com.example.newyorkerdk.UI.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.newyorkerdk.R;
import com.example.newyorkerdk.UI.fragments.BasketFragment;
import com.example.newyorkerdk.entities.Addition;
import com.example.newyorkerdk.entities.Wall;
import java.util.ArrayList;
import java.util.List;

/**
 * Adapter som er ansvarlig for at håndtere enkelte vægge i recyclerviewet i {@link BasketFragment}
 * @author Usamah
 * @author Mike
 */
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
        StringBuilder additionsTextBuilder = new StringBuilder();
        for (Addition addition: wall.getListOfAdditions()) {
            additionsTextBuilder.append(addition.simpleToString());
        }
        viewHolder.note_textfield.setText(String.valueOf(wall.getName()));
        viewHolder.price_textfield.setText(context.getString(R.string.price, String.valueOf(wall.getPrice())));
        viewHolder.wall_width_textfield.setText(context.getString(R.string.width_basket, String.valueOf(wall.getHeight())));
        viewHolder.wall_height_textfield.setText(context.getString(R.string.height_basket, String.valueOf(wall.getWidth())));
        viewHolder.wall_fields_height_textfield.setText(context.getString(R.string.fields_height_basket,
                String.valueOf(wall.getNumberOfGlassFieldsHeight())));
        viewHolder.wall_fields_width_textfield.setText(context.getString(R.string.fields_width_basket,
                String.valueOf(wall.getNumberOfGlassFieldsWidth())));
        viewHolder.wallAdditions_textView.setText(additionsTextBuilder);
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
        TextView wall_width_textfield;
        TextView wall_height_textfield;
        TextView wall_fields_height_textfield;
        TextView wall_fields_width_textfield;
        TextView wallAdditions_textView;
        OnWallListener listener;

        public RecyclerViewViewHolder(@NonNull View itemView, OnWallListener listener) {
            super(itemView);
            this.listener = listener;
            note_textfield = itemView.findViewById(R.id.wall_note_textview);
            price_textfield = itemView.findViewById(R.id.wall_price_textview);
            delete_button = itemView.findViewById(R.id.recycler_delete);
            wall_width_textfield = itemView.findViewById(R.id.wall_width_textfield);
            wall_height_textfield = itemView.findViewById(R.id.wall_height_textfield);
            wall_fields_height_textfield = itemView.findViewById(R.id.wall_fields_height);
            wall_fields_width_textfield = itemView.findViewById(R.id.wall_fields_width);
            wallAdditions_textView = itemView.findViewById(R.id.additions_textfield);
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
