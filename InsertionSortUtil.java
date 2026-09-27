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
            LocalDate keyDate = LocalDate.parse(key.getAppointmentDate());
            int j = i - 1;

            while (j >= 0 && LocalDate.parse(list.get(j).getAppointmentDate()).isAfter(keyDate)) {
                list.set(j + 1, list.get(j));
                j = j - 1;
            }
            list.set(j + 1, key);
        }
        return list;
    }
}
