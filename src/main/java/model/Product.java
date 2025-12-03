package model;

public class Product {

    public int id;

    public String name;
    public String category;

    public int stock;
    public double sellingPrice;

    public String description;

    public Product(String name, int stock, double sellingPrice) {
        this.name = name;
        this.stock = stock;
        this.sellingPrice = sellingPrice;
        this.category = "Egyéb";
        this.description = "";
    }

    public Product(String name, String category, int stock, double sellingPrice, String description) {
        this.name = name;
        this.category = category;
        this.stock = stock;
        this.sellingPrice = sellingPrice;
        this.description = description;
    }

    public Product() {
    }

}
