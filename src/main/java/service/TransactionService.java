package service;
import model.TransactionRequest;

public class TransactionService {

    public void sell(String cikkszam, int db) throws Exception {
        TransactionRequest req = new TransactionRequest(cikkszam, db);
        ApiClient.post("/sell", req, Void.class);
    }

    public void buy(String cikkszam, int db) throws Exception {
        TransactionRequest req = new TransactionRequest(cikkszam, db);
        ApiClient.post("/buy", req, Void.class);
    }
}