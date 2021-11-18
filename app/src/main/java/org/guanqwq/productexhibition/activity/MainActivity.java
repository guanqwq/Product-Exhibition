package org.guanqwq.productexhibition.activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.guanqwq.productexhibition.DataSource;
import org.guanqwq.productexhibition.ProductDBHelper;
import org.guanqwq.productexhibition.R;
import org.guanqwq.productexhibition.adapter.ProductCursorAdapter;
import org.guanqwq.productexhibition.adapter.TypeAdapter;

public class MainActivity extends AppCompatActivity {
    public static final String MESSAGE =
            "org.guanqwq.productexhibition.activity.MainActivity.KEY";

    SQLiteDatabase db;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 数据库初始化
        db = new ProductDBHelper(this).getReadableDatabase();

        // Recycler适配器 - 商品区
        TypeAdapter typeAdapter = new TypeAdapter(DataSource.types);
        RecyclerView typeRecyclerView = findViewById(R.id.typeRecyclerView);
        typeRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        typeRecyclerView.setAdapter(typeAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Log.d(MESSAGE, "onResume()被触发");
        setCursorAdapter();
    }

    private void setCursorAdapter() {
        cursor = db.query(ProductDBHelper.TABLE_NAME,
                null,
                "favorite=?",
                new String[] { "1" },
                null,
                null,
                null);

        RecyclerView collectRecyclerView = findViewById(R.id.collectRecyclerView);
        if (cursor.getCount() == 0) {
            collectRecyclerView.setVisibility(View.GONE);
            findViewById(R.id.textView2).setVisibility(View.GONE);
        } else {
            collectRecyclerView.setVisibility(View.VISIBLE);
            findViewById(R.id.textView2).setVisibility(View.VISIBLE);

            // RecyclerCursor适配器 - 收藏区
            ProductCursorAdapter cursorAdapter = new ProductCursorAdapter(this, cursor, 0);
            collectRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            collectRecyclerView.setAdapter(cursorAdapter);
        }

        // CursorAdapter adapter = new SimpleCursorAdapter(this,
        //         R.layout.product_list_item,
        //         cursor,
        //         new String[] {"name", "price", "imageRsID"},
        //         new int[] {R.id.productItemName, R.id.productItemPrice, R.id.productItemImageView},
        //         0);
    }

    // @Override
    // protected void onRestart() {
    //     super.onRestart();
    //     Log.d(MESSAGE, "onRestart()被触发");
    // }
    //
    // @Override
    // protected void onStart() {
    //     super.onStart();
    //     Log.d(MESSAGE, "onStart()被触发");
    // }
    //
    // @Override
    // protected void onStop() {
    //     super.onStop();
    //     Log.d(MESSAGE, "onStop()被触发");
    // }
    //
    // @Override
    // protected void onDestroy() {
    //     super.onDestroy();
    //     Log.d(MESSAGE, "onDestroy()被触发");
    // }
    //
    // @Override
    // protected void onPause() {
    //     super.onPause();
    //     Log.d(MESSAGE, "onPause()被触发");
    // }
}