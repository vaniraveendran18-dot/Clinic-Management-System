package util;

import model.Appointment;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Task 8 (Sorting): Insertion Sort implementation used to order
 * appointments by date (earliest first). Returns a new sorted list;
 * the original list passed in is left untouched.
 */
public class InsertionSortUtil {

    public static ArrayList<Appointment> sortAppointmentsByDate(ArrayList<Appointment> appointments) {
        ArrayList<Appointment> list = new ArrayList<>(appointments);

        for (int i = 1; i < list.size(); i++) {
            Appointment key = list.get(i);
            int j = i - 1;

            // shift every appointment that is later than key one place right
            while (j >= 0 && isLater(list.get(j), key)) {
                list.set(j + 1, list.get(j));
                j = j - 1;
            }
            list.set(j + 1, key);
        }
        return list;
    }

    /** True if a is after b: compares date first, then time if same day. */
    private static boolean isLater(Appointment a, Appointment b) {
        LocalDate dateA = LocalDate.parse(a.getAppointmentDate());
        LocalDate dateB = LocalDate.parse(b.getAppointmentDate());
        if (!dateA.equals(dateB)) {
            return dateA.isAfter(dateB);
        }
        return a.getAppointmentTime().compareTo(b.getAppointmentTime()) > 0;
    }
}
