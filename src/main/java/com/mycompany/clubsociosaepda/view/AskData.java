/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clubsociosaepda.view;

/**
 *
 * @author andre
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author andre
 */
public class AskData {

    /**
     * Reader used to capture user input from the standard input stream.
     */
    private BufferedReader br;

    /**
     * Constructs a new {@code AskData} instance and initialises the
     * {@link BufferedReader} over {@link System#in}.
     */
    public AskData() {
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    // -------------------------------------------------------------------------
    // Integer input methods
    // -------------------------------------------------------------------------
    /**
     * Prints a message and reads a single integer from the console. No
     * validation is performed; the caller is responsible for handling invalid
     * input.
     *
     * @param message the prompt to display to the user
     * @return the integer value entered by the user
     * @throws IOException if an I/O error occurs while reading
     * @throws NumberFormatException if the input cannot be parsed as an integer
     */
    public int askInt(String message) throws IOException {
        boolean error = false;
        int n = 0;
        do {
            try {
                System.out.print(message);
                n = Integer.parseInt(br.readLine());
                error = false;
            } catch (NumberFormatException ex) {
                error = true;
                System.out.println("Debes poner un número entero.");
            }
        } while (error);
        return n;
    }

    /**
     * Prompts the user for an integer and validates that it is greater than or
     * equal to a minimum value. The user is re-prompted until a valid value is
     * entered.
     *
     * @param message the prompt to display to the user
     * @param errorMessage the message to display when the entered value is
     * below {@code min}
     * @param min the minimum accepted value (inclusive)
     * @return an integer value {@code >= min}
     * @throws IOException if an I/O error occurs while reading
     */
    public int askInt(String message, String errorMessage, int min) throws IOException {
        int n;
        do {
            n = askInt(message);
            if (n < min) {
                System.out.println(errorMessage);
            }
        } while (n < min);
        return n;
    }

    /**
     * Prompts the user for an integer and validates that it falls within a
     * closed range [{@code min}, {@code max}]. The user is re-prompted until a
     * valid value is entered.
     *
     * @param message the prompt to display to the user
     * @param errorMessage the message to display when the entered value is out
     * of range
     * @param min the minimum accepted value (inclusive)
     * @param max the maximum accepted value (inclusive)
     * @return an integer value in the range [{@code min}, {@code max}]
     * @throws IOException if an I/O error occurs while reading
     */
    public int askInt(String message, String errorMessage, int min, int max) throws IOException {
        int n;
        do {
            n = askInt(message);
            if (n < min || n > max) {
                System.out.println(errorMessage);
            }
        } while (n < min || n > max);
        return n;
    }

    /**
     * Prompts the user for an integer and validates that it belongs to a given
     * list of accepted values. The user is re-prompted until a valid value is
     * entered.
     *
     * @param message the prompt to display to the user
     * @param errorMessage the message to display when the entered value is not
     * in the list
     * @param numsAccepted the list of valid integer values
     * @return an integer value contained in {@code numsAccepted}
     * @throws IOException if an I/O error occurs while reading
     */
    public int askInt(String message, String errorMessage, ArrayList<Integer> numsAccepted) throws IOException {
        int n;
        do {
            n = askInt(message);
            if (!numsAccepted.contains(n)) {
                System.out.println(errorMessage);
            }
        } while (!numsAccepted.contains(n));
        return n;
    }

    // -------------------------------------------------------------------------
    // Double input methods
    // -------------------------------------------------------------------------
    /**
     * Prints a message and reads a single {@code double} from the console. No
     * validation is performed; the caller is responsible for handling invalid
     * input.
     *
     * @param message the prompt to display to the user
     * @return the {@code double} value entered by the user
     * @throws IOException if an I/O error occurs while reading
     * @throws NumberFormatException if the input cannot be parsed as a
     * {@code double}
     */
    public double askDouble(String message) throws IOException {
        boolean error = false;
        double n = 0;
        do {
            try {
            System.out.print(message);
            n = Double.parseDouble(br.readLine());
            error = false;
            } catch (NumberFormatException ex) {
                error = true;
                System.out.println("Debes poner un número (puede ser con decimales).");
            }
        } while (error);
        return n;
    }

    /**
     * Prompts the user for a {@code double} and validates that it is greater
     * than or equal to a minimum value. The user is re-prompted until a valid
     * value is entered.
     *
     * @param message the prompt to display to the user
     * @param errorMessage the message to display when the entered value is
     * below {@code min}
     * @param min the minimum accepted value (inclusive)
     * @return a {@code double} value {@code >= min}
     * @throws IOException if an I/O error occurs while reading
     */

    public double askDouble(String message, String errorMessage, double min) throws IOException {
        double n;
        do {
            n = askDouble(message);
            if (n < min) {
                System.out.print(errorMessage);
            }
        } while (n < min);
        return n;
    }

    /**
     * Prompts the user for a {@code double} and validates that it falls within
     * a closed range [{@code min}, {@code max}]. The user is re-prompted until
     * a valid value is entered.
     *
     * @param message the prompt to display to the user
     * @param errorMessage the message to display when the entered value is out
     * of range
     * @param min the minimum accepted value (inclusive)
     * @param max the maximum accepted value (inclusive)
     * @return a {@code double} value in the range [{@code min}, {@code max}]
     * @throws IOException if an I/O error occurs while reading
     */
    public double askDouble(String message, String errorMessage, double min, double max) throws IOException {
        double n;
        do {
            n = askDouble(message);
            if (n < min || n > max) {
                System.out.print(errorMessage);
            }
        } while (n < min || n > max);
        return n;
    }

    // -------------------------------------------------------------------------
    // String input methods
    // -------------------------------------------------------------------------
    /**
     * Prompts the user for a non-blank string. The user is re-prompted until a
     * non-empty, non-whitespace-only value is entered.
     *
     * <p>
     * If the user enters a blank value, the message
     * {@code "No es pot deixar en blanc."} is displayed.</p>
     *
     * @param message the prompt to display to the user
     * @return a non-blank {@link String} entered by the user
     * @throws IOException if an I/O error occurs while reading
     */
    public String askString(String message) throws IOException {
        String ask = "";
        do {
            System.out.print(message);
            ask = br.readLine();
            if (ask.isBlank()) {
                System.out.println("No es pot deixar en blanc.");
            }
        } while (ask.isBlank());
        return ask;
    }

    /**
     * Prompts the user for a string and validates that it matches one of two
     * accepted options (case-insensitive). The user is re-prompted until a
     * valid option is entered.
     *
     * @param message the prompt to display to the user
     * @param errorMessage the message to display when the input does not match
     * either option
     * @param option1 the first accepted string value (case-insensitive)
     * @param option2 the second accepted string value (case-insensitive)
     * @return the entered string, which equals either {@code option1} or
     * {@code option2} ignoring case
     * @throws IOException if an I/O error occurs while reading
     */
    public String askString(String message, String errorMessage, String option1, String option2) throws IOException {
        String ask;
        do {
            ask = askString(message);
            if (!ask.equalsIgnoreCase(option1) && !ask.equalsIgnoreCase(option2)) {
                System.out.println(errorMessage);
            }
        } while (!ask.equalsIgnoreCase(option1) && !ask.equalsIgnoreCase(option2));
        return ask;
    }

    /**
     * Prompts the user for a string and validates that it is contained within a
     * list of accepted values. The user's input is automatically converted to
     * uppercase before validation.
     *
     * <p>
     * If the entered value is not in the list, an error message is displayed
     * listing all accepted words, and the user is re-prompted until a valid
     * value is provided.</p>
     *
     * @param message the prompt to display to the user
     * @param wordsAccepted the list of valid string values (comparison is
     * case-insensitive as the input is converted to uppercase)
     * @return the validated string in uppercase, guaranteed to be present in
     * {@code wordsAccepted}
     * @throws IOException if an I/O error occurs while reading
     */
    public String askString(String message, List<String> wordsAccepted) throws IOException {
        String ask;
        do {
            ask = askString(message).toUpperCase();
            if (!wordsAccepted.contains(ask)) {
                System.out.println("Wrong answer. Words Accepted: ");
                for (String w : wordsAccepted) {
                    System.out.print(w + " ");
                }
                System.out.println();
            }
        } while (!wordsAccepted.contains(ask));
        return ask;
    }

    // -------------------------------------------------------------------------
    // Boolean input method
    // -------------------------------------------------------------------------
    /**
     * Prompts the user for a boolean value represented by two string options
     * (e.g. {@code "yes"} / {@code "no"}). The comparison is case-insensitive.
     * The user is re-prompted until one of the two accepted options is entered.
     *
     * @param message the prompt to display to the user
     * @param errorMessage the message to display when the input does not match
     * either option
     * @param optionTrue the string that maps to {@code true} (case-insensitive)
     * @param optionFalse the string that maps to {@code false}
     * (case-insensitive)
     * @return {@code true} if the user entered {@code optionTrue},
     * {@code false} if the user entered {@code optionFalse}
     * @throws IOException if an I/O error occurs while reading
     */
    public boolean askBoolean(String message, String errorMessage, String optionTrue, String optionFalse) throws IOException {
        String ask;
        do {
            ask = askString(message);
            if (!ask.equalsIgnoreCase(optionTrue) && !ask.equalsIgnoreCase(optionFalse)) {
                System.out.println(errorMessage);
            }
        } while (!ask.equalsIgnoreCase(optionTrue) && !ask.equalsIgnoreCase(optionFalse));
        return ask.equalsIgnoreCase(optionTrue);
    }
}
