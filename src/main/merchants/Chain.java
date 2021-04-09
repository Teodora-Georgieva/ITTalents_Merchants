package main.merchants;

import main.shops.IChain;
import main.suppliers.Supplier;
import main.utils.Generator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Chain extends NonAmbulant{
    private HashSet<Supplier> suppliers;
    private HashSet<IChain> shops;

    public Chain(String name, String address, double money) {
        super(name, address, money);
        this.suppliers = new HashSet<>();
        this.shops = new HashSet<>();
    }

    @Override
    int getMaxCountOfSuppliers() {
        return 15;
    }

    @Override
    public void receiveProfit() {
        double profit = 0;
        for(IChain shop : this.shops){
            HashMap<Integer, Double> countOfSoldStocksAndTotalPrice = shop.sellProducts();
            Map.Entry<Integer, Double> entry = countOfSoldStocksAndTotalPrice.entrySet().iterator().next();
            int countOfSoldStocks = entry.getKey();
            profit+=entry.getValue();
            this.increaseMoney(profit);
            this.countOfSoldStocks+=countOfSoldStocks;
            System.out.println("Profit Chain: " + profit);
        }
    }

    public void payTax(){
        int taxes = 0;

        for(IChain shop : this.shops){
            taxes+=shop.getTax();
        }

        this.paidTaxes = taxes;
        this.decreaseMoney(taxes);
        System.out.println("Paid taxes Chain: " + taxes);
    }

    private int getMaxCountOfShops(){
        return 10;
    }

    public void addShop(IChain shop){
        if(this.shops.size() < this.getMaxCountOfShops()){
            this.shops.add(shop);
        }
    }

    public void setSupplier(Supplier supplier){
        if(this.suppliers.size() < this.getMaxCountOfSuppliers()){
            this.suppliers.add(supplier);
        }
    }

    public Supplier getSupplier() {
        int idx = Generator.generateRandomNumber(0, this.suppliers.size() - 1);
        ArrayList<Supplier> s = new ArrayList<>();
        s.addAll(this.suppliers);
        return s.get(idx);
    }

    @Override
    public void makeOrderToSupplier(Supplier supplier) {
        for(IChain shop : this.shops){
            makeOrder(supplier, shop);
        }
    }

    @Override
    public void showListOfProducts() {
        System.out.println("Showing list of products of Chain:");
        for(IChain shop : this.shops){
            System.out.println("shop address: " + shop.getAddress());
            showProducts(shop);
            System.out.println();
        }
    }
}