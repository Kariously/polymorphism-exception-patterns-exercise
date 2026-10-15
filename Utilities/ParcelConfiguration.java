package Utilities;

import Packages.Parcel;
import Utilities.ParcelExceptions.DuplicateTNException;

import java.util.ArrayList;

public class ParcelConfiguration {
    private static ParcelConfiguration instance;
    private final ArrayList<Parcel> parcels = new ArrayList<>();

    private ParcelConfiguration() { }

    public static synchronized ParcelConfiguration getInstance() {
        if (instance == null) {
            instance = new ParcelConfiguration();
        }

        return instance;
    }

    public void isTNAvailable(String TN) throws DuplicateTNException {
        for (Parcel p : parcels) {
            if (p)
        }
    }
}
