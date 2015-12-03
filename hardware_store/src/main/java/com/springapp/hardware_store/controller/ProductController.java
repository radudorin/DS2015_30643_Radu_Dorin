package com.springapp.hardware_store.controller;

import com.springapp.hardware_store.dao.ProductCategoryDAO;
import com.springapp.hardware_store.dao.ProductDAO;
import com.springapp.hardware_store.model.Product;
import com.springapp.hardware_store.model.ProductCategory;
import com.springapp.hardware_store.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    List<Product> getProducts() {
        ProductDAO productDAO = (ProductDAO) appContext.getBean("productDao");
        List<Product> products = productDAO.findAll();

        return products;
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    Product getProducts(@PathVariable("id") int id) {
        ProductDAO productDAO = (ProductDAO) appContext.getBean("productDao");
        Product product = productDAO.findById(id);

        return product;
    }

    @RequestMapping(value = "/category/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Product> getProductsByCategory(@PathVariable("id") int id) {
        ProductDAO productDAO = (ProductDAO) appContext.getBean("productDao");
        List<Product> products = productDAO.findProductsForCategory(id);

        return products;
    }

    @RequestMapping(value = "/category/get", method = RequestMethod.GET)
    public
    @ResponseBody
    List<ProductCategory> getProductsByCategory() {
        ProductCategoryDAO productCategoryDAO = (ProductCategoryDAO) appContext.getBean("productCategoryDao");
        List<ProductCategory> productCategories = productCategoryDAO.findAll();

        return productCategories;
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

        productDAO.saveOrUpdate(product);

        result.setHasErrors(false);
        return result;
    }
}
