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
    private int count = 0;

    public void addParcel() {
        count =1;
        Parcel parcel;
        String tn="";
        boolean again = true;
        boolean againv2 = true;

        System.out.println("\n=== ADD PARCEL TO SYSTEM ===");
        System.out.println("""
            [1] Document
            [2] Box
            [3] Fragile""");

        int choice = DataValidation.validateInt("Select parcel type: ", 1, 3);

        while (again) {
            tn = DataValidation.readTN();
            try {
                config.isTNAvailable(tn);
                again=false;
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
            int length = DataValidation.validateInt("Enter length (cm): ", Box.min_dimension, Box.max_dimension);
            int width = DataValidation.validateInt("Enter width (cm): ", Box.min_dimension, Box.max_dimension);
            int height = DataValidation.validateInt("Enter height (cm): ", Box.min_dimension, Box.max_dimension);
            parcel = new Box(tn, receiver, weight, length, width, height);
        } else {
            double weight = DataValidation.validateWeight(Fragile.max_weight);
            int length = DataValidation.validateInt("Enter length (cm): ", Fragile.min_dimension, Fragile.max_dimension);
            int width = DataValidation.validateInt("Enter width (cm): ", Fragile.min_dimension, Fragile.max_dimension);
            int height = DataValidation.validateInt("Enter height (cm): ", Fragile.min_dimension, Fragile.max_dimension);
            String hndlnote = DataValidation.validateString("Enter handling note: ");
            parcel = new Fragile(tn, receiver, weight, length, width, height, hndlnote);
        }

        while (againv2) {
            try {
                parcel.setCat(askService());
                againv2=false;
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
            System.out.println("\n=============================");
            System.out.println("No items have been added yet.");
            System.out.println("=============================");
            return;
        }

        DisplayConfiguration.showAll(config.getAllParcels());
    }


    public void changeDeliveryService() {
        if (count==0) {
            System.out.println("\n=============================");
            System.out.println("No items have been added yet.");
            System.out.println("=============================");
            return;
        }
        try {
            Parcel parcel = config.findPending(DataValidation.readTN());
            DeliveryCategories cat = askService();

            if (parcel.getCat().getName().equals(cat.getName())) {
                System.out.println("You already have this delivery service.");
                return;
            }

            parcel.setCat(cat);
            System.out.printf("Delivery service changed! New fee: Php %.2f%n", parcel.getfee());

        } catch (ParcelNotFoundException | ServiceNotFound | ParcelAlreadyDelivered e) {
            System.out.println(e.getMessage());
        }
    }

    private DeliveryCategories askService() {
        System.out.println("\n=== DELIVERY SERVICE ===");
        System.out.println("""
            [1] Standard
            [2] Express
            [3] Same Day""");
        int choice = DataValidation.validateInt("Enter choice: ", 1, 3);
        return DataValidation.readService(choice);
    }

    public void markDelivered() {
        if (count==0) {
            System.out.println("\n=============================");
            System.out.println("No items have been added yet.");
            System.out.println("=============================");
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
