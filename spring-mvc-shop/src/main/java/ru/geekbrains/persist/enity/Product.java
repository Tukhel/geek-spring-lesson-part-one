package ru.geekbrains.persist.enity;

public class Product {

    private int id;
    private String title;
    private double coat;

    public Product() {
    }

    public Product(int id, String title, double coat) {
        this.id = id;
        this.title = title;
        this.coat = coat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getCoat() {
        return coat;
    }

    public void setCoat(double coat) {
        this.coat = coat;
    }
}
