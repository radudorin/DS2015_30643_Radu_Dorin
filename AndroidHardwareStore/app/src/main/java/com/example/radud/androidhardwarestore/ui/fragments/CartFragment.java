package com.example.radud.androidhardwarestore.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.radud.androidhardwarestore.R;
import com.example.radud.androidhardwarestore.model.Result;
import com.example.radud.androidhardwarestore.model.ShoppingCart;
import com.example.radud.androidhardwarestore.sync.ApiHelper;
import com.example.radud.androidhardwarestore.ui.activities.ProductsActivity;
import com.example.radud.androidhardwarestore.ui.adapters.CartItemsAdapter;
import com.example.radud.androidhardwarestore.ui.interfaces.OnItemSelectedListener;
import com.example.radud.androidhardwarestore.utils.SessionUtils;
import com.example.radud.androidhardwarestore.utils.ToastUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by radud on 06/01/2016.
 */
public class CartFragment extends BaseFragment {

    @Bind(R.id.fc_content_rv)
    RecyclerView mContentRV;
    @Bind(R.id.fc_total)
    TextView mTotalTV;
    @Bind(R.id.fc_total_items)
    TextView mTotalItemsTV;

    private ShoppingCart mShoppingCart;
    OnItemSelectedListener mListener = new OnItemSelectedListener() {
        @Override
        public void onItemSelected(int id) {
            Intent intent = new Intent(getActivity(), ProductsActivity.class);
            intent.putExtra(ProductsActivity.CATEGORIES_ID_KEY, id);
            startActivity(intent);
        }
    };
    CartItemsAdapter mCartItemsAdapter;

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

    private void setUpRV() {
        mContentRV.setHasFixedSize(true);
        mContentRV.setLayoutManager(new LinearLayoutManager(getActivity()));
        mCartItemsAdapter = new CartItemsAdapter(mShoppingCart.getCartItems(), mListener);
        mContentRV.setAdapter(mCartItemsAdapter);
    }

    private void setViewsForShoppingCart() {

        setUpRV();
        mTotalTV.setText("Total : " + String.valueOf(mShoppingCart.getTotal()));
        mTotalItemsTV.setText("Total items : " + String.valueOf(mShoppingCart == null ? 0 : mShoppingCart.getCartItems().size()));

    }

    /*TextFormatter.formatQuantity(
                    holder.mProductVendor.getCartQuantity(), isAllowFraction) + " / " +
                    TextFormatter.formatPrice(holder.mProductVendor.getCartTotalPrice()));*/
}
