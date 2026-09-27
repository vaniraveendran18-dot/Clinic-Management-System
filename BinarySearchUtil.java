package util;

import model.Patient;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Task 8 (Searching): Binary Search implementation used to find a
 * patient by ID. Binary search requires a sorted list, so this class
 * sorts a copy by ID first and then performs the manual binary search
 * (not Collections.binarySearch, so the algorithm itself is demonstrated).
 */
public class BinarySearchUtil {

    /**
     * Returns the Patient with the given ID, or null if not found.
     */
    public static Patient searchPatientById(ArrayList<Patient> patients, String id) {
        if (patients == null || id == null) {
            return null;
        }

        ArrayList<Patient> sorted = new ArrayList<>(patients);
        sorted.sort(Comparator.comparing(Patient::getPersonId));

        int low = 0;
        int high = sorted.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            int comparison = sorted.get(mid).getPersonId().compareTo(id);

            if (comparison == 0) {
                return sorted.get(mid);
            } else if (comparison < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null; // not found
    }
}
