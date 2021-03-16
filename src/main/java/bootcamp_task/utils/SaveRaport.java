package bootcamp_task.utils;

import bootcamp_task.model.Song;

import java.io.File;
import java.util.List;

public class SaveRaport {

    private static final String dbPath = StringUtils.selectDbPatch();

    public static void saveDbToFile(List<Song> songsToSave) {
        String userChoice;
        File createdFile;
        String fileName;

        do {
            System.out.println("Wybierz, czy chcesz zapisać listę do formatu -> CSV <-   lub  -> XML <- :");
            userChoice = ConsoleInputProvider.readStringFromUserHandlingEmptyInput();
            switch (userChoice) {
                case "CSV":
                    do {
                        System.out.println("Podaj nazwę pliku do zapisu: ");
                        fileName = ConsoleInputProvider.readStringFromUserHandlingEmptyInput();
                        createdFile = new File(dbPath + fileName + ".txt");
                        if (createdFile.exists()) {
                            System.out.println("Plik z podaną nazwa już istniene w folderze, podaj inną nazwę: ");
                        }
                    } while (createdFile.exists());

                    CSVHandler.saveListToCSVFile(songsToSave, createdFile);

                    System.out.println("Utworzono nową bazę danych o nazwię " + "' " + fileName + " '");
                    break;

                case "XML":
                    do {
                        System.out.println("Podaj nazwę pliku do zapisu: ");
                        fileName = ConsoleInputProvider.readStringFromUserHandlingEmptyInput();
                        createdFile = new File(dbPath + fileName + ".xml");
                        if (createdFile.exists()) {
                            System.out.println("Plik z podaną nazwa już istniene w folderze, podaj inną nazwę: ");
                        }
                    } while (createdFile.exists());

                    XMLHandler.saveXMLFile(songsToSave, createdFile);
                    System.out.println("Utworzono nową bazę danych o nazwię " + "' " + fileName + " '");
                    break;
                default:
                    System.out.println("Nie wybrano właściwego formatu pliku do zapisu lub błąd pisowni");
                    Messages.showEndingChooseMessage("wprowadzić właściwą opcję do zapisu");
            }
            userChoice = ConsoleInputProvider.readStringFromUserHandlingEmptyInput();
        } while (!userChoice.toLowerCase().equals("n"));
    }


}
