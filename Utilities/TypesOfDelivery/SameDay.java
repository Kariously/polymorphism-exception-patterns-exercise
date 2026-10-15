package Utilities.TypesOfDelivery;

public class SameDay implements DeliveryCategories {
    public static final double max_weight = 10;

    @Override
    public String getName() {
        return "Same Day";
    }

    @Override
    public double calculatefee(double baseFee) {
        return (baseFee*2) + 100;
    }

    @Override
    public boolean checkweight(double chargeableWeight) {
        return chargeableWeight <= max_weight;
    }

    @Override
    public String restriction() {
        return "Same Day delivery only allows parcels up to 10 kg.";
    }

}
