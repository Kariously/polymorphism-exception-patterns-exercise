package Utilities.TypesOfDelivery;

public class Express implements DeliveryCategories{

    @Override
    public String getName() {
        return "Express";
    }

    @Override
    public double calculatefee(double baseFee) {
        return baseFee*1.5;
    }
}
