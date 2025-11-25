package hu.boltapp.model;

public class Product {
    private Long id;
    private String cikkszam;
    private String nev;
    private int mennyiseg;
    private double ar;

    public Long getId() { return id; }
    public String getCikkszam() { return cikkszam; }
    public String getNev() { return nev; }
    public int getMennyiseg() { return mennyiseg; }
    public double getAr() { return ar; }

public void setId(Long id) { this.id = id; }
public void setCikkszam(String cikkszam) { this.cikkszam = cikkszam; }
public void setNev(String nev) { this.nev = nev; }
public void setMennyiseg(int mennyiseg) { this.mennyiseg = mennyiseg; }
public void setAr(double ar) { this.ar = ar; }
}
