package org.guanqwq.productexhibition.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.guanqwq.productexhibition.DataSource;
import org.guanqwq.productexhibition.R;
import org.guanqwq.productexhibition.model.Product;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        int typeIndex = getIntent().getIntExtra(MainActivity.MESSAGE, -1);
        int productIndex = getIntent().getIntExtra(SecondActivity.MESSAGE, -1);

        Product currentProduct = DataSource.types[typeIndex].getProducts()[productIndex];

        ImageView imageView = findViewById(R.id.detailsImageView);
        TextView name = findViewById(R.id.detailsName);
        TextView price = findViewById(R.id.detailsPrice);
        TextView description = findViewById(R.id.detailsDescription);

        imageView.setImageResource(currentProduct.getImgID());
        name.setText(currentProduct.getName());
        price.setText( String.format("¥ %.2f", currentProduct.getPrice()) );
        description.setText(currentProduct.getDescription());
    }
}