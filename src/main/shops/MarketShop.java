package main.shops;

public class MarketShop extends Shop implements IETShop{
    public MarketShop(String address, String workTime, int area) {
        super(address, workTime, area);
    }

    @Override
    boolean validateArea(int area) {
        return area >= 2 && area <= 10;
    }

    @Override
    int setDefaultArea() {
        return 5;
    }

    @Override
    int setDefaultTax() {
        return 50;
    }
}