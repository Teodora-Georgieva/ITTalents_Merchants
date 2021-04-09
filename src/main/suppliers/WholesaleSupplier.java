package main.suppliers;

public class WholesaleSupplier extends Supplier{
    public WholesaleSupplier(String name, String address, String workTime) {
        super(name, address, workTime);
    }

    @Override
    public double getDiscountPercent() {
        return 15;
    }
}