package Utilities;

import Utilities.ParcelExceptions.ExceptionsValidation;
import Utilities.ParcelExceptions.InvalidInput;
import Utilities.TypesOfDelivery.DeliveryCategories;
import Utilities.TypesOfDelivery.Express;
import Utilities.TypesOfDelivery.SameDay;
import Utilities.TypesOfDelivery.Standard;

import java.util.Scanner;

public class DataValidation {
    static Scanner sc = new Scanner(System.in);

    private static int toInt(String input, int min, int max) throws ExceptionsValidation {
        int res;

        try {
            res = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new ExceptionsValidation("Invalid input. Please enter a valid number.");
        }

        if (res < min) {
            throw new ExceptionsValidation("Invalid input. Must be at least " + min + ".");
        }
        if (res > max) {
            throw new ExceptionsValidation("Invalid input. Enter a number from " + min + " to " + max + ".");
        }

        return res;
    }

    public static int validateInt(String prompt, int min, int max) {
        boolean again = true;
        int res = 0;

        while (again) {
            System.out.print(prompt);

            try {
                res = toInt(sc.nextLine().trim(), min, max);
                again = false;
            } catch (ExceptionsValidation e) {
                System.out.println(e.getMessage());
            }
        }

        return res;
    }

    private static String isvalidTN(String input) throws ExceptionsValidation {
        String tn = input.toUpperCase();

        if (!tn.matches("^ABC\\d{4}$")) {
            throw new ExceptionsValidation("Invalid tracking number.");
        }

        return tn;
    }

    public static String readTN() {
        boolean again = true;
        String res = "";

        while (again) {
            System.out.print("Enter tracking number (e.g. ABC0001): ");

            try {
                res = isvalidTN(sc.nextLine().trim());
            } catch (ExceptionsValidation e) {
                System.out.println(e.getMessage());
            }
        }

        return res;
    }

    private static String isvalidString(String input) throws InvalidInput {
        if (input.isEmpty()) {
            throw new InvalidInput("Invalid String Input");
        }

        return input;
    }

    public static String validateString(String prompt) {
        boolean again = true;
        String res = "";

        while (again) {
            System.out.print(prompt);

            try {
                res = isvalidString(sc.nextLine().trim());
                again = false;
            } catch (InvalidInput e) {
                System.out.println(e.getMessage());
            }
        }

        return res;
    }

    private static double isvalidWeight(String input, double maxWeight) throws InvalidInput {
        double res;

        try {
            res = Double.parseDouble(input);
        } catch (NumberFormatException e) {
            throw new InvalidInput("Invalid input. Please enter a valid number.");
        }

        if (res <= 0) {
            throw new InvalidInput("Weight mus not be zero.");
        } else if (res>maxWeight) {
            throw new InvalidInput("Weight must not be heavier than " + maxWeight + " kg for this parcel type.");
        }

        return res;
    }

    public static double validateWeight(double maxWeight) {
        boolean again = true;
        double res = 0;

        while (again) {
            System.out.print("Enter weight in kg (max " + maxWeight + "): ");

            try {
                res = isvalidWeight(sc.nextLine().trim(), maxWeight);
                again = false;
            } catch (InvalidInput e) {
                System.out.println(e.getMessage());
            }
        }

        return res;
    }

    public static DeliveryCategories readService(int choice) {
        DeliveryCategories cat = null;
        switch (choice) {
            case 1 -> {
                cat = new Standard();
                break;
            }
            case 2 -> {
                cat = new Express();
            }
            case 3 -> {
                cat = new SameDay();
            }
        }
        
        return cat;
    }
}
