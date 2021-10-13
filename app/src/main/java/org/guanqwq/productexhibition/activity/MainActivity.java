package org.guanqwq.productexhibition.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.guanqwq.productexhibition.DataSource;
import org.guanqwq.productexhibition.R;
import org.guanqwq.productexhibition.adapter.TypeAdapter;

public class MainActivity extends AppCompatActivity {
    public static final String MESSAGE =
            "org.guanqwq.productexhibition.activity.MainActivity.KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TypeAdapter adapter = new TypeAdapter(DataSource.types);
        RecyclerView recyclerView = findViewById(R.id.typeRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}