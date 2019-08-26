package soda.newgrocery;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class GroceryItem  extends BaseClass {

    public static final String TABLE_NAME = "GroceryItem";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_QUANTITY = "quantity";

    public GroceryItem(){

        this.name="";
        this.quantity="";
    }
    public GroceryItem(String name, String quantity){
        this.name=name;
        this.quantity=quantity;
    }

    private long id;

    public long getId() { return this.id; }
    public void setId(long id) { this.id = id; }
    private String name;

    public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }

    private String quantity;

    public String getQuantity() { return this.quantity; }
    public void setQuantity(String quantity) { this.quantity =quantity; }
    private int mData;


    public static final Parcelable.Creator<GroceryItem> CREATOR = new Parcelable.Creator<GroceryItem>() {
        public GroceryItem createFromParcel(Parcel in) {
            return new GroceryItem(in);
        }

        public GroceryItem[] newArray(int size) {
            return new GroceryItem[size];
        }
    };

    private GroceryItem(Parcel in) {
        mData = in.readInt();
    }
}
