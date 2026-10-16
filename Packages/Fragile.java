package Packages;

public class Fragile extends Box{
    public static final int min_dimension = 5;
    public static final int max_dimension = 80;
    public static final double max_weight = 30;
    private final String hndlnote;

    public Fragile(String TN, String receiver, double weight, double length, double width, double height, String hndlnote) {
        super(TN, receiver, weight, length, width, height);
        this.hndlnote = hndlnote;
    }

    @Override
    public double getBaseFee() {
        return super.getBaseFee() + 100;
    }

    @Override
    public String getTypeName() {
        return "Fragile";
    }
}
