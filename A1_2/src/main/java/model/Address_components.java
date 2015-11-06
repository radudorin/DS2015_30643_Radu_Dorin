package model;

/**
 * Created by radud on 05/11/2015.
 */
public class Address_components {
    private String long_name;

    private String[] types;

    public String[] getTypes() {
        return types;
    }

    public void setTypes(String[] types) {
        this.types = types;
    }

    public String getLong_name() {
        return long_name;
    }

    public void setLong_name(String long_name) {
        this.long_name = long_name;
    }

    @Override
    public String toString() {
        return "ClassPojo [long_name = " + long_name;
    }
}
