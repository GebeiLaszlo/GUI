package service;

import model.Product;

public class ProductService {
    public Product[] getAll() throws Exception {
        return ApiClient.get("/products", Product[].class);
    }

    public Product getByCikkszam(String cikkszam) throws Exception {
        return ApiClient.get("/products/" + cikkszam, Product.class);
    }

    public void add(Product p) throws Exception {
        ApiClient.post("/products", p, Void.class);
    }

    public void update(Product p) throws Exception {
        ApiClient.post("/products/update", p, Void.class);
    }

    public void delete(Long id) throws Exception {
        ApiClient.post("/products/delete/" + id, null, Void.class);
    }
}
