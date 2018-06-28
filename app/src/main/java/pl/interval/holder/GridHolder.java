package pl.interval.holder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import pl.interval.R;

/**
 * Created by Przemek on 2018-03-30.
 */

public class GridHolder extends RecyclerView.ViewHolder{

    public TextView training;
    public ImageView thumbnail;
    public CardView cardView;

    public GridHolder(View itemView) {
        super(itemView);

        training = itemView.findViewById(R.id.text_view_grid);
        thumbnail = itemView.findViewById(R.id.image_view_grid);
        cardView = itemView.findViewById(R.id.parent_card_view);
    }
}
