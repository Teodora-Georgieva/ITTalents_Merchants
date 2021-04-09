package main.shops;

import java.util.ArrayList;
import java.util.HashMap;

public interface IShop {
    void addProducts(ArrayList<Product> orderedProducts);
    HashMap<Integer, Double> sellProducts();

    int getTax();
    ArrayList<Product> getProducts();
    String getAddress();
}
