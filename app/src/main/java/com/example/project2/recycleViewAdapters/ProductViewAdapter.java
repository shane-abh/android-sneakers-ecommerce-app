package com.example.project2.recycleViewAdapters;





import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project2.DescriptionActivity;
import com.example.project2.Product;
import com.example.project2.R;

import java.util.ArrayList;
import java.util.List;

public class ProductViewAdapter extends RecyclerView.Adapter<ProductViewHolder>{

    List<Product> productData;

    public ProductViewAdapter(List<Product> productData) {
        this.productData = productData;
    }

    public void filterList(ArrayList<Product> filterlist) {
        // below line is to add our filtered
        // list in our course array list.
        productData = filterlist;
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new ProductViewHolder(layoutInflater, parent);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.productImage.setImageResource(productData.get(position).getImage());
        holder.productHeading.setText(productData.get(position).getTitle());
        holder.productTag.setText(productData.get(position).getTag());
        holder.productPrice.setText("$" + productData.get(position).getPrice());


        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = holder.getAdapterPosition();
                Intent intent = new Intent(view.getContext(), DescriptionActivity.class);

                intent.putExtra("image", String.valueOf(productData.get(pos).getImage()));
                intent.putExtra("productHeading", productData.get(pos).getTitle());
                intent.putExtra("productTag", productData.get(pos).getTag());
                intent.putExtra("productPrice", String.valueOf(productData.get(pos).getPrice()) );
                intent.putExtra("productDescription", productData.get(pos).getDescription());
                intent.putExtra("descriptionImage", String.valueOf(productData.get(pos).getDescription_image()));

                view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return productData.size();
    }
}

 class ProductViewHolder extends RecyclerView.ViewHolder {

    ImageView productImage;
    TextView productHeading;
    TextView productTag;
    TextView productPrice;
    Button addToCart;

    CardView item;


     ProductViewHolder(LayoutInflater inflater, ViewGroup parent){
         super(inflater.inflate(R.layout.product_layout,parent,false));
         productImage = itemView.findViewById(R.id.productImage);
         productHeading = itemView.findViewById((R.id.productHeading));
         productTag = itemView.findViewById(R.id.productTag);
         productPrice = itemView.findViewById(R.id.productPrice);
//         addToCart = itemView.findViewById(R.id.addToCart);

         item = itemView.findViewById(R.id.item);
     }
 }
