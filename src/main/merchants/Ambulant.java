package main.merchants;

import main.shops.Product;
import main.suppliers.RetailSupplier;
import main.suppliers.Supplier;
import main.utils.Generator;

import java.util.ArrayList;

public class Ambulant extends Merchant{
    private ArrayList<Product> products;
    private RetailSupplier supplier;

    public Ambulant(String name, String address, double money) {
        super(name, address, money);
        this.products = new ArrayList<>();
    }

    @Override
    int getMaxCountOfSuppliers() {
        return 1;
    }

    @Override
    public void receiveProfit() {
        double profit = this.sellProducts();
        this.increaseMoney(profit);
        System.out.println("Profit Ambulant: " + profit);
    }

    public double sellProducts() {
        int countOfSoldProducts = Generator.generateRandomNumber(1, this.products.size());
        double profit = 0;
        for (int i = 0; i < countOfSoldProducts; i++) {
            this.countOfSoldStocks++;
            int idx = Generator.generateRandomNumber(0, this.products.size() - 1);
            double priceOfProduct = this.products.get(idx).getPrice();
            profit+=priceOfProduct;
            this.products.remove(idx);
        }
        profit = profit + 0.3*profit;
        return profit;
    }

    public void setSupplier(RetailSupplier supplier) {
        if(this.supplier == null) {
            this.supplier = supplier;
        }
        else{
            System.out.println("The ambulant merchant " + this.getName() + " already has a supplier");
        }
    }

    public void payTax(){
        System.out.println("This is an ambulant merchant so he doesn't pay taxes.");
    }

    public RetailSupplier getSupplier() {
        return supplier;
    }

    public void addProducts(ArrayList<Product> products){
        this.products.addAll(products);
        this.products.sort((p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice()));
    }

    public void makeOrderToSupplier(Supplier supplier){
        double maxMoneyForOrder = this.getMoney() / 2;
        double crrMoneyToPay = 0;
        ArrayList<Product> orderedProducts = new ArrayList<>();

        while(crrMoneyToPay <= maxMoneyForOrder){
            Product p = supplier.giveProduct();
            crrMoneyToPay+=p.getPrice();
            if(crrMoneyToPay > maxMoneyForOrder){
                break;
            }
            orderedProducts.add(p);
        }

        double discount = supplier.getDiscountPercent()/100*crrMoneyToPay;
        crrMoneyToPay-=discount;
        supplier.receiveMoney(crrMoneyToPay);
        this.decreaseMoney(crrMoneyToPay);
        this.addProducts(orderedProducts);
    }

    @Override
    public void showListOfProducts() {
        System.out.println("Showing list of products of ambulant merchant: ");
        for (int i = 0; i < this.products.size(); i++) {
            System.out.println(this.products.get(i));
        }
    }
}