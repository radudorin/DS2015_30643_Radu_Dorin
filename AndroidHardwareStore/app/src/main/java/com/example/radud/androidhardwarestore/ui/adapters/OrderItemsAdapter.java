package com.example.radud.androidhardwarestore.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.radud.androidhardwarestore.R;
import com.example.radud.androidhardwarestore.model.OrderItem;
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
public class OrderItemsAdapter extends RecyclerView.Adapter<OrderItemsAdapter.OrderItemVH> {

    private LayoutInflater mLayoutInflater;
    private OnItemSelectedListener mListener;
    private List<OrderItem> mOrderItems;

    public OrderItemsAdapter(List<OrderItem> orderItems, OnItemSelectedListener listener) {
        mListener = listener;
        mOrderItems = orderItems;
        mLayoutInflater = (LayoutInflater) UIUtils.getAppContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public OrderItemVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.product_item, parent, false);
        return new OrderItemVH(view);
    }

    @Override
    public void onBindViewHolder(OrderItemVH holder, int position) {
        Product currentProduct = mOrderItems.get(position).getProduct();

        holder.mProductDescriptionTV.setText(currentProduct.getDescription());
        ImageLoader.loadImageWithPlaceHolder(holder.mProductIconIV, currentProduct.getImageSource(), R.drawable.product_placeholder);
        holder.mProductPriceTV.setText(String.valueOf(currentProduct.getPrice()) + " $");
        holder.mProductTitleTV.setText(currentProduct.getName());

        if (holder.mProductQuantityPriceTV.getVisibility() != View.VISIBLE) {
            holder.mProductQuantityPriceTV.setVisibility(View.VISIBLE);
        }

        holder.mProductStockTV.setVisibility(View.GONE);

        holder.mProductQuantityPriceTV.setText(mOrderItems.get(position).getQuantity() + " / " + mOrderItems.get(position).getQuantity() * currentProduct.getPrice() + "$");
    }

    public void setOrderItems(List<OrderItem> mOrderItems) {
        this.mOrderItems = mOrderItems;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mOrderItems == null ? 0 : mOrderItems.size();
    }

    public class OrderItemVH extends RecyclerView.ViewHolder {

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

        public OrderItemVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onItemSelected(mOrderItems.get(getAdapterPosition()).getId());
                }
            });
        }
    }
}
