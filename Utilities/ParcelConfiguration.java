package Utilities;

import Packages.Parcel;
import Utilities.ParcelExceptions.DuplicateTNException;
import Utilities.ParcelExceptions.ParcelAlreadyDelivered;
import Utilities.ParcelExceptions.ParcelNotFoundException;
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
            if (p.getTn().equals(TN)) {
                throw new DuplicateTNException();
            }
        }
    }

    public void addParcel(Parcel parcel) throws DuplicateTNException {
        isTNAvailable(parcel.getTn());
        parcels.add(parcel);
    }

    public Parcel findParcel(String TN) throws ParcelNotFoundException {
        for (Parcel p : parcels) {
            if (p.getTn().equals(TN)) {
                return p;
            }
        }
        throw new ParcelNotFoundException();
    }

    public Parcel findPending(String TN) throws ParcelNotFoundException, ParcelAlreadyDelivered {
        Parcel p = findParcel(TN);

        if (p.isDelivered()) {
            throw new ParcelAlreadyDelivered("Parcel already delivered.");
        }
        return p;
    }

    public void MarkDelivered(String TN) throws ParcelNotFoundException, ParcelAlreadyDelivered {
        Parcel p = findParcel(TN);

        if (p.isDelivered()) {
            throw new ParcelAlreadyDelivered("Parcel already delivered.");
        }

        p.markDelivered();
    }

    public ArrayList<Parcel> getAllParcels() {
        return parcels;
    }
}
