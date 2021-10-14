package org.guanqwq.productexhibition.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.guanqwq.productexhibition.R;
import org.guanqwq.productexhibition.activity.MainActivity;
import org.guanqwq.productexhibition.activity.SecondActivity;
import org.guanqwq.productexhibition.activity.ThirdActivity;
import org.guanqwq.productexhibition.model.Product;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private Product[] products;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView icon;
        private final TextView name;
        private final TextView price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(view -> {
                int typeIndex = ((Activity) itemView.getContext()).getIntent()
                        .getIntExtra(MainActivity.MESSAGE, -1);

                Intent intent = new Intent(itemView.getContext(), ThirdActivity.class);
                intent.putExtra(MainActivity.MESSAGE, typeIndex);
                intent.putExtra(SecondActivity.MESSAGE, getAdapterPosition());
                itemView.getContext().startActivity(intent);
            });

            icon = itemView.findViewById(R.id.productItemImageView);
            name = itemView.findViewById(R.id.productItemName);
            price = itemView.findViewById(R.id.productItemPrice);
        }

        public ImageView getIcon() {
            return icon;
        }

        public TextView getName() {
            return name;
        }

        public TextView getPrice() {
            return price;
        }
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param products Product[] containing the data to populate views to be used
     * by RecyclerView.
     */
    public ProductAdapter(Product[] products) {
        this.products = products;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_list_item, parent, false);

        return new ProductAdapter.ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getIcon().setImageResource( products[position].getImgID() );
        holder.getName().setText( products[position].getName() );
        holder.getPrice().setText( String.format("¥ %.2f", products[position].getPrice()) );
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return products.length;
    }
}
