package util;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Central place for input-validation rules so every screen validates
 * data the same way (single responsibility / DRY).
 */
public class ValidationUtil {

    public static void requireNotEmpty(String value, String fieldName) throws InvalidInputException {
        if (value == null || value.trim().isEmpty()) {
            throw new InvalidInputException(fieldName + " cannot be empty.");
        }
    }

    public static void requireValidDate(String value, String fieldName) throws InvalidInputException {
        requireNotEmpty(value, fieldName);
        try {
            LocalDate.parse(value.trim());
        } catch (DateTimeParseException e) {
            throw new InvalidInputException(fieldName + " must be in yyyy-MM-dd format.");
        }
    }

    public static double requireValidPositiveNumber(String value, String fieldName) throws InvalidInputException {
        requireNotEmpty(value, fieldName);
        double result;
        try {
            result = Double.parseDouble(value.trim());
        } catch (NumberFormatException e) {
            throw new InvalidInputException(fieldName + " must be a number.");
        }
        if (result < 0) {
            throw new InvalidInputException(fieldName + " cannot be negative.");
        }
        return result;
    }
}
