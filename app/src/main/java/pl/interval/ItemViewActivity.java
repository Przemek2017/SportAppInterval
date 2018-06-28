package pl.interval;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;


/**
 * Created by Przemek on 2018-03-27.
 */

public class ItemViewActivity extends AppCompatActivity {

    private static final String TAG = "ItemViewActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_view);
        Log.d(TAG, "onCreate: started");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getIncomingIntent();
    }

    private void getIncomingIntent(){
        Log.d(TAG, "getIncomingIntent: init");
        if((getIntent().hasExtra(IntentFlags.TRAINING))
                && (getIntent().hasExtra(IntentFlags.DESCRIPTION))
                && (getIntent().hasExtra(String.valueOf(IntentFlags.THUMBNAIL)))){
            String training = getIntent().getStringExtra(IntentFlags.TRAINING);
            String description = getIntent().getStringExtra(IntentFlags.DESCRIPTION);
            int thumbnail = getIntent().getIntExtra(IntentFlags.THUMBNAIL, 1);

            init(training, description, thumbnail);
        }
    }

    private void init(String training, String description, int thumbnail){
        TextView trainingItem = findViewById(R.id.item_training_view);
        trainingItem.setText(training);

        TextView descriptionItem = findViewById(R.id.item_description_view);
        descriptionItem.setText(description);

        ImageView thumbnailItem = findViewById(R.id.item_image_view);
        Glide.with(getApplicationContext())
                .load(thumbnail)
                .into(thumbnailItem);
    }

}
