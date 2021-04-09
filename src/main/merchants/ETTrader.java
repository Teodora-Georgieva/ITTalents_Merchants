package main.merchants;

import main.shops.IETShop;
import main.suppliers.RetailSupplier;
import main.suppliers.Supplier;
import main.utils.Generator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class ETTrader extends NonAmbulant{
    private HashSet<RetailSupplier> suppliers;
    private IETShop shop;

    public ETTrader(String name, String address, double money) {
        super(name, address, money);
        this.suppliers = new HashSet<>();
    }

    @Override
    int getMaxCountOfSuppliers() {
        return 5;
    }

    @Override
    public void receiveProfit() {
        HashMap<Integer, Double> countOfSoldStocksAndTotalPrice = shop.sellProducts();
        Map.Entry<Integer, Double> entry = countOfSoldStocksAndTotalPrice.entrySet().iterator().next();
        int countOfSoldStocks = entry.getKey();
        double profit = entry.getValue();
        this.increaseMoney(profit);
        this.countOfSoldStocks+=countOfSoldStocks;
        System.out.println("Profit ET: " + profit);
    }

    public void payTax(){
        int tax = this.shop.getTax();
        this.paidTaxes = tax;
        this.decreaseMoney(tax);
        System.out.println("Paid tax ET: " + tax);
    }

    public Supplier getSupplier() {
        int idx = Generator.generateRandomNumber(0, this.suppliers.size() - 1);
        ArrayList<Supplier> s = new ArrayList<>();
        s.addAll(this.suppliers);
        return s.get(idx);
    }

    @Override
    public void makeOrderToSupplier(Supplier supplier) {
        makeOrder(supplier, this.shop);
    }

    @Override
    public void showListOfProducts() {
        System.out.println("Showing list of products of ET Merchant: ");
        showProducts(this.shop);
    }

    public void addShop(IETShop shop){
        if(this.shop == null){
            this.shop = shop;
        }
        else{
            System.out.println("The ET Trader " + this.getName() + " already has a shop");
        }
    }

    public void setSupplier(RetailSupplier supplier){
        if(this.suppliers.size() < this.getMaxCountOfSuppliers()){
            this.suppliers.add(supplier);
        }
    }
}