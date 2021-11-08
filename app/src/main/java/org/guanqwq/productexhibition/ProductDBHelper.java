package org.guanqwq.productexhibition;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import org.guanqwq.productexhibition.model.Product;
import org.guanqwq.productexhibition.model.Type;

public class ProductDBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "shopping";
    public static final int DATABASE_VERSION = 2;

    public static final String TABLE_NAME = "products";

    public static final String ID = "_id";
    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String PRICE = "price";
    public static final String IMG_ID = "img_id";
    public static final String TYPE = "type";
    public static final String FAVOR = "favorite";

    public ProductDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = String.format(
                "CREATE TABLE %s(" +
                        "%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s VARCHAR," +
                        "%s TEXT," +
                        "%s DOUBLE," +
                        "%s INTEGER," +
                        "%s VARCHAR)",
                TABLE_NAME, ID, NAME, DESCRIPTION,
                 PRICE, IMG_ID, TYPE);
        db.execSQL(CREATE_TABLE);

        for (Type type : DataSource.types) {
            for (Product p : type.getProducts()) {
                insertProduct(db, null, p.getName(), p.getPrice(), p.getImgID(),
                        p.getDescription(), type.getName());
            }
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 2) {
            String ADD_COLUMN =
                    String.format("ALTER TABLE %s ADD %s TINYINT", TABLE_NAME, FAVOR);
            db.execSQL(ADD_COLUMN);

            String INIT_FAVOR = String.format("UPDATE %s SET %s = 0", TABLE_NAME, FAVOR);
            db.execSQL(INIT_FAVOR);
        }
    }

    public void insertProduct(SQLiteDatabase db, Integer id, String name, double price,
                              int imgID, String description, String type) {
        ContentValues values = new ContentValues();

        if (id != null)
            values.put(ID, id);

        values.put(NAME, name);
        values.put(DESCRIPTION, description);
        values.put(PRICE, price);
        values.put(IMG_ID, imgID);
        values.put(TYPE, type);

        db.insert(TABLE_NAME, null, values);
    }
}
