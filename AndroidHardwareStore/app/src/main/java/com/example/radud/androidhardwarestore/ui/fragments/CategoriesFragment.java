package com.example.radud.androidhardwarestore.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.radud.androidhardwarestore.R;
import com.example.radud.androidhardwarestore.model.ProductCategory;
import com.example.radud.androidhardwarestore.model.Result;
import com.example.radud.androidhardwarestore.sync.ApiHelper;
import com.example.radud.androidhardwarestore.ui.activities.ProductsActivity;
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
 * Created by radud on 06/01/2016.
 */
public class CategoriesFragment extends BaseFragment {

    @Bind(R.id.fc_categories_rv)
    RecyclerView mCategoriesRV;

    List<ProductCategory> mProductCategories;
    CategoriesAdapter mCategoriesAdapter;
    OnItemSelectedListener mListener = new OnItemSelectedListener() {
        @Override
        public void onItemSelected(int id) {
            Intent intent = new Intent(getActivity(), ProductsActivity.class);
            intent.putExtra(ProductsActivity.CATEGORIES_ID_KEY, id);
            startActivity(intent);
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categories, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadCategories();
    }

    @Override
    public String getTitle() {
        return "Categories";
    }

    private void setUpRV() {
        mCategoriesRV.setHasFixedSize(true);
        mCategoriesRV.setLayoutManager(new GridLayoutManager(getActivity(), 2));
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
                    ToastUtils.showError(getActivity(), listResult.getMessage());
                }
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });

    }

}
