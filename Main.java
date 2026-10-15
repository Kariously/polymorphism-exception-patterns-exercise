import Utilities.DataValidation;
import Utilities.Menu;
import Utilities.ParcelConfiguration;

public class Main {
    public static void main() {
        Menu menu = new Menu();

        boolean again=true;

        while (again) {
            System.out.println("=== PARCEL DELIVERY SYSTEM ===");
            System.out.println("""
                    [1] Add Parcel
                    [2] View All Parcels
                    [3] Change Delivery Service
                    [4] Mark as Delivered
                    [5] Exit""");

            int choice = DataValidation.validateInt("Enter choice", 1, 5);

            switch (choice) {
                case 1 -> // function 1
                case 2 -> // function 2
                case 3 -> // function 3
                case 4 -> // function 4
                case 5 -> again = false;
            }
        }
    }
}
