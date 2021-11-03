package org.guanqwq.productexhibition;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import org.guanqwq.productexhibition.model.Product;
import org.guanqwq.productexhibition.model.Type;

public class ProductDatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "shopping";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "products";

    public static final String KEY_PRODUCT_ID = "_id";
    public static final String KEY_PRODUCT_NAME = "name";
    public static final String KEY_PRODUCT_DESCRIPTION = "description";
    public static final String KEY_PRODUCT_PRICE = "price";
    public static final String KEY_PRODUCT_IMAGE = "img_id";

    public ProductDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = String.format(
                "CREATE TABLE %s(" +
                        "%s INTEGER PRIMARY KEY AUTO_INCREMENT," +
                        "%s VARCHAR," +
                        "%s TEXT," +
                        "%s DOUBLE," +
                        "%s INTEGER)",
                TABLE_NAME, KEY_PRODUCT_ID, KEY_PRODUCT_NAME,
                KEY_PRODUCT_DESCRIPTION, KEY_PRODUCT_PRICE, KEY_PRODUCT_IMAGE);
        db.execSQL(CREATE_TABLE);

        for (Type type : DataSource.types) {
            for (Product p : type.getProducts()) {
                insertProduct(db, null, p.getName(), p.getPrice(), p.getImgID(), p.getDescription());
            }
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertProduct(SQLiteDatabase db, Integer id, String name, double price, int imgID, String description) {
        ContentValues values = new ContentValues();

        if (id != null)
            values.put(KEY_PRODUCT_ID, id);

        values.put(KEY_PRODUCT_NAME, name);
        values.put(KEY_PRODUCT_DESCRIPTION, description);
        values.put(KEY_PRODUCT_PRICE, price);
        values.put(KEY_PRODUCT_IMAGE, imgID);


        db.insert(TABLE_NAME, null, values);
    }
}
