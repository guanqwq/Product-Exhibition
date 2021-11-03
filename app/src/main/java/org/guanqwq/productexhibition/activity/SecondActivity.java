package org.guanqwq.productexhibition.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;

import org.guanqwq.productexhibition.DataSource;
import org.guanqwq.productexhibition.ProductDatabaseHelper;
import org.guanqwq.productexhibition.R;
import org.guanqwq.productexhibition.adapter.ProductAdapter;
import org.guanqwq.productexhibition.model.Type;

public class SecondActivity extends AppCompatActivity {
    public static final String MESSAGE =
            "org.guanqwq.productexhibition.activity.SecondActivity.KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        int typeIndex = this.getIntent().getIntExtra(MainActivity.MESSAGE, -1);
        Type currentType = DataSource.types[typeIndex];

        // ProductAdapter adapter = new ProductAdapter(currentType.getProducts());
        // RecyclerView recyclerView = findViewById(R.id.productRecyclerView);
        // recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // recyclerView.setAdapter(adapter);

        ProductDatabaseHelper databaseHelper = new ProductDatabaseHelper(this);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.query(
                ProductDatabaseHelper.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null);



    }
}