package main.merchants;

import main.suppliers.Supplier;
import main.utils.Validator;


public abstract class Merchant {
    private String name;
    private String address;
    private double money;
    int countOfSoldStocks;
    double paidTaxes;

    Merchant(String name, String address, double money){
        if(Validator.isValidString(name)){
            this.name = name;
        }
        else{
            this.name = "Ivan Ivanov";
        }

        if(Validator.isValidString(address)){
            this.address = address;
        }
        else{
            this.address = "Sofia, Lyulin 5";
        }

        if(money > 0){
            this.money = money;
        }
        else{
            this.money = 1000;
        }

        this.countOfSoldStocks = 0;
        this.paidTaxes = 0;
    }

    abstract int getMaxCountOfSuppliers();

    public abstract void receiveProfit();
    public abstract Supplier getSupplier();

    public String getName() {
        return name;
    }

    void decreaseMoney(double amount){
        this.money-=amount;
    }

    public double getMoney() {
        return money;
    }

    void increaseMoney(double amount){
        this.money+=amount;
    }

    public double getPaidTaxes() {
        return paidTaxes;
    }

    public abstract void makeOrderToSupplier(Supplier supplier);
    public abstract void showListOfProducts();
    public abstract void payTax();

    public int getCountOfSoldStocks() {
        return countOfSoldStocks;
    }
}