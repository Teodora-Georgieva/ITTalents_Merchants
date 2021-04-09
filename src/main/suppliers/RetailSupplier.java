package main.suppliers;

public class RetailSupplier extends Supplier{
    public RetailSupplier(String name, String address, String workTime) {
        super(name, address, workTime);
    }

    @Override
    public double getDiscountPercent() {
        return 0;
    }
}