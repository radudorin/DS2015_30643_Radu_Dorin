/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.a4.javawebservice.model;

import java.io.Serializable;

/**
 *
 * @author radud
 */
public class Route implements Serializable {

    private int id;
    private String arrivalTime;
    private String city;
    private String status;
    private int packageId;
    private int userId;

    public Route() {
    }

    public Route(String arrivalTime, String city, String status, int packageId, int userId) {
        this.arrivalTime = arrivalTime;
        this.city = city;
        this.status = status;
        this.packageId = packageId;
        this.userId = userId;
    }

    public Route(int id, String arrivalTime, String city, String status, int packageId, int userId) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.city = city;
        this.status = status;
        this.packageId = packageId;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPackageId() {
        return packageId;
    }

    public void setPackageId(int packageId) {
        this.packageId = packageId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
