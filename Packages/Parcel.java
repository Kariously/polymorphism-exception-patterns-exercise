package Packages;

import Utilities.ParcelExceptions.ServiceNotFound;
import Utilities.TypesOfDelivery.DeliveryCategories;
import Utilities.TypesOfDelivery.Standard;

public abstract class Parcel {
    private final String tn, receiver;
    private final double weight;
    private boolean status; // pending = false; delivered = true
    private DeliveryCategories cat;

    public Parcel(String tn, String receiver, double weight) {
        this.tn = tn;
        this.receiver = receiver;
        this.weight = weight;
        this.cat = new Standard();
        this.status = false;
    }

    public abstract double getChargeableWeight();

    public abstract double getBaseFee();

    public abstract String getTypeName();

    public double getfee() {
        return cat.calculatefee(getBaseFee());
    }

    public DeliveryCategories getCat() { return cat; }

    public String getTypeofDelivery() { return cat.getName(); }

    public String getTn() { return tn; }

    public String getReceiver() { return receiver; }

    public double getWeight() { return weight; }

    public boolean isDelivered() { return status; }

    public void markDelivered() { this.status = true; }

    public String getStatus() { return status ? "Delivered" : "Pending"; }

    public void setCat(DeliveryCategories cat) throws ServiceNotFound {
        if (!cat.checkweight(getChargeableWeight())) {
            throw new ServiceNotFound(cat.restriction());
        }

        this.cat = cat;
    }
}
