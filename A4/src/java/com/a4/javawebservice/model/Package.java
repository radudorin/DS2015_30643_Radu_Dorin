/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.a4.javawebservice.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author radud
 */
@XmlRootElement
public class Package implements Serializable {

    private int id;

    private int receiverId;

    private int senderId;

    private String description;

    private String name;

    private String destinationCity;

    private String senderCity;

    private boolean tracking;

    public Package() {
    }

    public Package(int id, int receiverId, int senderId, String description, String name, String destinationCity, String senderCity, boolean tracking) {
        this.id = id;
        this.receiverId = receiverId;
        this.senderId = senderId;
        this.description = description;
        this.name = name;
        this.destinationCity = destinationCity;
        this.senderCity = senderCity;
        this.tracking = tracking;
    }
    
    public Package(int receiverId, int senderId, String description, String name, String destinationCity, String senderCity, boolean tracking) {
        this.receiverId = receiverId;
        this.senderId = senderId;
        this.description = description;
        this.name = name;
        this.destinationCity = destinationCity;
        this.senderCity = senderCity;
        this.tracking = tracking;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    public String getSenderCity() {
        return senderCity;
    }

    public void setSenderCity(String senderCity) {
        this.senderCity = senderCity;
    }

    public boolean isTracking() {
        return tracking;
    }

    public void setTracking(boolean tracking) {
        this.tracking = tracking;
    }

}
