package Utilities;

import Packages.Parcel;
import java.util.List;

public class DisplayConfiguration {
    public static void printParcelTable(List<Parcel> parcels) {
        System.out.println();
        if (parcels.isEmpty()) {
            System.out.println("No parcels found.");
            return;
        }

        String line = "-".repeat(104);
        System.out.println(line);
        System.out.printf("%-12s %-10s %-20s %13s %-10s %14s %-10s%n",
                "Tracking No.", "Type", "Receiver", "Charge Wt(kg)", "Service", "Fee (Php)", "Status");
        System.out.println(line);

        for (Parcel p : parcels) {
            System.out.printf("%-12s %-10s %-20s %13.2f %-10s %14.2f %-10s%n",
                    p.getTn(),
                    p.getTypeName(),
                    p.getReceiver(),
                    p.getChargeableWeight(),
                    p.getStatus(),
                    p.getBaseFee(),
                    p.getStatus());
        }
        System.out.println(line);
    }
}
