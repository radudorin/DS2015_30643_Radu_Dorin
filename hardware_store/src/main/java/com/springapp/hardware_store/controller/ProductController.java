package com.springapp.hardware_store.controller;

import com.springapp.hardware_store.dao.*;
import com.springapp.hardware_store.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by radud on 03/12/2015.
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ApplicationContext appContext;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public
    @ResponseBody
    Result<List<Product>> getProducts() {
        ProductDAO productDAO = (ProductDAO) appContext.getBean("productDao");
        List<Product> products = productDAO.findAll();

        Result<List<Product>> result = new Result<List<Product>>();

        if (products == null || products.isEmpty()) {
            result.setHasErrors(true);
            result.setMessage("No products found");
            return result;
        }

        result.setHasErrors(false);
        result.setResponse(products);

        return result;
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    Result<Product> getProducts(@PathVariable("id") int id) {
        ProductDAO productDAO = (ProductDAO) appContext.getBean("productDao");
        Product product = productDAO.findById(id);

        Result<Product> result = new Result<Product>();

        if (product == null) {
            result.setHasErrors(true);
            result.setMessage("Product not found.");
            return result;
        }

        result.setHasErrors(false);
        result.setResponse(product);

        return result;
    }

    @RequestMapping(value = "/category/get/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    Result<List<Product>> getProductsByCategory(@PathVariable("id") int id) {
        ProductDAO productDAO = (ProductDAO) appContext.getBean("productDao");
        List<Product> products = productDAO.findProductsForCategory(id);
        Result<List<Product>> result = new Result<List<Product>>();

        if (products == null || products.isEmpty()) {
            result.setHasErrors(true);
            result.setMessage("No products found");
            return result;
        }

        result.setHasErrors(false);
        result.setResponse(products);

        return result;
    }

    @RequestMapping(value = "/category/get", method = RequestMethod.GET)
    public
    @ResponseBody
    Result getProductsByCategory() {
        ProductCategoryDAO productCategoryDAO = (ProductCategoryDAO) appContext.getBean("productCategoryDao");
        List<ProductCategory> productCategories = productCategoryDAO.findAll();

        Result<List<ProductCategory>> result = new Result<List<ProductCategory>>();

        if (productCategories.isEmpty()) {
            result.setHasErrors(true);
            result.setMessage("Categories are empty");
        }

        result.setHasErrors(false);
        result.setResponse(productCategories);

        return result;
    }

    @RequestMapping(value = "/rating/get/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    Result<List<Rating>> getRatingsForProduct(@PathVariable("id") int id) {
        RatingDAO ratingDAO = (RatingDAO) appContext.getBean("ratingDao");
        List<Rating> ratings = ratingDAO.findRatingsForProduct(id);

        Result<List<Rating>> result = new Result<List<Rating>>();

        if (ratings.isEmpty()) {
            result.setHasErrors(false);
            result.setMessage("Categories are empty");
        }

        result.setHasErrors(false);
        result.setResponse(ratings);

        return result;
    }

    @RequestMapping(value = "/rating/add/{id}", method = RequestMethod.POST)
    public
    @ResponseBody
    Result getRatingsForProduct(@RequestParam(value = "memberId") int memberId, @RequestBody Rating rating, @PathVariable("id") int id) {
        RatingDAO ratingDAO = (RatingDAO) appContext.getBean("ratingDao");
        MemberDAO memberDAO = (MemberDAO) appContext.getBean("memberDao");
        ProductDAO productDAO = (ProductDAO) appContext.getBean("productDao");

        rating.setMember(memberDAO.findById(memberId));
        rating.setProduct(productDAO.findById(id));
        ratingDAO.save(rating);

        Result result = new Result();
        result.setHasErrors(false);
        result.setMessage("Success");

        return result;
    }

    @RequestMapping(value = "/subscription/add/{id}", method = RequestMethod.POST)
    public
    @ResponseBody
    Result addSubscription(@RequestParam(value = "memberId") int memberId, @PathVariable("id") int id) {
        SubscriptionDAO subscriptionDAO = (SubscriptionDAO) appContext.getBean("subscriptionDao");
        MemberDAO memberDAO = (MemberDAO) appContext.getBean("memberDao");
        ProductDAO productDAO = (ProductDAO) appContext.getBean("productDao");

        Subscription subscription = new Subscription();

        subscription.setMember(memberDAO.findById(memberId));
        subscription.setProduct(productDAO.findById(id));
        subscriptionDAO.save(subscription);

        Result result = new Result();
        result.setHasErrors(false);
        result.setMessage("Success");

        return result;
    }

    @RequestMapping(value = "subscription/delete/{id}", method = RequestMethod.DELETE)
    public
    @ResponseBody
    Result deleteSubscription(@PathVariable("id") int id, @RequestParam(value = "memberId") int memberId) {
        SubscriptionDAO subscriptionDAO = (SubscriptionDAO) appContext.getBean("subscriptionDao");
        subscriptionDAO.delete(id, memberId);

        Result result = new Result();
        result.setHasErrors(false);
        result.setMessage("Success");

        return result;
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public
    @ResponseBody
    Product deleteProduct(@PathVariable("id") int id) {
        ProductDAO productDAO = (ProductDAO) appContext.getBean("productDao");
        Product product = productDAO.delete(id);

        return product;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public
    @ResponseBody
    Result register(@RequestBody Product product) {
        ProductDAO productDAO = (ProductDAO) appContext.getBean("productDao");
        ProductCategoryDAO productCategoryDAO = (ProductCategoryDAO) appContext.getBean("productCategoryDao");

        Result result = new Result();

        if (product == null) {
            result.setHasErrors(true);
            result.setMessage("Member is null");
            return result;
        }

        ProductCategory productCategory = productCategoryDAO.getByField("name", product.getCategory().getName());
        product.setCategory(productCategory);

        productDAO.save(product);

        result.setHasErrors(false);
        return result;
    }


}
