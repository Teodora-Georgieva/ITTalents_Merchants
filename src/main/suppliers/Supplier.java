package main.suppliers;

import main.shops.Product;
import main.utils.Generator;
import main.utils.Validator;

import java.util.ArrayList;
import java.util.Objects;

public abstract class Supplier {
    private String name;
    private String address;
    private String workTime;
    private ArrayList<Product> products;
    private double money;

    Supplier(String name, String address, String workTime){
        if(Validator.isValidString(name)){
            this.name = name;
        }
        else{
            this.name = "Petar Petrov";
        }

        if(Validator.isValidString(address)){
            this.address = address;
        }
        else{
            this.address = "Sofia, Vitosha";
        }

        if(Validator.isValidString(workTime)){
            this.workTime = workTime;
        }
        else{
            this.workTime = "07:00 - 19:00";
        }

        this.products = new ArrayList<>();
        this.money = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Supplier supplier = (Supplier) o;
        return Objects.equals(name, supplier.name) &&
                Objects.equals(address, supplier.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, address);
    }

    public Product giveProduct(){
        int idx = Generator.generateRandomNumber(0, this.products.size() - 1);
        Product p = this.products.get(idx);
        this.products.remove(idx);
        return p;
    }

    public void addProduct(Product p){
        this.products.add(p);
    }

    public abstract double getDiscountPercent();

    public void receiveMoney(double money){
        if(money > 0){
            this.money+=money;
        }
    }
}