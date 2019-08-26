package soda.newgrocery.Adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import soda.newgrocery.GroceryItem;
import soda.newgrocery.R;

public class GroceryItemAdapter extends RecyclerView.Adapter<GroceryItemAdapter.MyViewHolder> {


    private List<GroceryItem> groceryItemList;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView nameTextView;
        public TextView quantityTextView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView=(TextView) itemView.findViewById(R.id.grocery_item_name);
            quantityTextView=(TextView) itemView.findViewById(R.id.grocery_item_quantity);

        }
    }
    public GroceryItemAdapter(List<GroceryItem> groceryItemList){
        this.groceryItemList= groceryItemList;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.grocery_item_row, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder viewHolder, int i) {
        GroceryItem groceryItem= groceryItemList.get(i);

        viewHolder.nameTextView.setText(groceryItem.getName());
        viewHolder.quantityTextView.setText(groceryItem.getQuantity());


    }



    @Override
    public int getItemCount() {
        return groceryItemList.size();
    }
}

