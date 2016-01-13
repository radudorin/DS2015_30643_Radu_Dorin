package com.example.radud.androidhardwarestore.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.radud.androidhardwarestore.R;
import com.example.radud.androidhardwarestore.model.Order;
import com.example.radud.androidhardwarestore.ui.interfaces.OnItemSelectedListener;
import com.example.radud.androidhardwarestore.utils.UIUtils;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by radud on 07/01/2016.
 */
public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.OrderVH> {

    private LayoutInflater mLayoutInflater;
    private OnItemSelectedListener mListener;
    private List<Order> mOrders;
    private SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    public OrdersAdapter(List<Order> orderItems, OnItemSelectedListener listener) {
        mListener = listener;
        mOrders = orderItems;
        mLayoutInflater = (LayoutInflater) UIUtils.getAppContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public OrderVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.order_item, parent, false);
        return new OrderVH(view);
    }

    @Override
    public void onBindViewHolder(OrderVH holder, int position) {

        Order currentOrder = mOrders.get(position);

        holder.mDeliveryDateTV.setText(format.format(currentOrder.getDeliveryDate()));
        holder.mOrderDateTV.setText(format.format(currentOrder.getOrderDate()));
        holder.mOrderIdTV.setText(String.valueOf(currentOrder.getId()));
        holder.mOrderTotalTV.setText(String.valueOf(currentOrder.getTotal()));
        holder.mOrderStatusTV.setText(currentOrder.getOrderStatus().getStatusName());
    }

    @Override
    public int getItemCount() {
        return mOrders == null ? 0 : mOrders.size();
    }

    public class OrderVH extends RecyclerView.ViewHolder {

        @Bind(R.id.oi_delivery_date)
        TextView mDeliveryDateTV;
        @Bind(R.id.oi_order_date)
        TextView mOrderDateTV;
        @Bind(R.id.oi_order_id)
        TextView mOrderIdTV;
        @Bind(R.id.oi_order_total)
        TextView mOrderTotalTV;
        @Bind(R.id.oi_order_status)
        TextView mOrderStatusTV;

        public OrderVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onItemSelected(mOrders.get(getAdapterPosition()).getId());
                }
            });
        }
    }

}
