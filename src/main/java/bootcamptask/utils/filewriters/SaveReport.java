package bootcamptask.utils.filewriters;

import bootcamptask.SongsCollection;
import bootcamptask.model.Song;
import bootcamptask.utils.parsers.CSVHandler;
import bootcamptask.utils.parsers.XMLHandler;
import bootcamptask.utils.stringhandlers.InputProvider;
import bootcamptask.utils.stringhandlers.Messages;

import java.io.File;
import java.util.List;

public class SaveReport {

    private static final String filePatch = SongsCollection.ORIGIN_PATH;

    public static void saveDbToFile(List<Song> songsToSave, InputProvider inputProvider) {
        String userChoice;

        do {
            System.out.println("Wybierz, czy chcesz zapisać listę do formatu -> CSV <-   lub  -> XML <- :");
            userChoice = inputProvider.provideStringHandlingEmptyInput();
            switch (userChoice) {
                case "CSV":
                   performSaveToFileAction(userChoice,songsToSave, inputProvider);
                    break;

                case "XML":
                    performSaveToFileAction(userChoice,songsToSave, inputProvider);
                    break;
                default:
                    System.out.println("Nie wybrano właściwego formatu pliku do zapisu lub błąd pisowni");

            }
            Messages.showUserChooseMessage("spróbować ZAPISAĆ listę jeszcze raz","poprzedniego MENU: ");
            userChoice = inputProvider.provideStringHandlingEmptyInput();
        } while (userChoice.toLowerCase().equals("t"));

    }

    private static void performSaveToFileAction(String userChoice, List<Song> songsToSave, InputProvider inputProvider) {
        File createdFile;
        String fileName;
        String fileType = "";
        if (userChoice.equals("CSV")) {
            fileType = ".csv";
        } else if (userChoice.equals("XML")) {
            fileType = ".xml";
        }
        do {
            System.out.println("Podaj nazwę pliku do zapisu (bez rozszerzenia): ");
            fileName = inputProvider.provideStringHandlingEmptyInput();
            createdFile = new File(filePatch + fileName + fileType);
            if (createdFile.exists()) {
                System.out.println("Plik z podaną nazwa już istniene w folderze, podaj inną nazwę: ");
            }
        } while (createdFile.exists());

        if (userChoice.equals("CSV")) {
            CSVHandler.saveListToCSVFile(songsToSave, createdFile);
        } else if (userChoice.equals("XML")) {
            XMLHandler.saveXMLToFile(songsToSave, createdFile);
        }
        System.out.println("Utworzono nowy plik o nazwię " + "' " + fileName +fileType +" '");
    }

}
