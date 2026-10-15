package Utilities;

import Packages.Box;
import Packages.Document;
import Packages.Fragile;
import Packages.Parcel;
import Utilities.ParcelExceptions.DuplicateTNException;
import Utilities.ParcelExceptions.ParcelAlreadyDelivered;
import Utilities.ParcelExceptions.ParcelNotFoundException;
import Utilities.ParcelExceptions.ServiceNotFound;
import Utilities.TypesOfDelivery.DeliveryCategories;

public class Menu {
    private final ParcelConfiguration config = ParcelConfiguration.getInstance();

    public void addParcel() {
        String tn = "";
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

        if (choice==1) {
            double weight = DataValidation.validateWeight(Document.max_weight);
            int pages = DataValidation.validateInt("Enter number of pages (1-100): ", 1, 100);
            parcel = new Document(tn, receiver, weight, pages);
        } else if (choice==2) {
            double weight = DataValidation.validateWeight(Box.max_weight);
            double length = DataValidation.validateDouble("Enter length (cm): ", 1, Double.MAX_VALUE);
            double width = DataValidation.validateDouble("Enter width (cm): ", 1, Double.MAX_VALUE);
            double height = DataValidation.validateDouble("Enter height (cm): ", 1, Double.MAX_VALUE);
            parcel = new Box(tn, receiver, weight, length, width, height);
        } else {
            double weight = DataValidation.validateWeight(Fragile.max_weight);
            double length = DataValidation.validateDouble("Enter length (cm): ", 1, Double.MAX_VALUE);
            double width = DataValidation.validateDouble("Enter width (cm): ", 1, Double.MAX_VALUE);
            double height = DataValidation.validateDouble("Enter height (cm): ", 1, Double.MAX_VALUE);
            String hndlnote = DataValidation.validateString("Enter handling note: ");
            parcel = new Fragile(tn, receiver, weight, length, width, height, hndlnote);
        }

        char choicev3 = DataValidation.validatechar("Would you like to change your delivery service? (Y/N):", "YN");
        if (choicev3=='Y') {
            changeDeliveryService();
        }

        try {
            config.addParcel(parcel);
            System.out.printf("Parcel added successfully! Delivery fee: %.2f\n", parcel.getfee());
        } catch (DuplicateTNException e) {
            System.out.println(e.getMessage());
        }
    }

    public void viewParcels() {
        DisplayConfiguration.printParcelTable(config.getAllParcels());
    }

    public void changeDeliveryService() {
        try {
            Parcel parcel = config.findPending(DataValidation.readTN());

            System.out.println("=== DELIVERY SERVICE ===");
            System.out.println("""
                    [1] Standard
                    [2] Express
                    [3] Same Day""");

            int choicev2 = DataValidation.validateInt("Enter choice: ", 1, 3);
            DeliveryCategories cat = DataValidation.readService(choicev2);
            parcel.setCat(cat);
        } catch (ParcelNotFoundException | ServiceNotFound | ParcelAlreadyDelivered e) {
            System.out.println(e.getMessage());
        }
    }

    public void markDelivered() {
        try {
            config.MarkDelivered(DataValidation.readTN());
            System.out.println("Parcel delivered successfully!");
        } catch (ParcelNotFoundException | ParcelAlreadyDelivered e) {
            System.out.println(e.getMessage());
        }
    }
}
