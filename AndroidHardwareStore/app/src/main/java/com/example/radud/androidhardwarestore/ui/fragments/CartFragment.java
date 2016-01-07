package com.example.radud.androidhardwarestore.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.radud.androidhardwarestore.R;
import com.example.radud.androidhardwarestore.model.Result;
import com.example.radud.androidhardwarestore.model.ShoppingCart;
import com.example.radud.androidhardwarestore.sync.ApiHelper;
import com.example.radud.androidhardwarestore.utils.SessionUtils;
import com.example.radud.androidhardwarestore.utils.ToastUtils;

import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by radud on 06/01/2016.
 */
public class CartFragment extends BaseFragment {

    ShoppingCart mShoppingCart;

    @Override
    public String getTitle() {
        return "Cart";
    }

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
        loadShoppingCart();
    }

    private void loadShoppingCart() {
        ApiHelper.getApi().getShoppingCart(SessionUtils.getUserId(), new Callback<Result<ShoppingCart>>() {
            @Override
            public void success(Result<ShoppingCart> shoppingCartResult, Response response) {
                if (shoppingCartResult.getHasErrors() || shoppingCartResult.getResponse() == null) {
                    ToastUtils.showError(getActivity(), shoppingCartResult.getMessage());
                } else {
                    mShoppingCart = shoppingCartResult.getResponse();
                    setViewsForShoppingCart();
                }
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    private void setViewsForShoppingCart() {

    }

    /*TextFormatter.formatQuantity(
                    holder.mProductVendor.getCartQuantity(), isAllowFraction) + " / " +
                    TextFormatter.formatPrice(holder.mProductVendor.getCartTotalPrice()));*/
}
