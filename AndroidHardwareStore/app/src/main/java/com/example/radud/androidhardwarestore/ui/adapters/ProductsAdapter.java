package com.example.radud.androidhardwarestore.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.radud.androidhardwarestore.R;
import com.example.radud.androidhardwarestore.model.Product;
import com.example.radud.androidhardwarestore.ui.interfaces.OnItemSelectedListener;
import com.example.radud.androidhardwarestore.utils.ImageLoader;
import com.example.radud.androidhardwarestore.utils.UIUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by radud on 03/01/2016.
 */
public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductVH> {

    private LayoutInflater mLayoutInflater;
    private OnItemSelectedListener mListener;
    private List<Product> mProducts;

    public ProductsAdapter(List<Product> products, OnItemSelectedListener listener) {
        mListener = listener;
        mProducts = products;
        mLayoutInflater = (LayoutInflater) UIUtils.getAppContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public ProductVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.product_item, parent, false);
        return new ProductVH(view);
    }

    @Override
    public void onBindViewHolder(ProductVH holder, int position) {
        Product currentProduct = mProducts.get(position);

        holder.mProductDescriptionTV.setText(currentProduct.getDescription());
        ImageLoader.loadImageWithPlaceHolder(holder.mProductIconIV, currentProduct.getImageSource(), R.drawable.product_placeholder);
        holder.mProductPriceTV.setText(String.valueOf(currentProduct.getPrice()) + " $");
        holder.mProductStockTV.setText(String.valueOf(currentProduct.getStock()));
        holder.mProductTitleTV.setText(currentProduct.getName());
    }

    public void setProducts(List<Product> mProducts) {
        this.mProducts = mProducts;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mProducts == null ? 0 : mProducts.size();
    }

    public class ProductVH extends RecyclerView.ViewHolder {

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

        public ProductVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onItemSelected(mProducts.get(getAdapterPosition()).getId());
                }
            });
        }
    }

}
