package model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by radud on 05/11/2015.
 */
public class Results {
    private String place_id;

    private Geometry geometry;

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    @SerializedName("address_components")
    private List<Address_components> address_components;

    public List<Address_components> getAddress_components() {
        return address_components;
    }

    public void setAddress_components(List<Address_components> address_components) {
        this.address_components = address_components;
    }

    @Override
    public String toString() {
        return "ClassPojo [place_id = " + place_id + ", address_components = " + address_components;
    }
}