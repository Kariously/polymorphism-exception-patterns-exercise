package Utilities.TypesOfDelivery;

public interface DeliveryCategories {
    String getName();

    double calculatefee(double baseFee);

    default boolean checkweight(double chargeableWeight) { // for same day
        return true;
    }

    default String restriction() {
        return "";
    }
}
