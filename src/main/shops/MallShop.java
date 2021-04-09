package main.shops;

public class MallShop extends Shop implements IChain{
    public MallShop(String address, String workTime, int area) {
        super(address, workTime, area);
    }

    @Override
    boolean validateArea(int area) {
        return area >= 10 && area <= 100;
    }

    @Override
    int setDefaultArea() {
        return 50;
    }

    @Override
    int setDefaultTax() {
        return 150;
    }


}