package bootcamptask.utils.stringhandlers;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleInputProvider implements InputProvider {

    private static Scanner scanner = new Scanner(System.in);

    public static void waitForPressedEnter() {
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String provideStringHandlingEmptyInput() {
        return provideStringHandlingEmptyInputCustomErrorMessage(null,"Nie wprowadzono żadnej liczby");
    }

    @Override
    public String provideStringHandlingEmptyInputCustomErrorMessage(String mainMessage, String exceptionMessage) {
        String string;
        do {
            if (!StringUtils.isEmpty(mainMessage)) {
                System.out.println(mainMessage);
            }
            string = scanner.nextLine().trim();
            if (string.isEmpty()) {
                System.out.println("Nie wprowadzono żadnego słowa");
            }
        } while (string.isEmpty());
        return string;
    }


    @Override
    public int provideIntHandlingEmptyInput() {
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

    public static void closeScanner() {
        scanner.close();
    }

}
