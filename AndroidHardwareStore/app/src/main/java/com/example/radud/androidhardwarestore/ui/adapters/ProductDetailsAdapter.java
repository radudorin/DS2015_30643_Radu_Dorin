package com.example.radud.androidhardwarestore.ui.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.radud.androidhardwarestore.R;
import com.example.radud.androidhardwarestore.model.Product;
import com.example.radud.androidhardwarestore.model.Rating;
import com.example.radud.androidhardwarestore.utils.ImageLoader;
import com.example.radud.androidhardwarestore.utils.UIUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by radud on 05/01/2016.
 */
public class ProductDetailsAdapter extends RecyclerView.Adapter {

    public static final int VIEW_TYPE_INFO = 1;
    public static final int VIEW_TYPE_RATING = 2;

    private LayoutInflater mLayoutInflater;
    private Product mProduct;
    private List<Rating> mRatings;

    public ProductDetailsAdapter(Product product, List<Rating> ratings) {
        mRatings = ratings;
        mProduct = product;
        mLayoutInflater = (LayoutInflater) UIUtils.getAppContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return VIEW_TYPE_INFO;
        } else {
            return VIEW_TYPE_RATING;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_INFO) {
            return new InfoViewHolder(mLayoutInflater.inflate(R.layout.product_info_item, parent, false));
        } else {
            return new RatingViewHolder(mLayoutInflater.inflate(R.layout.product_rating_item, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (getItemViewType(position) == VIEW_TYPE_INFO) {
            InfoViewHolder currentHolder = (InfoViewHolder) holder;
            currentHolder.mDescriptionTV.setText(mProduct.getDescription());
            currentHolder.mAvailableQuantityTV.setText("Available quantity : " + mProduct.getStock());
            currentHolder.mPriceTV.setText("Product price : " + mProduct.getPrice());
            ImageLoader.loadImageWithPlaceHolder(currentHolder.mImageIV, mProduct.getImageSource(), R.drawable.product_placeholder);
        } else {
            RatingViewHolder currentHolder = (RatingViewHolder) holder;
            Rating currentRating = mRatings.get(position - 1);
            currentHolder.mComment.setText(currentRating.getComment());
            currentHolder.mRating.setNumStars(currentRating.getValue());
        }

    }

    @Override
    public int getItemCount() {
        return mRatings == null ? 1 : mRatings.size() + 1;
    }

    public class InfoViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.pii_product_image_iv)
        ImageView mImageIV;
        @Bind(R.id.pii_product_description_tv)
        TextView mDescriptionTV;
        @Bind(R.id.pii_quantity_tv)
        TextView mAvailableQuantityTV;
        @Bind(R.id.pii_rating_rb)
        RatingBar mRatingBar;
        @Bind(R.id.pii_price_tv)
        TextView mPriceTV;

        public InfoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mRatingBar.setIsIndicator(false);
            mRatingBar.setMax(5);
            mRatingBar.setNumStars(5);
            mRatingBar.setStepSize(1f);
            Drawable drawable = mRatingBar.getProgressDrawable();
            drawable.setColorFilter(Color.parseColor("#ffd700"), PorterDuff.Mode.SRC_ATOP);
            mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

                }
            });
        }
    }

    public class RatingViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.pri_rb)
        RatingBar mRating;
        @Bind(R.id.pri_tv)
        TextView mComment;

        public RatingViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
