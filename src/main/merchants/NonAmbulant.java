package main.merchants;

import main.shops.IShop;
import main.shops.Product;
import main.suppliers.Supplier;

import java.util.ArrayList;

public abstract class NonAmbulant extends Merchant{
    NonAmbulant(String name, String address, double money) {
        super(name, address, money);
    }

    void makeOrder(Supplier supplier, IShop shop){
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
        shop.addProducts(orderedProducts);
    }

    void showProducts(IShop shop){
        for(Product p : shop.getProducts()){
            System.out.println(p);
        }
    }
}