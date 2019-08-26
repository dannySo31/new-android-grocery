package soda.newgrocery;



import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.ActionBar;

import soda.newgrocery.Context.DbContext;
import soda.newgrocery.Repository.GroceryItemRepository;

public class AddGroceryItemActivity extends BaseActivity {
    private DbContext db;
    private long entityId;
    private boolean operation;
    public AddGroceryItemActivity() {
        this.db = new DbContext(this);
        entityId=0;
        operation= false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_grocery_item);
        Intent intent= getIntent();

        if(intent!=null){
            Button operationButton=(Button) findViewById(R.id.btn_operation);
            operation= (Boolean) intent.getBooleanExtra("isEdit",false);
            Log.d("Operation",""+ operation);
            if (!operation){

                operationButton.setText(R.string.add_item_btn);
                setTitle(R.string.add_grocery_item);
                entityId=0;

            }else{
                GroceryItem item= (GroceryItem)intent.getSerializableExtra("GroceryItemName");
                operationButton.setText(R.string.update_item_btn);
                setTitle(R.string.update_item_btn);
                entityId= item.getId();
                EditText titleText=(EditText)findViewById(R.id.label_text);
                titleText.setText(item.getName());
                EditText quantityEditText= (EditText)findViewById(R.id.quanity_text);
                quantityEditText.setText(item.getQuantity());
            }
        }


        ActionBar actionBar= getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if (id == R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void addBook(View view) {
        EditText titleText=(EditText)findViewById(R.id.label_text);
        String title= titleText.getText().toString();
        EditText quantityEditText= (EditText)findViewById(R.id.quanity_text);
        String quantity= quantityEditText.getText().toString();

        GroceryItemRepository groceryItemRepository= new GroceryItemRepository(db);
        GroceryItem groceryItem= new GroceryItem();
        groceryItem.setId(entityId);
        groceryItem.setName(title);
        groceryItem.setQuantity(quantity);
        if(operation){
            groceryItemRepository.Update(groceryItem);
        }else{
            groceryItemRepository.Insert(groceryItem,true);
        }



        Intent intent= new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}
