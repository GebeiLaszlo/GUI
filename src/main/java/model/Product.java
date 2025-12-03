package model;

public class Product {


    public String name;
    public String category;

    public int stock;
    public double sellingPrice;

    public String description;

    public Product() {
        this.name = name;
        this.category = category;
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

    private Long id;
    private String cikkszam;
    private String nev;
    private int mennyiseg;
    private double ar;

    public Long getId() {
        return id;
    }

    public String getCikkszam() {
        return cikkszam;
    }

    public String getNev() {
        return nev;
    }

    public int getMennyiseg() {
        return mennyiseg;
    }

    public double getAr() {
        return ar;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCikkszam(String cikkszam) {
        this.cikkszam = cikkszam;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public void setMennyiseg(int mennyiseg) {
        this.mennyiseg = mennyiseg;
    }

    public void setAr(double ar) {
        this.ar = ar;
    }
}
