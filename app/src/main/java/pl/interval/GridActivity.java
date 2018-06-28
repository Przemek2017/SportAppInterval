package pl.interval;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ActionMode;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pl.interval.adapter.GridAdapter;
import pl.interval.model.Interval;
import pl.interval.utils.RecyclerItemTouch;

public class GridActivity extends AppCompatActivity {
    private static final String TAG = "GridActivity";

    private long onBackPressedTime;
    int toastDuration = 2000;
    public static final int COLUMN_NUM = 3;

    private GridAdapter gridAdapter;
    private RecyclerView recyclerView;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private List<Interval> intervalList = TrainingArrays.getIntervalList();

    ActionMode actionMode;
    boolean isMultiSelect = false;
    private List<Interval> multiSelectList = new ArrayList<>();
    Menu context_menu;

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_grid, menu);
//        return super.onCreateOptionsMenu(menu);
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//
//        switch (id) {
//            case R.id.action_add:
//                Toast.makeText(this, "Click", Toast.LENGTH_SHORT).show();
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate: ");
        setContentView(R.layout.activity_grid);

        gridAdapter = new GridAdapter(getApplicationContext(), intervalList, multiSelectList);
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(COLUMN_NUM, StaggeredGridLayoutManager.VERTICAL);

        recyclerView = findViewById(R.id.grid_recycler_view);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setAdapter(gridAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerItemTouch(
                GridActivity.this, recyclerView, new RecyclerItemTouch.OnItemListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.i(TAG, "onItemClick: " + position);
                if (isMultiSelect) {
                    multiSelect(view, position);
                } else {
                    Log.i(TAG, "onItemClick: INTENT");
                    Intent intent = new Intent(GridActivity.this, ItemViewActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra(IntentFlags.TRAINING, TrainingArrays.getIntervalList().get(position).getTraining());
                    intent.putExtra(IntentFlags.DESCRIPTION, TrainingArrays.getIntervalList().get(position).getDescription());
                    intent.putExtra(IntentFlags.THUMBNAIL, TrainingArrays.getIntervalList().get(position).getThumbnail());
                    startActivity(intent);
                }
            }

            @Override
            public void onLongItemClick(View view, int position) {
                Log.i(TAG, "onLongItemClick: " + position);
                if (!isMultiSelect) {
                    multiSelectList = new ArrayList<>();
                    isMultiSelect = true;

                    if (actionMode == null) {
                        actionMode = startActionMode(mActionModeCallback);
                    }
                    multiSelect(view, position);
                }
//                final CardView cardView = view.findViewById(R.id.parent_card_view);
//
//                if (cardView.getCardBackgroundColor().getDefaultColor() == -1){
//                    cardView.setCardBackgroundColor(Color.parseColor("#FFC75F"));
//                } else {
//                    cardView.setCardBackgroundColor(Color.WHITE);
//                }
            }
        }));


    }


    public void multiSelect(View view, int position) {
        Log.i(TAG, "multiSelect: ");
        final CardView cardView = view.findViewById(R.id.parent_card_view);

        if (actionMode != null) {
            if (multiSelectList.contains(intervalList.get(position))) {
                multiSelectList.remove(intervalList.get(position));
                cardView.setCardBackgroundColor(Color.WHITE);
            } else {
                multiSelectList.add(intervalList.get(position));
                cardView.setCardBackgroundColor(Color.parseColor("#FFC75F"));
            }

//
//            if (cardView.getCardBackgroundColor().getDefaultColor() == -1) {
//                cardView.setCardBackgroundColor(Color.parseColor("#FFC75F"));
//            } else {
//                cardView.setCardBackgroundColor(Color.WHITE);
//            }


            checkMultiSelectListSize();
        }
        refreshAdapter();
    }

    private void checkMultiSelectListSize() {
        Log.i(TAG, "checkMultiSelectListSize: ");
        if (multiSelectList.size() >= 0) {
            actionMode.setTitle("" + multiSelectList.size());
        }
//        else {
//            actionMode.setTitle("");
//        }
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onBackPressed() {
        if (onBackPressedTime + toastDuration > System.currentTimeMillis()) {
            super.onBackPressed();
        } else {
            Toast.makeText(getApplicationContext(), "Aby wyjść naciśnij jeszcze raz", toastDuration).show();
        }
        onBackPressedTime = System.currentTimeMillis();
    }

    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            Log.i(TAG, "onCreateActionMode: ");
            //checkMultiSelectListSize();
            isMultiSelect = true;
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.menu_grid, menu);
            context_menu = menu;
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            Log.i(TAG, "onPrepareActionMode: ");
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {

            Log.i(TAG, "onActionItemClicked: ");

            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < multiSelectList.size(); i++){
                stringBuilder.append("\n" + multiSelectList.get(i).training);
            }
            switch (item.getItemId()) {
                case R.id.action_add:
                    Toast.makeText(getApplicationContext(), "onActionItemClicked " + stringBuilder, Toast.LENGTH_SHORT).show();
                    return true;
                default:
            return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            Log.i(TAG, "onDestroyActionMode: ");
//            //mActionModeCallback = null;
            isMultiSelect = false;
            actionMode = null;
            multiSelectList = new ArrayList<>();

//            multiSelectList = new ArrayList<Interval>();
//            refreshAdapter();
        }
    };

    private void refreshAdapter() {
        Log.i(TAG, "refreshAdapter: ");
//        gridAdapter.selectedIds = multiSelectList;
//        gridAdapter.trainingList = TrainingArrays.getIntervalList();
        gridAdapter.notifyDataSetChanged();
    }
}
