package Utilities;

import Packages.Parcel;
import Utilities.ParcelExceptions.DuplicateTNException;

public class Menu {
    private final ParcelConfiguration config = ParcelConfiguration.getInstance();

    public void addParcel() {
        String tn;
        boolean again = true;
        Parcel parcel;

        System.out.println("=== ADD PARCEL TO SYSTEM ===");
        System.out.println("""
                [1] Document
                [2] Box
                [3] Fragile""");

        int choice = DataValidation.validateInt("Select parcel type: ", 1, 3);

        while (again) {
            tn = DataValidation.readTN();

            try {
                config.isTNAvailable(tn);
                again = false;
            } catch (DuplicateTNException e) {
                System.out.println(e.getMessage());
            }
        }

        String receiver = DataValidation.validateString("Enter your name: ");

        if (type==1) {
            double weight =
        }
    }
}
