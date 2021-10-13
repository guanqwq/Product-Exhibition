package org.guanqwq.productexhibition.adapter;

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
import org.guanqwq.productexhibition.model.Type;

public class TypeAdapter extends RecyclerView.Adapter<TypeAdapter.ViewHolder> {
    private Type[] types;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView;
        private final TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(view -> {
                Intent intent = new Intent(view.getContext(), SecondActivity.class);
                intent.putExtra(MainActivity.MESSAGE, getAdapterPosition());
                view.getContext().startActivity(intent);
            });

            imageView = itemView.findViewById(R.id.typeItemImageView);
            textView = itemView.findViewById(R.id.typeItemTextView);
        }

        public ImageView getImageView() {
            return imageView;
        }

        public TextView getTextView() {
            return textView;
        }
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param types Type[] containing the data to populate views to be used
     * by RecyclerView.
     */
    public TypeAdapter(Type[] types) {
        this.types = types;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.type_list_item, parent, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getImageView().setImageResource( types[position].getIcon() );
        holder.getTextView().setText( types[position].getName() );
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return types.length;
    }
}
