package com.example.nitikaaggarwal.usernotes;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by nitikaaggarwal on 05/07/16.
 */
public class ListShowAdapter extends RecyclerView.Adapter<ListShowAdapter.ItemViewHolder> implements View.OnClickListener {
    private OnItemClickListener mItemClickListener;
    public List<Note> noteList;

    @Override
    public void onClick(View v) {
        int position = (Integer) v.getTag();
        (mItemClickListener).onItemClick(noteList.get(position));


    }

    public static interface OnItemClickListener {
        public void onItemClick(Note festival);

    }

    public ListShowAdapter(OnItemClickListener itemClickListener, List<Note> noteList) {
        this.noteList = noteList;
        this.mItemClickListener = itemClickListener;

    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        public TextView mTitle;
        public TextView mDescription;
        public View rowFestival;


        public ItemViewHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.title_list);
            mDescription = (TextView) itemView.findViewById(R.id.description_list);
            rowFestival = (View) itemView.findViewById(R.id.row_list);
        }
    }

    @Override
    public ListShowAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_recycler_view, parent, false);

        return new ItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ListShowAdapter.ItemViewHolder holder, int position) {
        Note note = noteList.get(position);
        holder.rowFestival.setTag(position);
        holder.rowFestival.setOnClickListener(this);

        holder.mTitle.setText(note.getTitle());
        holder.mDescription.setText(note.getDescription());
    }

    @Override
    public int getItemCount() {

        return null != noteList ? noteList.size() : 0;
    }


}