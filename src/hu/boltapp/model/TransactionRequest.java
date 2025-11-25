package hu.boltapp.model;

public class TransactionRequest {

    private String cikkszam;
    private int mennyiseg;

    public TransactionRequest(String cikkszam, int mennyiseg) {
        this.cikkszam = cikkszam;
        this.mennyiseg = mennyiseg;
    }

    public TransactionRequest() {}

    public String getCikkszam() {
        return cikkszam;
    }

    public int getMennyiseg() {
        return mennyiseg;
    }

    public void setCikkszam(String cikkszam) {
        this.cikkszam = cikkszam;
    }

    public void setMennyiseg(int mennyiseg) {
        this.mennyiseg = mennyiseg;
    }
}
