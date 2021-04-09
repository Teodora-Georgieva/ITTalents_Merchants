package main;

import main.merchants.Ambulant;
import main.merchants.Chain;
import main.merchants.ETTrader;
import main.merchants.Merchant;
import main.shops.*;
import main.suppliers.RetailSupplier;
import main.suppliers.Supplier;
import main.suppliers.WholesaleSupplier;
import main.utils.Generator;

import java.util.ArrayList;
import java.util.Random;

public class Demo {
    public static void main(String[] args) {
        ArrayList<Supplier> suppliers = new ArrayList<>();
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            String name = "Supplier" + (i+1);
            String address = "SupplierAddress" + (i+1);
            Supplier s = r.nextBoolean() ? new RetailSupplier(name, address, "") :
                                           new WholesaleSupplier(name, address, "");
            suppliers.add(s);
        }

        ArrayList<Product> products = new ArrayList<>();
        for (int i = 0; i < 5000; i++) {
            String name = "Product" + (i+1);
            double price = Generator.generateRandomNumber(5, 15);
            products.add(new Product(name, price));
        }

        for(Supplier s : suppliers){
            for (int i = 0; i < 500; i++) {
                Product p = products.get(0);
                products.remove(0);
                s.addProduct(p);
            }
        }

        ArrayList<IChain> iChains = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            String address = "ShopAddress" + (i+1);
            IChain shop = r.nextBoolean() ? new MallShop(address, "", Generator.generateRandomNumber(10, 100)) :
                                            new Booth(address, "", Generator.generateRandomNumber(4, 6));
            iChains.add(shop);
        }

        ArrayList<IETShop> iEtShops = new ArrayList<>();
        for (int i = 10; i < 20; i++) {
            String address = "ShopAddress" + (i+1);
            IETShop shop = r.nextBoolean() ? new MarketShop(address, "", Generator.generateRandomNumber(2, 10)) :
                                            new Booth(address, "", Generator.generateRandomNumber(4, 6));
            iEtShops.add(shop);
        }

        ArrayList<Merchant> merchants = new ArrayList<>();
        Ambulant ambulant = new Ambulant("Ambulant merchant", "No address", 100);
        ETTrader etTrader = new ETTrader("ET Merchant", "Address of ET Merchant", 500);
        Chain chain = new Chain("Chain", "Address of Chain", 3000);
        merchants.add(ambulant);
        merchants.add(etTrader);
        merchants.add(chain);

        ArrayList<RetailSupplier> retailSuppliers = new ArrayList<>();
        for (int i = 0; i < suppliers.size(); i++) {
            if(suppliers.get(i) instanceof RetailSupplier){
                retailSuppliers.add((RetailSupplier) suppliers.get(i));
                suppliers.remove(i);
            }
        }

        ambulant.setSupplier(retailSuppliers.get(0));
        retailSuppliers.remove(0);

        int indexOfiETShop = Generator.generateRandomNumber(0, iEtShops.size() - 1);
        etTrader.addShop(iEtShops.get(indexOfiETShop));

        int countOfSuppliersForET = Generator.generateRandomNumber(1, 5);

        if(retailSuppliers.size() == 0){
            System.out.println("There are no retail suppliers!!!");
        }
        else {
            for (int i = 0; i < countOfSuppliersForET; i++) {
                if (retailSuppliers.size() > 0) {
                    int supplierIdx = Generator.generateRandomNumber(0, retailSuppliers.size() - 1);
                    RetailSupplier retailSupplier = retailSuppliers.get(supplierIdx);
                    retailSuppliers.remove(supplierIdx);
                    etTrader.setSupplier(retailSupplier);
                }
                else{
                    break;
                }
            }
        }

        int countOfShopsForChain = Generator.generateRandomNumber(1, 10);
        for (int i = 0; i < countOfShopsForChain; i++) {
            int idx = Generator.generateRandomNumber(0, iChains.size() - 1);
            chain.addShop(iChains.get(idx));
        }

        int countOfSuppliersForChain = Generator.generateRandomNumber(1, 15);
        for (int i = 0; i < countOfSuppliersForChain; i++) {
            Supplier s;
            if(suppliers.size() > 0 && retailSuppliers.size() > 0){
                s = r.nextBoolean() ? suppliers.get(0) : retailSuppliers.get(0);
            }
            else if(suppliers.size() == 0){
                s = retailSuppliers.get(0);
            }
            else if(retailSuppliers.size() == 0){
                s = suppliers.get(0);
            }
            else{
                break;
            }

            chain.setSupplier(s);
        }

        startTrading(merchants);

        System.out.println();
        System.out.println("Showing money balance of merchants:");
        for(Merchant merchant : merchants) {
            System.out.println(merchant.getName() + ", " + merchant.getMoney() + "lv.");
        }

        System.out.println("Showing merchant with most sold stocks: ");
        merchants.sort((m1, m2) -> Integer.compare(m2.getCountOfSoldStocks(), m1.getCountOfSoldStocks()));
        Merchant withMostSoldStocks = merchants.get(0);
        System.out.println(withMostSoldStocks.getName() + withMostSoldStocks.getCountOfSoldStocks());

        System.out.println("Testing if they are sorted right: ");
        for (Merchant m : merchants){
            System.out.println(m.getName() + ", " + m.getCountOfSoldStocks() + " sold stocks");
        }

        System.out.println("Showing merchant with biggest tax: ");
        merchants.sort((m1, m2) -> Double.compare(m2.getPaidTaxes(), m1.getPaidTaxes()));
        Merchant withBiggestTax = merchants.get(0);
        System.out.println(withBiggestTax.getName() + withBiggestTax.getPaidTaxes());

        System.out.println("Testing if they are sorted right: ");
        for (Merchant m : merchants){
            System.out.println(m.getName() + ", " + m.getPaidTaxes() + " lv for taxes");
        }
    }

    static void startTrading(ArrayList<Merchant> merchants){
        for(Merchant merchant : merchants){
            Supplier supplier = merchant.getSupplier();
            merchant.makeOrderToSupplier(supplier);
            merchant.showListOfProducts();
            merchant.receiveProfit();
            merchant.payTax();
        }
    }
}