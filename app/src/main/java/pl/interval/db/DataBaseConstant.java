package pl.interval.db;

/**
 * Created by Przemek on 2018-02-01.
 */

public class DataBaseConstant {

    public static final String DATABASE = "interval.db";
    public static final String TABLE = "table";
    public static final String ID = "id";
    public static final String TRAINING = "training";
    public static final String DESCRIPTION = "description";
    public static final String IMAGE = "image";
    public static final String IS_SELECTED = "is_selected";
    public static final String TEXT_NOT_NULL = "TEXT NOT NULL";

    public static final int VERSION = 1;

    public static final String createTable = "CREATE TABLE " + TABLE + " ("
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TRAINING + " " + TEXT_NOT_NULL + ", "
            + DESCRIPTION + " " + TEXT_NOT_NULL + ", "
            + IMAGE + " " + TEXT_NOT_NULL + ", "
            + IS_SELECTED + " " + TEXT_NOT_NULL + ");";

    public static final String dropTable = "DROP TABLE IF EXISTS " + TABLE + ";";
}
