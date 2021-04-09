package main.shops;

import main.utils.Generator;
import main.utils.Validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public abstract class Shop implements IShop{
    private String address;
    private String workTime;
    private int area;
    private int tax;
    private ArrayList<Product> products;

    Shop(String address, String workTime, int area){
        if(Validator.isValidString(address)){
            this.address = address;
        }
        else{
            this.address = "Sofia, Nadejda 2";
        }

        if(Validator.isValidString(workTime)){
            this.workTime = workTime;
        }
        else{
            this.workTime = "09:00 - 20:00";
        }

        if(validateArea(area)){
            this.area = area;
        }
        else{
            this.area = setDefaultArea();
        }

        tax = setDefaultTax();
        this.products = new ArrayList<>();
    }

    abstract boolean validateArea(int area);
    abstract int setDefaultArea();
    abstract int setDefaultTax();

    public void addProducts(ArrayList<Product> products){
        this.products.addAll(products);
        this.products.sort((p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice()));
    }

    public HashMap<Integer, Double> sellProducts() {
        int countOfSoldProducts = Generator.generateRandomNumber(1, this.products.size());
        double profit = 0;
        for (int i = 0; i < countOfSoldProducts; i++) {
            int idx = Generator.generateRandomNumber(0, this.products.size() - 1);
            double priceOfProduct = this.products.get(idx).getPrice();
            profit += priceOfProduct;
            this.products.remove(idx);
        }
        profit = profit + 0.3 * profit;
        HashMap<Integer, Double> countOfProductsAndTotalPrice = new HashMap<>();
        countOfProductsAndTotalPrice.put(countOfSoldProducts, profit);
        return countOfProductsAndTotalPrice;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shop shop = (Shop) o;
        return area == shop.area &&
                Objects.equals(address, shop.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, area);
    }

    public int getTax() {
        return tax;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }
}