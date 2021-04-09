package main.shops;

import main.utils.Validator;

public class Product {
    private String name;
    private double price;

    public Product(String name, double price){
        if(Validator.isValidString(name)){
            this.name = name;
        }
        else{
            this.name = "book";
        }

        if(price >= 5 && price <= 15){
            this.price = price;
        }
        else{
            this.price = 10;
        }
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String
    toString() {
        return "product name: " + name + ", price: " + price;
    }
}