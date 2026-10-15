package Packages;

public class Document extends Parcel {
    public static final double max_weight = 0.5;
    private final int noofpages;

    public Document(String TN, String receiver, double weight, int noofpages) {
        super(TN, receiver, weight);
        this.noofpages = noofpages;
    }

    @Override
    public double getChargeableWeight() {
        return getWeight();
    }

    @Override
    public double getBaseFee() {
        return 50;
    }

    @Override
    public String getTypeName() {
        return "Document";
    }

    public int getNoofpages() { return noofpages; }
}
