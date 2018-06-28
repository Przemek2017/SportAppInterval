package pl.interval.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.DrawableContainer;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import pl.interval.R;
import pl.interval.holder.GridHolder;
import pl.interval.model.Interval;

/**
 * Created by Przemek on 2018-03-27.
 */

public class GridAdapter extends RecyclerView.Adapter<GridHolder> {
    private static final String TAG = "GridAdapter";

    private Context context;
    public List<Interval> trainingList;
    /* for multiselect */
    public List<Interval> multiSelectList = new ArrayList<>();

    public GridAdapter(Context context, List<Interval> trainingList, List<Interval> multiSelectList) {
        this.context = context;
        this.trainingList = trainingList;
        this.multiSelectList = multiSelectList;
    }

    @Override
    public GridHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View gridView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_grid, parent, false);
        return new GridHolder(gridView);
    }

    @Override
    public void onBindViewHolder(final GridHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called");
        int id = trainingList.get(position).getId();

        holder.training.setText(trainingList.get(position).getTraining());

        Glide.with(context)
                .load(trainingList.get(position).getThumbnail())
                .into(holder.thumbnail);

        //Multiselect
//        if (multiSelectList.contains(id)){
//            holder.cardView.setForeground(new ColorDrawable(ContextCompat.getColor(context, R.color.colorSelectedItemBackground)));
//            holder.cardView.setCardBackgroundColor(Color.parseColor("#FFC75F"));
//        } else {
//            holder.cardView.setForeground(new ColorDrawable(ContextCompat.getColor(context, android.R.color.transparent)));
//            holder.cardView.setCardBackgroundColor(Color.WHITE);
//        }

    }
//
//    public void setSelectedIds(List<Interval> selectedIds) {
//        this.selectedIds = selectedIds;
//        notifyDataSetChanged();
//    }

    @Override
    public int getItemCount() {
        return trainingList.size();
    }

}
