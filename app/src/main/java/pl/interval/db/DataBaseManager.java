package pl.interval.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import pl.interval.model.Interval;

/**
 * Created by Przemek on 2018-02-01.
 */

public class DataBaseManager extends SQLiteOpenHelper {

    public DataBaseManager(Context context) {
        super(context, DataBaseConstant.DATABASE, null, DataBaseConstant.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DataBaseConstant.createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DataBaseConstant.dropTable);
        onCreate(sqLiteDatabase);
    }

//    public TrainingArrays<Interval> getTraining(int isSelected) {
//        TrainingArrays<Interval> intervalArrayList = new ArrayList<>();
//        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
//        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + DataBaseConstant.TABLE
//                + " WHERE " + DataBaseConstant.IS_SELECTED
//                + " LIKE " + isSelected, null);
//
//        Interval interval;
//
//        while (cursor.moveToNext()) {
//            int id = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(DataBaseConstant.ID)));
//            String training = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseConstant.TRAINING));
//            String description = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseConstant.DESCRIPTION));
//            String image = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseConstant.IMAGE));
//            Integer is_selecte = Integer.valueOf(cursor.getString(cursor.getColumnIndexOrThrow(DataBaseConstant.IS_SELECTED)));
//
//            interval = new Interval();
//            interval.setId(id);
//            interval.setTraining(training);
//            interval.setDescription(description);
//            interval.setThumbnail(image);
//            interval.setSelected(is_selecte);
//
//            intervalArrayList.add(interval);
//        }
//        return intervalArrayList;
//    }

    public void updateTask(Interval interval, int is_selected) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseConstant.IS_SELECTED, is_selected);

        String where = DataBaseConstant.ID + "=?";
        sqLiteDatabase.update(DataBaseConstant.TABLE, contentValues, where, new String[]{String.valueOf(interval.getId())});
    }

//        public void insertTask(TaskModel taskModel) {
//        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(DataBaseConstant.TRAINING, taskModel.getTask());
//        contentValues.put(DataBaseConstant.TIME, taskModel.getTime());
//        contentValues.put(DataBaseConstant.DATE, taskModel.getDate());
//
//        sqLiteDatabase.insert(DataBaseConstant.TABLE, null, contentValues);
//    }

//    public boolean deleteTask(String id) {
//        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        String where = DataBaseConstant.ID + "=?";
//        int result = sqLiteDatabase.delete(DataBaseConstant.TABLE, where, new String[]{id});
//        if (result > 0) {
//            return true;
//        }
//        return false;
//    }

//    public boolean deleteTable(){
//        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
//        String del = "DELETE FROM " + DataBaseConstant.TABLE;
//        sqLiteDatabase.execSQL(del);
//        Log.e("TAG", "Delete table " + del);
//        return true;
//    }
}
