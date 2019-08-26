package soda.newgrocery;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import androidx.appcompat.app.ActionBar;

import java.io.Serializable;

import soda.newgrocery.Context.DbContext;
import soda.newgrocery.Repository.GroceryItemRepository;

public class ViewActivity extends BaseActivity {
    GroceryItem groceryItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        ActionBar actionBar= getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        groceryItem= (GroceryItem)getIntent().getSerializableExtra("GroceryItemName");

        TextView viewName= (TextView) findViewById(R.id.view_name);
        viewName.setText(groceryItem.getName());;
        TextView viewQuantity= (TextView) findViewById(R.id.view_quantity);
        viewQuantity.setText(groceryItem.getQuantity());
    }

    public void EditItem(View view) {

        String message= groceryItem.getName() + ": " + groceryItem.getQuantity();
        //Snackbar.make(view,message, Snackbar.LENGTH_LONG).setAction("Action", null).show();

        Intent intent= new Intent(getApplicationContext(), AddGroceryItemActivity.class);
        intent.putExtra("GroceryItemName", groceryItem);
        intent.putExtra("isEdit", true);
        startActivity(intent);

    }

    public void DeleteItem(View view) {
        DbContext db= new DbContext(this);
        GroceryItemRepository groceryItemRepository= new GroceryItemRepository(db);

        groceryItemRepository.Delete(groceryItem);

        Intent intent= new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}
