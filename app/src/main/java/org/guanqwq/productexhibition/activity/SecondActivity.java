package org.guanqwq.productexhibition.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import org.guanqwq.productexhibition.DataSource;
import org.guanqwq.productexhibition.ProductDBHelper;
import org.guanqwq.productexhibition.R;
import org.guanqwq.productexhibition.adapter.ProductCursorAdapter;
import org.guanqwq.productexhibition.model.Type;

public class SecondActivity extends AppCompatActivity {
    public static final String MESSAGE =
            "org.guanqwq.productexhibition.activity.SecondActivity.KEY";

    SQLiteDatabase db;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        int typeIndex = this.getIntent().getIntExtra(MainActivity.MESSAGE, -1);
        // Type currentType = DataSource.types[typeIndex];
        String type = DataSource.types[typeIndex].getName();

        // ProductAdapter adapter = new ProductAdapter(currentType.getProducts());

        ProductDBHelper databaseHelper = new ProductDBHelper(this);
        db = databaseHelper.getReadableDatabase();
        cursor = db.query(ProductDBHelper.TABLE_NAME,
                null,
                "type=?",
                new String[] { type },
                null,
                null,
                null);

        ProductCursorAdapter adapter = new ProductCursorAdapter(this, cursor, 0);

        RecyclerView recyclerView = findViewById(R.id.productRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cursor.close();
        db.close();
    }
}