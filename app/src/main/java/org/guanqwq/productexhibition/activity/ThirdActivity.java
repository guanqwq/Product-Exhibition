package org.guanqwq.productexhibition.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.guanqwq.productexhibition.DataSource;
import org.guanqwq.productexhibition.ProductDBHelper;
import org.guanqwq.productexhibition.R;
import org.guanqwq.productexhibition.model.Product;

public class ThirdActivity extends AppCompatActivity {
    private boolean isStar = false;
    private long productID;
    SQLiteDatabase db;
    Cursor cursor;

    @SuppressLint({"Range", "DefaultLocale"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        ImageView imageView = findViewById(R.id.detailsImageView);
        TextView name = findViewById(R.id.detailsName);
        TextView price = findViewById(R.id.detailsPrice);
        TextView description = findViewById(R.id.detailsDescription);
        ImageView starBtn = findViewById(R.id.starImage);

        // int typeIndex = getIntent().getIntExtra(MainActivity.MESSAGE, -1);
        productID = getIntent().getLongExtra(SecondActivity.MESSAGE, -1);

        // Product currentProduct = DataSource.types[typeIndex].getProducts()[productIndex];
        ProductDBHelper dbHelper = new ProductDBHelper(this);
        db = dbHelper.getReadableDatabase();
        cursor = db.query(ProductDBHelper.TABLE_NAME,
                null,
                "_id=?",
                new String[] {String.valueOf(productID)},
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            imageView.setImageResource(cursor.getInt( cursor.getColumnIndex(ProductDBHelper.IMG_ID) ));
            name.setText(cursor.getString( cursor.getColumnIndex(ProductDBHelper.NAME) ));
            price.setText( String.format("¥ %.2f",
                    cursor.getDouble( cursor.getColumnIndex(ProductDBHelper.PRICE) )) );
            description.setText(cursor.getString( cursor.getColumnIndex(ProductDBHelper.DESCRIPTION) ));

            isStar = cursor.getInt( cursor.getColumnIndex(ProductDBHelper.FAVOR) )==1;

            if (isStar) {
                starBtn.setImageResource(R.drawable.bookmark_solid);
            }
        }

        View.OnClickListener onClickListener = view -> {
            isStar = !isStar;

            if (isStar) {
                ((ImageView)view).setImageResource(R.drawable.bookmark_solid);
            } else {
                ((ImageView)view).setImageResource(R.drawable.bookmark_regular);
            }

            String UPDATE_FAVOR =
                    String.format("UPDATE %s SET %s=%d WHERE _id=%d",
                            ProductDBHelper.TABLE_NAME,
                            ProductDBHelper.FAVOR,
                            isStar?1:0,
                            productID);
            db.execSQL(UPDATE_FAVOR);
        };
        starBtn.setOnClickListener(onClickListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cursor.close();
        db.close();
    }
}