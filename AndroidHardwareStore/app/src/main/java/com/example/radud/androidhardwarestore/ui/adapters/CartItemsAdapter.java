package com.example.radud.androidhardwarestore.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.radud.androidhardwarestore.R;
import com.example.radud.androidhardwarestore.model.CartItem;
import com.example.radud.androidhardwarestore.model.Product;
import com.example.radud.androidhardwarestore.ui.interfaces.OnItemSelectedListener;
import com.example.radud.androidhardwarestore.utils.ImageLoader;
import com.example.radud.androidhardwarestore.utils.UIUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by radud on 07/01/2016.
 */
public class CartItemsAdapter extends RecyclerView.Adapter<CartItemsAdapter.CartItemVH> {

    private LayoutInflater mLayoutInflater;
    private OnItemSelectedListener mListener;
    private List<CartItem> mCartItems;

    public CartItemsAdapter(List<CartItem> CartItems, OnItemSelectedListener listener) {
        mListener = listener;
        mCartItems = CartItems;
        mLayoutInflater = (LayoutInflater) UIUtils.getAppContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public CartItemVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.product_item, parent, false);
        return new CartItemVH(view);
    }

    @Override
    public void onBindViewHolder(CartItemVH holder, int position) {
        Product currentProduct = mCartItems.get(position).getProduct();

        holder.mProductDescriptionTV.setText(currentProduct.getDescription());
        ImageLoader.loadImageWithPlaceHolder(holder.mProductIconIV, currentProduct.getImageSource(), R.drawable.product_placeholder);
        holder.mProductPriceTV.setText(String.valueOf(currentProduct.getPrice()) + " $");
        holder.mProductStockTV.setText(String.valueOf(currentProduct.getStock()));
        holder.mProductTitleTV.setText(currentProduct.getName());

        if (holder.mProductQuantityPriceTV.getVisibility() != View.VISIBLE) {
            holder.mProductQuantityPriceTV.setVisibility(View.VISIBLE);
        }

        holder.mProductQuantityPriceTV.setText(mCartItems.get(position).getQuantity() + " / " + mCartItems.get(position).getQuantity() * currentProduct.getPrice());
    }

    public void setCartItems(List<CartItem> mCartItems) {
        this.mCartItems = mCartItems;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mCartItems == null ? 0 : mCartItems.size();
    }

    public class CartItemVH extends RecyclerView.ViewHolder {

        @Bind(R.id.pi_quantity_price_tv)
        TextView mProductQuantityPriceTV;
        @Bind(R.id.pi_product_description)
        TextView mProductDescriptionTV;
        @Bind(R.id.pi_product_icon_iv)
        ImageView mProductIconIV;
        @Bind(R.id.pi_product_price)
        TextView mProductPriceTV;
        @Bind(R.id.pi_product_title)
        TextView mProductTitleTV;
        @Bind(R.id.pi_product_stock)
        TextView mProductStockTV;

        public CartItemVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onItemSelected(mCartItems.get(getAdapterPosition()).getId());
                }
            });
        }
    }

}
