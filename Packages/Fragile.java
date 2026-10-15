package Packages;

public class Fragile extends Box{
    public static final double max_weight = 30;
    private final String hndlnote;

    public Fragile(String TN, String receiver, double weight, int length, int width, int height, String hndlnote) {
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

    public String getHndlnote() { return hndlnote; }
}
