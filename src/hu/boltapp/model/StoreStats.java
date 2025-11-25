package hu.boltapp.model;

public class StoreStats {

    private double profit;
    private double kiadas;
    private int szabadHely;

    public double getProfit() {
        return profit;
    }

    public double getKiadas() {
        return kiadas;
    }

    public int getSzabadHely() {
        return szabadHely;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public void setKiadas(double kiadas) {
        this.kiadas = kiadas;
    }

    public void setSzabadHely(int szabadHely) {
        this.szabadHely = szabadHely;
    }
}
