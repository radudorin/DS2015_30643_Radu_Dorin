package com.example.radud.androidhardwarestore.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by radud on 07/01/2016.
 */
public class OrderItemsAdapter extends RecyclerView.Adapter<OrderItemsAdapter.OrderItemVH> {

    @Override
    public OrderItemVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(OrderItemVH holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class OrderItemVH extends RecyclerView.ViewHolder {

        public OrderItemVH(View itemView) {
            super(itemView);
        }
    }

}
