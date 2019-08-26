package soda.newgrocery.Context;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import soda.newgrocery.GroceryItem;

import soda.newgrocery.GroceryItem;

public class DbContext extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "myGrocery.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + GroceryItem.TABLE_NAME + " (" +
                    GroceryItem.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    GroceryItem.COLUMN_NAME + " TEXT," +
                    GroceryItem.COLUMN_QUANTITY + " INTEGER)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + GroceryItem.TABLE_NAME;


    public DbContext(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);

        Log.d("1", "Creating Database");
        SeedGenerator seedGenerator= new SeedGenerator(sqLiteDatabase);



        Log.d("2", "Finished creating Database");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
        onCreate(sqLiteDatabase);
    }
}
