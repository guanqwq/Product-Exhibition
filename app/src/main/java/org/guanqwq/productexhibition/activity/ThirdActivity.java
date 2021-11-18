package org.guanqwq.productexhibition.activity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.guanqwq.productexhibition.ProductDBHelper;
import org.guanqwq.productexhibition.R;

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

        // 视图声明
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

        // 数据填充
        if (cursor.moveToFirst()) {
            imageView.setImageResource( getInt(ProductDBHelper.IMG_ID) );
            name.setText( getString(ProductDBHelper.NAME) );
            price.setText( String.format("¥ %.2f",
                    getDouble(ProductDBHelper.PRICE) ));
            description.setText( getString(ProductDBHelper.DESCRIPTION) );

            isStar = getInt(ProductDBHelper.FAVOR)==1;

            if (isStar) {
                starBtn.setImageResource(R.drawable.bookmark_solid);
            }
        }

        // 收藏按钮单击监听器
        View.OnClickListener onClickListener = view -> {
            isStar = !isStar;

            String UPDATE_FAVOR =
                    String.format("UPDATE %s SET %s=%d WHERE _id=%d",
                            ProductDBHelper.TABLE_NAME,
                            ProductDBHelper.FAVOR,
                            isStar?1:0,
                            productID);
            db.execSQL(UPDATE_FAVOR);

            if (isStar) {
                ((ImageView)view).setImageResource(R.drawable.bookmark_solid);
                Toast.makeText(view.getContext(), "已添加到收藏区", Toast.LENGTH_SHORT).show();
            } else {
                ((ImageView)view).setImageResource(R.drawable.bookmark_regular);
                Toast.makeText(view.getContext(), "已取消收藏", Toast.LENGTH_SHORT).show();
            }
        };
        starBtn.setOnClickListener(onClickListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cursor.close();
        db.close();
    }

    @SuppressLint("Range")
    public String getString(String column) {
        return cursor.getString( cursor.getColumnIndex(column) );
    }

    @SuppressLint("Range")
    public int getInt(String column) {
        return cursor.getInt( cursor.getColumnIndex(column) );
    }

    @SuppressLint("Range")
    public double getDouble(String column) {
        return cursor.getDouble( cursor.getColumnIndex(column) );
    }
}