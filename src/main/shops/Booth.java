package main.shops;

public class Booth extends Shop implements IETShop, IChain{
    public Booth(String address, String workTime, int area) {
        super(address, workTime, area);
    }

    @Override
    boolean validateArea(int area) {
        return area >= 4 && area <= 6;
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