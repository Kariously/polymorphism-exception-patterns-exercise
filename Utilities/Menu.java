package Utilities;

public class Menu {
    private final ParcelConfiguration config = ParcelConfiguration.getInstance();

    public void addParcel() {
        String tn;
        boolean again = true;

        System.out.println("=== ADD PARCEL TO SYSTEM ===");
        System.out.println("""
                [1] Document
                [2] Box
                [3] Fragile""");

        int choice = DataValidation.validateInt("Select parcel type: ", 1, 3);

        while (again) {
            tn = DataValidation.readTN();

            try {
                config
            }
        }
    }
}
