import Utilities.DataValidation;
import Utilities.Menu;

public class Main {
    public static void main(String[] args) {
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
                case 1 -> menu.addParcel();
                case 2 -> menu.viewParcels();
                case 3 -> menu.changeDeliveryService();
                case 4 -> menu.markDelivered();
                case 5 -> again = false;
            }
        }
    }
}
