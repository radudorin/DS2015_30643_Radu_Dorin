package model;

import java.io.Serializable;

/**
 * Created by radud on 13/12/2015.
 */
public class DVD implements Serializable {

    private static final long serialVersionUID = 1L;

    private String title;
    private double price;
    private int year;

    public DVD(String title, double price, int year) {
        super();
        this.title = title;
        this.price = price;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "DVD [title=" + title + ", price=" + price + ", year=" + year + "]";
    }

}
