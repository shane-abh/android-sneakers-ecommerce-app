package com.example.project2.recycleViewAdapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project2.Cart;
import com.example.project2.CartManager;
import com.example.project2.R;

import java.util.List;

public class CartViewAdapter extends RecyclerView.Adapter<CartViewHolder> {

    private final CartViewInterface cartViewInterface;
    List<Cart> cartData;


    public CartViewAdapter(CartViewInterface cartViewInterface, List<Cart> cartData) {
        this.cartViewInterface = cartViewInterface;
        this.cartData = cartData;

    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new CartViewHolder(layoutInflater, parent, cartViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {

        if(!cartData.isEmpty()) {


            holder.cartImg.setImageResource(cartData.get(position).getProductImage());
            holder.cartProductHeading.setText(cartData.get(position).getProductTitle());
            holder.cartProductTag.setText(cartData.get(position).getProductTag());
//        holder.cartProductSize.setText(String.valueOf(cartData.get(position).getShoeSize()));
            holder.cartProductPrice.setText(String.valueOf(cartData.get(position).getProductPrice()));
            holder.cartProductQty.setText("Qty: "+ String.valueOf(cartData.get(position).getQuantity()));




        }
    }

    @Override
    public int getItemCount() {
        return cartData.size();
    }



}


class CartViewHolder extends RecyclerView.ViewHolder {

    ImageView cartImg;
    TextView cartProductHeading;
    TextView cartProductTag;
    TextView cartProductQty;
    TextView cartProductPrice;
//    TextView cartProductSize;

    ImageView deletebtn;


    public CartViewHolder(LayoutInflater inflater, ViewGroup parent, CartViewInterface cartViewInterface) {
        super(inflater.inflate(R.layout.cart_item_layout,parent,false));
        cartImg = itemView.findViewById(R.id.cartImg);
        cartProductHeading = itemView.findViewById(R.id.cartProductHeading);
        cartProductTag = itemView.findViewById(R.id.cartProductTag);
//        cartProductSize = itemView.findViewById(R.id.cartProductSize);
        cartProductQty = itemView.findViewById(R.id.cartProductQty);
        cartProductPrice = itemView.findViewById(R.id.cartProductPrice);



        deletebtn = itemView.findViewById(R.id.deletebtn);

        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cartViewInterface!=null){
                    int pos = getAdapterPosition();

                    if(pos != RecyclerView.NO_POSITION){
                        cartViewInterface.onDeleteClick(pos);
                    }
                }

            }

        });

    }
}