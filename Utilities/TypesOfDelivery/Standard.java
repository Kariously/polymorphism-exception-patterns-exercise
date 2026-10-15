package Utilities.TypesOfDelivery;

public class Standard implements  DeliveryCategories{

    @Override
    public String getName() {
        return "Standard";
    }

    @Override
    public double calculatefee(double baseFee) {
        return baseFee;
    }
}
