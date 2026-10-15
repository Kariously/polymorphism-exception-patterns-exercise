package Packages;

public class Box extends Parcel {
    public static final double min_dimension = 5;
    public static final double max_dimension = 100;
    public static final double max_weight = 50;
    private final double length;
    private final double width;
    private final double height;

    public Box(String TN, String receiver, double weight, double length, double width, double height) {
        super(TN, receiver, weight);
        this.length = length;
        this.width = width;
        this.height = height;
    }

    public double getVolWeight() {
        return ((double) length * width * height) / 3500.0;
    }

    @Override
    public double getChargeableWeight() {
        return Math.ceil(Math.max(getWeight(), getVolWeight()));
    }

    @Override
    public double getBaseFee() {
        return 40*getChargeableWeight();
    }

    @Override
    public String getTypeName() {
        return "Box";
    }

    public double getLength() { return length; }
    public double getWidth()  { return width; }
    public double getHeight() { return height; }
}
