package Packages;

public class Box extends Parcel {
    public static final double max_weight = 50;
    private final int length;
    private final int width;
    private final int height;

    public Box(String TN, String receiver, double weight, int length, int width, int height) {
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

    public int getLength() { return length; }
    public int getWidth()  { return width; }
    public int getHeight() { return height; }
}
