package bootcamp_task.actions;

import bootcamp_task.model.Song;
import bootcamp_task.utils.CSVHandler;
import bootcamp_task.utils.ConsoleInputProvider;
import bootcamp_task.utils.Messages;
import bootcamp_task.utils.XMLHandler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LoadSongsList {

    CSVHandler csvHandler = new CSVHandler();
    XMLHandler xmlHandler = new XMLHandler();

    private String dbPath = "D:\\test\\";

    void createDBFolder(File file) {
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    public List<Song> loadListFromFile(List<Song> songsInMemory) {

        List<Song> songsFromFile = new ArrayList<>();
        List<Song> songsAfterComparison = new ArrayList<>();
        File file;
        String userChoice;
        String fileExtension;

        createDBFolder(new File(dbPath));

        do {
            System.out.println("Podaj czy plik do wczystania jest w formacie -> CSV <-   lub  -> XML <- :");
            userChoice = ConsoleInputProvider.readStringFromUserHandlingEmptyInput();

            switch (userChoice) {
                case "CSV":
                    fileExtension = ".txt";
                    if (checkIfAnyfileFits(fileExtension)) {
                        System.out.println("\nZawartość katalogu z plikami:\n");
                        showCustomFiles(".txt");
                        System.out.println("\nWybierz plik do wczytania (bez rozszerzenia):");

                        do {
                            String fileName = ConsoleInputProvider.readStringFromUserHandlingEmptyInput();
                            file = new File(dbPath + "\\" + fileName + ".txt");
                            if (file.exists()) {
                                try {
                                    songsFromFile = csvHandler.readCSVFromList(file);
                                } catch (RuntimeException e) {
                                    System.out.println("Niepoprawny format danych wewnątrz pliku, brak możliwości otworzenia");
                                }
                            } else {
                                System.out.println("Niepoprawna nazwa pliku do odczytu, podaj właściwą nazwę (bez rozszerzenia):");
                            }
                        } while (!file.exists());

                    } else {
                        System.out.println("Brak plików o podanym rozszerzeniu w folderze");
                    }
                    break;
                case "XML":
                    fileExtension = ".xml";
                    if (checkIfAnyfileFits(fileExtension)) {
                        System.out.println("\nZawartość katalogu z plikami:\n");
                        showCustomFiles(".xml");
                        System.out.println("\nWybierz plik do wczytania (bez rozszerzenia): ");

                        do {
                            String dbName = ConsoleInputProvider.readStringFromUserHandlingEmptyInput();
                            file = new File(dbPath + "\\" + dbName + ".xml");
                            if (file.exists()) {
                                try {
                                    songsFromFile = xmlHandler.parseXMLToList(file);
                                } catch (Exception t) {
                                    System.out.println("Niepoprawny format danych wewnątrz pliku, brak możliwości otworzenia");
                                }
                            } else {
                                System.out.println("Niepoprawna nazwa pliku do odczytu, podaj właściwą nazwę:");
                            }
                        } while (!file.exists());
                    } else {
                        System.out.println("Brak plików o podanym rozszerzeniu w folderze");
                    }
                    break;

                default:
                    System.out.println("Nie podano właściwego rozszerzenia, wpisz -> CSV <-   lub  -> XML <- :");
            }
            songsAfterComparison = compareSongsFromFileWihSongsInMemory(songsInMemory, songsFromFile);

            Messages.showEndingChooseMessage("spróbować znaleźć plik z innym rozszerzeniem");

            userChoice = ConsoleInputProvider.readStringFromUserHandlingEmptyInput();
        }
        while (userChoice.toLowerCase().equals("t"));


        return songsAfterComparison;
    }

    private File[] loadCustomFilesFromFolder(String filetype) {
        File file = new File(dbPath);
        return file.listFiles((file1, name) -> name.endsWith(filetype));
    }

    private void showCustomFiles(String filetype) {
        File[] files = loadCustomFilesFromFolder(filetype);
        for (int i = 0; i < files.length; i++) {
            System.out.println(files[i]);
        }
    }

    private boolean checkIfAnyfileFits(String filetype) {
        boolean isAnyfileCorrect = true;
        if (loadCustomFilesFromFolder(filetype).length == 0) {
            isAnyfileCorrect = false;
        }
        return isAnyfileCorrect;
    }

    public List<Song> compareSongsFromFileWihSongsInMemory(List<Song> songsInMemory, List<Song> songsFromFile) {

        int numberOfLoops = songsInMemory.size();

        if (songsInMemory.isEmpty()) {
            songsInMemory.addAll(songsFromFile);

        } else {
            for (int i = 0; i < numberOfLoops; i++) {

                for (int j = 0; j < songsFromFile.size(); j++) {
                    if (songsInMemory.get(i).getTitle().equals(songsFromFile.get(j).getTitle())
                            && songsInMemory.get(i).getAuthor().equals(songsFromFile.get(j).getAuthor())) {
                        int tempVotes = songsInMemory.get(i).getVote();
                        songsInMemory.get(i).setVote(tempVotes + songsFromFile.get(j).getVote());

                    } else {
                        if (!songsInMemory.contains(songsFromFile.get(j)))
                            songsInMemory.add(songsFromFile.get(j));
                    }
                }
            }

        }
        return songsInMemory;
    }


}



