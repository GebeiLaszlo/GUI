package service;

import model.Product;

import java.util.ArrayList;
import java.util.List;

public class MockProductService {

    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();

        products.add(new Product("Laptop", "Számítógép", 1200, 499_000, "15.6\" i7, 16 GB RAM"));
        products.add(new Product("Okostelefon", "Mobil", 800, 299_000, "128 GB, AMOLED kijelző"));
        products.add(new Product("Televízió", "TV", 300, 189_000, "55\" 4K UHD"));
        products.add(new Product("Vezeték nélküli egér", "Periféria", 500, 5_990, "Ergonomikus kialakítás"));
        products.add(new Product("USB-C kábel", "Kiegészítő", 1500, 2_490, "1.5 m, gyorstöltés"));

        products.add(new Product("Játék konzol", "Konzol", 200, 189_990, "Legújabb generáció"));
        products.add(new Product("Kontroller", "Konzol", 400, 23_990, "Vezeték nélküli"));
        products.add(new Product("Bluetooth hangszóró", "Hangtechnika", 350, 29_990, "Vízálló, hordozható"));
        products.add(new Product("Fejhallgató", "Hangtechnika", 600, 19_990, "Zajszűrős"));
        products.add(new Product("Soundbar", "Hangtechnika", 150, 89_990, "2.1-es rendszer, mélynyomóval"));

        products.add(new Product("Monitor 24\"", "Monitor", 260, 59_990, "Full HD, IPS"));
        products.add(new Product("Monitor 27\"", "Monitor", 180, 89_990, "QHD, 144 Hz"));
        products.add(new Product("Nyomtató", "Iroda", 90, 49_990, "Wi-Fi, multifunkciós"));
        products.add(new Product("Router", "Hálózat", 220, 24_990, "Wi-Fi 6"));
        products.add(new Product("Switch 8 port", "Hálózat", 140, 14_990, "Gigabites, 8 port"));

        products.add(new Product("SSD 1 TB", "Tároló", 320, 39_990, "NVMe"));
        products.add(new Product("HDD 2 TB", "Tároló", 210, 29_990, "3.5\", 7200 rpm"));
        products.add(new Product("Pendrive 64 GB", "Tároló", 900, 5_490, "USB 3.2"));
        products.add(new Product("Memóriakártya 128 GB", "Tároló", 450, 12_990, "UHS-I"));

        products.add(new Product("Tablet", "Tablet", 190, 129_990, "10.5\" kijelző"));
        products.add(new Product("Okosóra", "Wearable", 260, 79_990, "Pulzusmérő, GPS"));
        products.add(new Product("Laptop táska", "Kiegészítő", 340, 9_990, "15.6\"-os laptophoz"));
        products.add(new Product("Billentyűzet", "Periféria", 380, 12_990, "Mechanikus, háttérvilágításos"));
        products.add(new Product("Webkamera", "Periféria", 170, 18_990, "Full HD, mikrofonos"));

        return products;
    }
}
