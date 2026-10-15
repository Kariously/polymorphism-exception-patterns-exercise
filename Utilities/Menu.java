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

import java.util.ArrayList;

public class Menu {
    private final ParcelConfiguration config = ParcelConfiguration.getInstance();
    private final ArrayList<Parcel> parcels = new ArrayList<>();
    private int count = 0;

    public void addParcel() {
        count =1;
        Parcel parcel;
        String tn;

        System.out.println("\n=== ADD PARCEL TO SYSTEM ===");
        System.out.println("""
            [1] Document
            [2] Box
            [3] Fragile""");

        int choice = DataValidation.validateInt("Select parcel type: ", 1, 3);

        while (true) {
            tn = DataValidation.readTN();
            try {
                config.isTNAvailable(tn);
                break;
            } catch (DuplicateTNException e) {
                System.out.println(e.getMessage());
            }
        }

        String receiver = DataValidation.validateString("Enter receiver name: ");

        if (choice == 1) {
            double weight = DataValidation.validateWeight(Document.max_weight);
            int pages = DataValidation.validateInt("Enter number of pages (1-100): ", 1, 100);
            parcel = new Document(tn, receiver, weight, pages);
        } else if (choice == 2) {
            double weight = DataValidation.validateWeight(Box.max_weight);
            double length = DataValidation.validateDouble("Enter length (cm): ", Box.min_dimension, Box.max_dimension);
            double width = DataValidation.validateDouble("Enter width (cm): ", Box.min_dimension, Box.max_dimension);
            double height = DataValidation.validateDouble("Enter height (cm): ", Box.min_dimension, Box.max_dimension);
            parcel = new Box(tn, receiver, weight, length, width, height);
        } else {
            double weight = DataValidation.validateWeight(Fragile.max_weight);
            double length = DataValidation.validateDouble("Enter length (cm): ", Fragile.min_dimension, Fragile.max_dimension);
            double width = DataValidation.validateDouble("Enter width (cm): ", Fragile.min_dimension, Fragile.max_dimension);
            double height = DataValidation.validateDouble("Enter height (cm): ", Fragile.min_dimension, Fragile.max_dimension);
            String hndlnote = DataValidation.validateString("Enter handling note: ");
            parcel = new Fragile(tn, receiver, weight, length, width, height, hndlnote);
        }

        while (true) {
            try {
                parcel.setCat(askService());
                break;
            } catch (ServiceNotFound e) {
                System.out.println(e.getMessage());
            }
        }

        try {
            config.addParcel(parcel);
            System.out.printf("Parcel added successfully! Delivery fee: Php %.2f%n", parcel.getfee());
        } catch (DuplicateTNException e) {
            System.out.println(e.getMessage());
        }
    }

    public void viewParcels() {
        if (count==0) {
            System.out.println("\nNo items have been added yet.");
            return;
        }

        DisplayConfiguration.showAll(config.getAllParcels());
    }


    public void changeDeliveryService() {
        if (count==0) {
            System.out.println("\nNo items have been added yet.");
            return;
        }
        try {
            Parcel parcel = config.findPending(DataValidation.readTN());
            parcel.setCat(askService());
            System.out.printf("Delivery service changed! New fee: Php %.2f%n", parcel.getfee());
        } catch (ParcelNotFoundException | ServiceNotFound | ParcelAlreadyDelivered e) {
            System.out.println(e.getMessage());
        }
    }

    private DeliveryCategories askService() {
        System.out.println("=== DELIVERY SERVICE ===");
        System.out.println("""
            [1] Standard
            [2] Express
            [3] Same Day""");
        int choice = DataValidation.validateInt("Enter choice: ", 1, 3);
        return DataValidation.readService(choice);
    }

    public void markDelivered() {
        if (count==0) {
            System.out.println("\nNo items have been added yet.");
            return;
        }

        try {
            config.MarkDelivered(DataValidation.readTN());
            System.out.println("Parcel delivered successfully!");
        } catch (ParcelNotFoundException | ParcelAlreadyDelivered e) {
            System.out.println(e.getMessage());
        }
    }
}
