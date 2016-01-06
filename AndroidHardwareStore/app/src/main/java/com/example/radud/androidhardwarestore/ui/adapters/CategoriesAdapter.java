package com.example.radud.androidhardwarestore.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.radud.androidhardwarestore.R;
import com.example.radud.androidhardwarestore.model.ProductCategory;
import com.example.radud.androidhardwarestore.ui.interfaces.OnItemSelectedListener;
import com.example.radud.androidhardwarestore.utils.ImageLoader;
import com.example.radud.androidhardwarestore.utils.UIUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by radud on 03/01/2016.
 */
public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoriesVH> {

    private final String ALL_ICON_PNG = "http://computerrepairdowney.com/images/12.png";
    private final String ALL_TEXT = "All";

    private LayoutInflater mLayoutInflater;
    private List<ProductCategory> mCategories;
    private OnItemSelectedListener mListener;

    public CategoriesAdapter(List<ProductCategory> categories, OnItemSelectedListener listener) {
        mCategories = categories;
        mListener = listener;
        mLayoutInflater = (LayoutInflater) UIUtils.getAppContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public CategoriesVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.category_item, parent, false);
        return new CategoriesVH(view);
    }

    @Override
    public void onBindViewHolder(CategoriesVH holder, int position) {
        if (position == 0) {
            ImageLoader.loadImage(holder.mCategoryIconIV, ALL_ICON_PNG);
            holder.mCategoryNameTV.setText(ALL_TEXT);
        } else {
            position = position - 1;
            ProductCategory currentCategory = mCategories.get(position);
            ImageLoader.loadImage(holder.mCategoryIconIV, currentCategory.getImageUrl());
            holder.mCategoryNameTV.setText(currentCategory.getName());
        }
    }

    @Override
    public int getItemCount() {
        return mCategories == null ? 0 : mCategories.size() + 1;
    }

    public class CategoriesVH extends RecyclerView.ViewHolder {

        @Bind(R.id.ci_category_icon_iv)
        ImageView mCategoryIconIV;
        @Bind(R.id.ci_category_name_tv)
        TextView mCategoryNameTV;

        public CategoriesVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getAdapterPosition() != 0) {
                        mListener.onItemSelected(mCategories.get(getAdapterPosition() - 1).getId());
                    } else {
                        mListener.onItemSelected(-1);
                    }
                }
            });
        }
    }
}
