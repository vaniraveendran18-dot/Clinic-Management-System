package util;

import java.time.LocalDate;
import java.time.LocalTime;
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

    public static void requireValidTime(String value, String fieldName) throws InvalidInputException {
        requireNotEmpty(value, fieldName);
        try {
            LocalTime.parse(value.trim());
        } catch (DateTimeParseException e) {
            throw new InvalidInputException(fieldName + " must be in HH:mm format, e.g. 09:30.");
        }
    }

    public static void requireValidPhone(String value, String fieldName) throws InvalidInputException {
        requireNotEmpty(value, fieldName);
        if (!value.trim().replace(" ", "").matches("\\+?\\d{8,12}")) {
            throw new InvalidInputException(fieldName + " must be 8-12 digits, e.g. 0412345678.");
        }
    }
}
