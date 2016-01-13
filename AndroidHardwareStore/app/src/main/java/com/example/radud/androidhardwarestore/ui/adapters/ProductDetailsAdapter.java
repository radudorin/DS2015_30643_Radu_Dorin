package com.example.radud.androidhardwarestore.ui.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.radud.androidhardwarestore.R;
import com.example.radud.androidhardwarestore.model.PriceItem;
import com.example.radud.androidhardwarestore.model.Product;
import com.example.radud.androidhardwarestore.model.Rating;
import com.example.radud.androidhardwarestore.model.Result;
import com.example.radud.androidhardwarestore.sync.ApiHelper;
import com.example.radud.androidhardwarestore.utils.ImageLoader;
import com.example.radud.androidhardwarestore.utils.ToastUtils;
import com.example.radud.androidhardwarestore.utils.UIUtils;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by radud on 05/01/2016.
 */
public class ProductDetailsAdapter extends RecyclerView.Adapter {

    public static final int VIEW_TYPE_INFO = 1;
    public static final int VIEW_TYPE_RATING = 2;

    private LayoutInflater mLayoutInflater;
    private Product mProduct;
    private List<Rating> mRatings;
    private Context mContext;
    private ProductListener mListener;

    public ProductDetailsAdapter(Context context, Product product, List<Rating> ratings, ProductListener listener) {
        mListener = listener;
        mContext = context;
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

    public void setRatings(List<Rating> ratings) {
        this.mRatings = ratings;
        notifyDataSetChanged();
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
        @Bind(R.id.pii_price_chart_lc)
        LineChart mLineChart;

        public InfoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mRatingBar.setIsIndicator(false);
            mRatingBar.setMax(5);
            mRatingBar.setNumStars(5);
            mRatingBar.setStepSize(1f);
            Drawable drawable = mRatingBar.getProgressDrawable();
            drawable.setColorFilter(UIUtils.getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
            mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, final float rating, boolean fromUser) {
                    new MaterialDialog.Builder(mContext)
                            .title("Enter your feedback")
                            .inputType(InputType.TYPE_CLASS_TEXT)
                            .cancelable(true)
                            .cancelListener(new DialogInterface.OnCancelListener() {
                                @Override
                                public void onCancel(DialogInterface dialog) {
                                    mRatingBar.setRating(0);
                                }
                            })
                            .input("Enter your comment here", "", new MaterialDialog.InputCallback() {
                                @Override
                                public void onInput(MaterialDialog dialog, CharSequence input) {
                                    mListener.onRatingAdded(mProduct.getId(), input.toString(), rating);
                                }
                            }).show();
                }
            });

            ApiHelper.getApi().getPriceItems(mProduct.getId(), new Callback<Result<List<PriceItem>>>() {
                @Override
                public void success(Result<List<PriceItem>> listResult, Response response) {
                    if (listResult.getHasErrors() || listResult.getResponse() == null) {
                        ToastUtils.showError(UIUtils.getAppContext(), listResult.getMessage());
                    } else {
                        setData(listResult.getResponse());
                    }
                }

                @Override
                public void failure(RetrofitError error) {

                }
            });
        }

        private void setData(List<PriceItem> priceItems) {
            if (priceItems == null) {
                return;
            }
//            LineData lineData = createData();
//            mLineChart.setData(lineData);
//
//            ArrayList<Entry> valsComp1 = new ArrayList<>();
//            ArrayList<Entry> valsComp2 = new ArrayList<>();
//
//            Entry c1e1 = new Entry(100.000f, 0);
//            valsComp1.add(c1e1);
//            Entry c1e2 = new Entry(50.000f, 1);
//            valsComp1.add(c1e2);
//
//            Entry c2e1 = new Entry(120.000f, 0);
//            valsComp2.add(c2e1);
//            Entry c2e2 = new Entry(110.000f, 1);
//            valsComp2.add(c2e2);
//
//            LineDataSet setComp1 = new LineDataSet(valsComp1, "Company 1");
//            setComp1.setAxisDependency(YAxis.AxisDependency.LEFT);
//
//            ArrayList<LineDataSet> dataSets = new ArrayList<>();
//            dataSets.add(setComp1);
//
//            ArrayList<String> xVals = new ArrayList<>();
//            xVals.add("1.Q");
//            xVals.add("2.Q");
//            xVals.add("3.Q");
//            xVals.add("4.Q");
//
//            LineData data = new LineData(xVals, dataSets);
//            mLineChart.setData(data);
//            mLineChart.invalidate();

            LineData lineData = createData();
            mLineChart.setData(lineData);

            LineDataSet yVals = createSet("Red", "Price evolution");
            yVals.setDrawValues(false);
            lineData.addDataSet(yVals);

            for (int i = 0; i < priceItems.size(); i++) {

                lineData.addXValue(priceItems.get(i).getModificationDate().toString());
                lineData.addEntry(new Entry((float) priceItems.get(i).getPrice(), i), 0);
            }

            mLineChart.notifyDataSetChanged();
            mLineChart.invalidate();
        }

        private LineDataSet createSet(String color, String name) {
            LineDataSet set = new LineDataSet(null, name);
            set.setAxisDependency(YAxis.AxisDependency.LEFT);
            set.setColor(Color.parseColor(color));
            set.setLineWidth(3f);
            set.setFillAlpha(100);

            set.setDrawCircles(true);
            set.setCircleSize(1f);
            set.setCircleColor(Color.parseColor(color));
            set.setDrawCircleHole(false);

            return set;
        }

        private LineData createData() {
            LineData lineData = new LineData();
            lineData.addXValue("");
            lineData.setDrawValues(false);
            lineData.setValueTextColor(Color.BLACK);
            lineData.setValueTextSize(9f);

            return lineData;
        }

        public void drawLines() {
            XAxis xAxis = mLineChart.getXAxis();
            xAxis.setTextSize(10f);
            xAxis.setTextColor(Color.BLACK);
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

            xAxis.setDrawGridLines(true);
            xAxis.setDrawAxisLine(false);
            xAxis.setGridColor(Color.LTGRAY);
            xAxis.setGridLineWidth(2f);

            YAxis leftAxis = mLineChart.getAxisLeft();
            leftAxis.setTextColor(Color.BLACK);
            leftAxis.setTextSize(10f);


            leftAxis.setGridColor(Color.LTGRAY);
            leftAxis.setGridLineWidth(2f);
            leftAxis.setDrawGridLines(true);
            leftAxis.setDrawAxisLine(false);

            YAxis rightAxis = mLineChart.getAxisRight();
            rightAxis.setDrawLabels(false);
            rightAxis.setStartAtZero(false);

            rightAxis.setGridColor(Color.LTGRAY);
            rightAxis.setGridLineWidth(2f);
            rightAxis.setDrawGridLines(true);
            rightAxis.setDrawAxisLine(false);

        }

        private void setUpLineChart() {
            mLineChart.setGridBackgroundColor(UIUtils.getColor(R.color.white));
            mLineChart.setDrawGridBackground(false);
            mLineChart.setOnChartValueSelectedListener(null);
            mLineChart.setDescription("");
            mLineChart.setScaleMinima(1.5f, 1f);
            mLineChart.setHorizontalScrollBarEnabled(true);
            mLineChart.moveViewToX(1);

            drawLines();
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

    public interface ProductListener {
        public void onRatingAdded(int productId, String comment, float rating);

        public void onAddToCart(int productId, int quantity);
    }

}
