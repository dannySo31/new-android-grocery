package soda.newgrocery.Repository;



import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import soda.newgrocery.Context.DbContext;
import soda.newgrocery.GroceryItem;

public class GroceryItemRepository implements IRepository<GroceryItem> {

    private DbContext context;
    public GroceryItemRepository(DbContext context) {
        this.context= context;

    }
    public GroceryItemRepository(){

    }

    @Override
    public List<GroceryItem> GetAll() {
        List<GroceryItem> notes = new ArrayList<>();
        SQLiteDatabase db= context.getReadableDatabase();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + GroceryItem.TABLE_NAME + " ORDER BY " +
                GroceryItem.COLUMN_ID + " DESC";


        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                GroceryItem note = new GroceryItem();
                note.setId(cursor.getInt(cursor.getColumnIndex(GroceryItem.COLUMN_ID)));
                note.setName(cursor.getString(cursor.getColumnIndex(GroceryItem.COLUMN_NAME)));
                note.setQuantity(cursor.getString(cursor.getColumnIndex(GroceryItem.COLUMN_QUANTITY)));

                notes.add(note);
                Log.d("Select", note.getName());
            } while (cursor.moveToNext());
        }

        // close db connection
        context.close();

        // return notes list
        return notes;
    }

    @Override
    public long Update(GroceryItem entity) {

        SQLiteDatabase db;

        db = context.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(GroceryItem.COLUMN_NAME, entity.getName());
        values.put(GroceryItem.COLUMN_QUANTITY, entity.getQuantity());
        values.put(GroceryItem.COLUMN_ID, entity.getId());
        // updating row
        long id= db.update(GroceryItem.TABLE_NAME, values, GroceryItem.COLUMN_ID + " = ?",
                new String[]{String.valueOf(entity.getId())});
        context.close();

        return  id;

    }

    @Override
    public long Insert(GroceryItem entity, boolean closeContext) {

        SQLiteDatabase db;

        db = context.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(GroceryItem.COLUMN_NAME, entity.getName());
        values.put(GroceryItem.COLUMN_QUANTITY, entity.getQuantity());
        // insert row
        long id = db.insert(GroceryItem.TABLE_NAME, null, values);
        Log.d("et", "Inserted:" + id);
        // close db connection
        if(closeContext)
            context.close();
        // return newly inserted row id
        return id;
    }

    @Override
    public void Delete(GroceryItem entity) {
        SQLiteDatabase db;
        db = context.getWritableDatabase();
        db.delete(GroceryItem.TABLE_NAME, GroceryItem.COLUMN_ID + " = ?",
                new String[]{String.valueOf(entity.getId())});
        db.close();
    }

    @Override
    public long Insert(GroceryItem entity, boolean closeContext, SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(GroceryItem.COLUMN_NAME, entity.getName());
        values.put(GroceryItem.COLUMN_QUANTITY, entity.getQuantity());
        // insert row
        long id = db.insert(GroceryItem.TABLE_NAME, null, values);
        Log.d("et", "Inserted:" + id);
        // close db connection
        if(closeContext)
            context.close();
        // return newly inserted row id
        return id;
    }

    @Override
    public GroceryItem Get(long id) {
        // get readable database as we are not inserting anything

        SQLiteDatabase db= context.getReadableDatabase();
        Cursor cursor = db.query(GroceryItem.TABLE_NAME,
                new String[]{GroceryItem.COLUMN_ID, GroceryItem.COLUMN_NAME, GroceryItem.COLUMN_QUANTITY},
                GroceryItem.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare note object
        GroceryItem item = new GroceryItem();

        item.setId(cursor.getLong(cursor.getColumnIndex(GroceryItem.COLUMN_ID)));
        item.setName(cursor.getString(cursor.getColumnIndex(GroceryItem.COLUMN_NAME)));
        item.setQuantity(cursor.getString(cursor.getColumnIndex(GroceryItem.COLUMN_QUANTITY)));

        // close the db connection
        cursor.close();

        return item;
    }

    @Override
    public void Close() {
        context.close();
    }
}

