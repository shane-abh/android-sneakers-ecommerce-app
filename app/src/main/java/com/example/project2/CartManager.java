package com.example.project2;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.project2.recycleViewAdapters.CartSizeChangeListener;

import java.util.ArrayList;
import java.util.List;

public class CartManager {

    private static CartManager instance;
    private List<Cart> cartItems;

    private String cartSize;



    private CartManager() {
        cartItems = new ArrayList<>();

    }

    public static synchronized CartManager getInstance() {
        if (instance == null) {
            instance = new CartManager();

        }
        return instance;
    }



    public List<Cart> getCartItems() {
        return cartItems;
    }

    public void addToCart(Cart item) {
        cartItems.add(item);
        this.cartSize = getSize() + "";

    }

    public void clearCart(){
        cartItems.clear();
    }

    public void removeFromCart(int position) {
        cartItems.remove(position);
    }

    public  int getSize(){
        return cartItems.size();
    }



    public List<CartSizeChangeListener> listeners = new ArrayList<>();

    public void registerListener(CartSizeChangeListener listener) {
        listeners.add(listener);
    }

    public void unregisterListener(CartSizeChangeListener listener) {
        listeners.remove(listener);
    }

    public  void notifyCartSizeChanged() {
        int newSize = getSize();
        for (CartSizeChangeListener listener : listeners) {
            listener.onCartSizeChanged(newSize);
        }
    }



}
