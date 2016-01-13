package com.example.radud.androidhardwarestore.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.radud.androidhardwarestore.R;
import com.example.radud.androidhardwarestore.model.Order;
import com.example.radud.androidhardwarestore.model.Result;
import com.example.radud.androidhardwarestore.sync.ApiHelper;
import com.example.radud.androidhardwarestore.ui.activities.ProductsActivity;
import com.example.radud.androidhardwarestore.ui.adapters.OrdersAdapter;
import com.example.radud.androidhardwarestore.ui.interfaces.OnItemSelectedListener;
import com.example.radud.androidhardwarestore.utils.SessionUtils;
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
public class OrdersFragment extends BaseFragment {

    @Bind(R.id.fo_content_rv)
    RecyclerView mContentRV;

    OnItemSelectedListener mListener = new OnItemSelectedListener() {
        @Override
        public void onItemSelected(int id) {
            Intent intent = new Intent(getActivity(), ProductsActivity.class);
            intent.putExtra(ProductsActivity.CATEGORIES_ID_KEY, id);
            startActivity(intent);
        }
    };

    List<Order> mOrders;
    OrdersAdapter mOrdersAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadOrders();
    }

    @Override
    public String getTitle() {
        return "Orders";
    }

    private void loadOrders() {
        ApiHelper.getApi().getOrders(SessionUtils.getUserId(), new Callback<Result<List<Order>>>() {
            @Override
            public void success(Result<List<Order>> listResult, Response response) {
                if (listResult.getHasErrors() || listResult.getResponse() == null) {
                    ToastUtils.showError(getActivity(), listResult.getMessage());
                } else {
                    mOrders = listResult.getResponse();
                    setUpRV();
                }
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    private void setUpRV() {
        mContentRV.setHasFixedSize(true);
        mContentRV.setLayoutManager(new LinearLayoutManager(getActivity()));
        mOrdersAdapter = new OrdersAdapter(mOrders, mListener);
        mContentRV.setAdapter(mOrdersAdapter);
    }

}
