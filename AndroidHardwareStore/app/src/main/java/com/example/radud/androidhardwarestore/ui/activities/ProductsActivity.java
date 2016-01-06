package com.example.radud.androidhardwarestore.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.SearchView;

import com.example.radud.androidhardwarestore.R;
import com.example.radud.androidhardwarestore.model.Product;
import com.example.radud.androidhardwarestore.model.Result;
import com.example.radud.androidhardwarestore.sync.ApiHelper;
import com.example.radud.androidhardwarestore.ui.adapters.ProductsAdapter;
import com.example.radud.androidhardwarestore.ui.interfaces.OnItemSelectedListener;
import com.example.radud.androidhardwarestore.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by radud on 03/01/2016.
 */
public class ProductsActivity extends AppCompatActivity {

    public static final String CATEGORIES_ID_KEY = "CATEGORIES_ID_KEY";

    @Bind(R.id.ap_products_rv)
    RecyclerView mProductsRV;
    @Bind(R.id.ap_sv)
    SearchView mSearchView;

    List<Product> mProducts;
    List<Product> mFilteredProducts;

    ProductsAdapter mProductAdapter;
    OnItemSelectedListener mListener = new OnItemSelectedListener() {
        @Override
        public void onItemSelected(int id) {
            ToastUtils.showError(ProductsActivity.this, id + "");
            Intent intent = new Intent(ProductsActivity.this, ProductDetailsActivity.class);
            intent.putExtra(ProductDetailsActivity.PRODUCT_ID_KEY, id);
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Products");
        setContentView(R.layout.activity_products);
        ButterKnife.bind(this);
        int categoryId = 0;
        if (getIntent() != null) {
            categoryId = getIntent().getIntExtra(CATEGORIES_ID_KEY, -1);
        }
        setUpSearchView();
        loadProducts(categoryId);
    }

    private void setUpSearchView() {
        mFilteredProducts = new ArrayList<>();
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                if (newText.equals("")) {
                    mProductAdapter.setProducts(mProducts);
                } else {
                    mFilteredProducts.clear();
                    for (Product product : mProducts) {
                        if (product.getName().toLowerCase().contains(newText.toLowerCase())) {
                            mFilteredProducts.add(product);
                        }
                    }
                    mProductAdapter.setProducts(mFilteredProducts);
                }
                return false;
            }
        });
    }

    private void setUpRV() {
        mProductsRV.setLayoutManager(new LinearLayoutManager(ProductsActivity.this));
        mProductAdapter = new ProductsAdapter(mProducts, mListener);
        mProductsRV.setAdapter(mProductAdapter);
    }

    private void loadProducts(int categoryId) {
        Callback<Result<List<Product>>> callback = new Callback<Result<List<Product>>>() {
            @Override
            public void success(Result<List<Product>> productsResult, Response response) {
                if (!productsResult.getHasErrors() && productsResult.getResponse() != null) {
                    mProducts = productsResult.getResponse();
                    setUpRV();
                } else {
                    ToastUtils.showError(ProductsActivity.this, productsResult.getMessage());
                }
            }

            @Override
            public void failure(RetrofitError error) {

            }
        };

        if (categoryId != -1) {
            ApiHelper.getApi().getProducts(categoryId, callback);
        } else {
            ApiHelper.getApi().getProducts(callback);
        }
    }

}
