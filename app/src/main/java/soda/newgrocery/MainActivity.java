package soda.newgrocery;


import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;


import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import soda.newgrocery.Adapters.GroceryItemAdapter;
import soda.newgrocery.Context.DbContext;
import soda.newgrocery.Listeners.RecyclerTouchListener;
import soda.newgrocery.Repository.GroceryItemRepository;

public class MainActivity extends BaseActivity {
    private List<GroceryItem> groceryItemList= new ArrayList<GroceryItem>();
    private RecyclerView recyclerView;
    private GroceryItemAdapter groceryItemAdapter;
    private DbContext db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        db= new DbContext(this);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(getApplicationContext(), AddGroceryItemActivity.class);
                startActivity(intent);

            }
        });


        // groceryItemList.add(new GroceryItem("Product 1", "2"));
        // groceryItemList.add(new GroceryItem("Product 2", "2"));
        // groceryItemList.add(new GroceryItem("Product 3", "2"));
        //dbContext= new DbContext(getApplicationContext());



        //LoadGroceries();
    }

    private void PopulateGroceryItems() {
        groceryItemList= new ArrayList<GroceryItem>();
        recyclerView=(RecyclerView) findViewById((R.id.myRecyclerView));
        groceryItemAdapter= new GroceryItemAdapter(groceryItemList);
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(groceryItemAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                GroceryItem groceryItem = groceryItemList.get(position);
                String message= groceryItem.getName() + ": " + groceryItem.getQuantity();
                //Snackbar.make(view,message, Snackbar.LENGTH_LONG).setAction("Action", null).show();

                Intent intent= new Intent(getApplicationContext(), ViewActivity.class);
                intent.putExtra("GroceryItemName", groceryItem);
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        GroceryItemRepository groceryItemRepository= new GroceryItemRepository(db);
        for (GroceryItem item: groceryItemRepository.GetAll()){

            groceryItemList.add(item);
        }

        groceryItemAdapter.notifyDataSetChanged();
    }

    private void LoadGroceries() {

        ScrollView scrollView= (ScrollView)findViewById(R.id.scrollView);

        for(int i=0;i>10;i++){
            TextView textView= new TextView(this);
            textView.setText("TextBox" + i);
            scrollView.addView(textView);

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        db= new DbContext(this);
        Log.d("Resume","Resumed");
        PopulateGroceryItems();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void viewItem(View view) {
        Intent intent = new Intent(this, ViewActivity.class);
        startActivity(intent);
    }
}
