package org.guanqwq.productexhibition.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.guanqwq.productexhibition.ProductDBHelper;
import org.guanqwq.productexhibition.R;
import org.guanqwq.productexhibition.activity.SecondActivity;
import org.guanqwq.productexhibition.activity.ThirdActivity;

public class ProductCursorAdapter extends RecyclerViewCursorAdapter {
    /**
     * Recommended constructor.
     *
     * @param context The context
     * @param c       The cursor from which to get the data.
     * @param flags   Flags used to determine the behavior of the adapter;
     *                Currently it accept {@link #FLAG_REGISTER_CONTENT_OBSERVER}.
     */
    public ProductCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, Cursor cursor) {
        @SuppressLint("Range") int imgID = cursor.getInt(cursor.getColumnIndex(ProductDBHelper.IMG_ID));
        @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(ProductDBHelper.NAME));
        @SuppressLint("Range") Double price = cursor.getDouble(cursor.getColumnIndex(ProductDBHelper.PRICE));

        ((ViewHolder) holder).getIcon().setImageResource(imgID);
        ((ViewHolder) holder).getName().setText(name);
        ((ViewHolder) holder).getPrice().setText( String.format("¥ %.2f", price) );
    }

    @Override
    protected void onContentChanged() {

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_list_item, parent, false);

        return new ViewHolder(view);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView icon;
        private final TextView name;
        private final TextView price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(view -> {
                Intent intent = new Intent(view.getContext(), ThirdActivity.class);
                intent.putExtra(SecondActivity.MESSAGE, getItemId());
                view.getContext().startActivity(intent);
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
}
