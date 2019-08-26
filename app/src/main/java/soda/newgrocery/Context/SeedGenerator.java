package soda.newgrocery.Context;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import soda.newgrocery.GroceryItem;
import soda.newgrocery.Repository.GroceryItemRepository;
import soda.newgrocery.Repository.IRepository;

import soda.newgrocery.GroceryItem;

public class SeedGenerator {
    private SQLiteDatabase context;
    public SeedGenerator(SQLiteDatabase context) {
        this.context= context;
        SeedGroceryItems();
    }

    private void SeedGroceryItems() {
        IRepository<GroceryItem> groceryItemRepository= new GroceryItemRepository();
        groceryItemRepository.Insert(new GroceryItem("Product 1", "2"),false, context);
        groceryItemRepository.Insert(new GroceryItem("Product 2", "2"),false, context);
        groceryItemRepository.Insert(new GroceryItem("Product 3", "2"),false,context);


    }
}
