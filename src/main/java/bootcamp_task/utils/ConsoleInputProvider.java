package bootcamp_task.utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleInputProvider {

    private static Scanner scanner = new Scanner(System.in);

    public static int readIntFromUserHandlingEmptyInput() {
        int number = 0;
        boolean exceptionOccurred;
        do {
            try {
                exceptionOccurred = false;
                number = scanner.nextInt();
                scanner.nextLine();
                if (String.valueOf(number).length() == 0) {
                    System.out.println("Nie wprowadzono żadnej liczby");
                }
            } catch (InputMismatchException e) {
                exceptionOccurred = true;
                System.out.println("Wprowadzona wartość nie jest liczbą całkowitą, podaj własciwą liczbę");
                scanner.next();
            }
        } while (exceptionOccurred);
        return number;
    }

    public static String readStringFromUserHandlingEmptyInput() {
        String string;
        do {
            string = scanner.nextLine().trim();
            if (string.isEmpty()) {
                System.out.println("Nie wprowadzono żadnego słowa");
            }

        } while (string.isEmpty());
        return string;
    }

    public static void closeScanner() {
        scanner.close();
    }

}
