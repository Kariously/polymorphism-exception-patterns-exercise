package Utilities;

import Packages.Parcel;
import java.util.List;

public class DisplayConfiguration {
    public static void showAll(List<Parcel> parcels) {
        if (parcels.isEmpty()) {
            System.out.println("\nNo items have been added yet.");
            return;
        }


        String[] headers = {"Tracking No.", "Type", "Receiver", "Chargeable Wt (kg)",
                "Service", "Delivery Fee (Php)", "Status"};

        String[][] rows = new String[parcels.size()][headers.length];
        for (int r = 0; r < parcels.size(); r++) {
            Parcel p = parcels.get(r);
            rows[r][0] = p.getTn();
            rows[r][1] = p.getTypeName();
            rows[r][2] = p.getReceiver();
            rows[r][3] = String.format("%.2f", p.getChargeableWeight());
            rows[r][4] = p.getCat().getName();
            rows[r][5] = String.format("%.2f", p.getfee());
            rows[r][6] = p.getStatus();
        }

        int[] widths = new int[headers.length];
        for (int c = 0; c < headers.length; c++) {
            widths[c] = headers[c].length();
        }
        for (String[] row : rows) {
            for (int c = 0; c < headers.length; c++) {
                widths[c] = Math.max(widths[c], row[c].length());
            }
        }

        String headerRow = buildRow(headers, widths);
        String separator = "-".repeat(headerRow.length());

        System.out.println(separator);
        System.out.println(headerRow);
        System.out.println(separator);

        for (String[] row : rows) {
            System.out.println(buildRow(row, widths));
        }

        System.out.println(separator);
        System.out.println();
    }

    private static String buildRow(String[] cells, int[] widths) {
        StringBuilder sb = new StringBuilder("|");
        for (int c = 0; c < cells.length; c++) {
            sb.append(" ").append(center(cells[c], widths[c])).append(" |");
        }
        return sb.toString();
    }

    private static String center(String text, int width) {
        int total = width - text.length();
        int left = total / 2;
        int right = total - left;
        return " ".repeat(left) + text + " ".repeat(right);
    }
}
