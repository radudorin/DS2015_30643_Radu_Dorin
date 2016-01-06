package com.example.radud.androidhardwarestore.sync;

import com.example.radud.androidhardwarestore.model.Member;
import com.example.radud.androidhardwarestore.model.Product;
import com.example.radud.androidhardwarestore.model.ProductCategory;
import com.example.radud.androidhardwarestore.model.Rating;
import com.example.radud.androidhardwarestore.model.Result;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by profiralexandr on 10/11/15.
 */
public interface HardwareStoreApi {

    @GET("/member/get/{id}")
    void getMember(@Path("id") int memberId, Callback<Member> member);

    @GET("/product/category/get/{id}")
    void getProducts(@Path("id") int categoryId, Callback<Result<List<Product>>> products);

    @GET("/product/get/{id}")
    void getProduct(@Path("id") int productId, Callback<Result<Product>> product);

    @GET("/product/rating/get/{id}")
    void getRatings(@Path("id") int productId, Callback<Result<List<Rating>>> ratings);

    @GET("/product/get")
    void getProducts(Callback<Result<List<Product>>> products);

    @GET("/product/category/get")
    void getCategories(Callback<Result<List<ProductCategory>>> categories);

    @FormUrlEncoded
    @POST("/member/login")
    void login(@Field("username") String username, @Field("password") String password, Callback<Result<Member>> callback);

    @POST("/member/register")
    void register(@Body Member member, Callback<Result<Member>> callback);
}
