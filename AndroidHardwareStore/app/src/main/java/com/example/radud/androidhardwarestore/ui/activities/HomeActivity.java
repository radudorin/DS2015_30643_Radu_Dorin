package com.example.radud.androidhardwarestore.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.radud.androidhardwarestore.R;
import com.example.radud.androidhardwarestore.model.ProductCategory;
import com.example.radud.androidhardwarestore.model.Result;
import com.example.radud.androidhardwarestore.sync.ApiHelper;
import com.example.radud.androidhardwarestore.ui.adapters.CategoriesAdapter;
import com.example.radud.androidhardwarestore.ui.interfaces.OnItemSelectedListener;
import com.example.radud.androidhardwarestore.utils.ToastUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by radud on 02/01/2016.
 */
public class HomeActivity extends AppCompatActivity {

    @Bind(R.id.ah_categories_rv)
    RecyclerView mCategoriesRV;

    List<ProductCategory> mProductCategories;
    CategoriesAdapter mCategoriesAdapter;
    OnItemSelectedListener mListener = new OnItemSelectedListener() {
        @Override
        public void onItemSelected(int id) {
            Intent intent = new Intent(HomeActivity.this, ProductsActivity.class);
            intent.putExtra(ProductsActivity.CATEGORIES_ID_KEY, id);
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Home");
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        loadCategories();
    }

    private void setUpRV() {
        mCategoriesRV.setHasFixedSize(true);
        mCategoriesRV.setLayoutManager(new GridLayoutManager(this, 2));
        mCategoriesAdapter = new CategoriesAdapter(mProductCategories, mListener);
        mCategoriesRV.setAdapter(mCategoriesAdapter);
    }

    private void loadCategories() {
        ApiHelper.getApi().getCategories(new Callback<Result<List<ProductCategory>>>() {
            @Override
            public void success(Result<List<ProductCategory>> listResult, Response response) {
                if (!listResult.getHasErrors() && listResult.getResponse() != null) {
                    mProductCategories = listResult.getResponse();
                    setUpRV();
                } else {
                    ToastUtils.showError(HomeActivity.this, listResult.getMessage());
                }
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });

    }
}
