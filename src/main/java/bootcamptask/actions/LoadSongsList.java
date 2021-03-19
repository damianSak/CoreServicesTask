package bootcamptask.actions;

import bootcamptask.SongsCollection;
import bootcamptask.model.Song;
import bootcamptask.utils.parsers.CSVHandler;
import bootcamptask.utils.parsers.XMLHandler;
import bootcamptask.utils.stringhandlers.InputProvider;
import bootcamptask.utils.stringhandlers.Messages;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LoadSongsList {

    private List<Song> songs;
    private InputProvider inputProvider;

    public LoadSongsList(List<Song> songs, InputProvider inputProvider) {
        this.songs = songs;
        this.inputProvider = inputProvider;
    }

    public List<Song> loadListFromFile() {
        List<Song> songsFromFile = new ArrayList<>();
        List<Song> songsAfterComparison;
        String userChoice;

        do {
            System.out.println("Podaj czy plik do wczystania jest w formacie -> CSV <-   lub  -> XML <- :");
            System.out.println("Wpisz swój wybór:");
            userChoice = inputProvider.provideStringHandlingEmptyInput();
            switch (userChoice) {
                case "CSV":
                    songsFromFile = performAction(userChoice);
                    break;
                case "XML":
                    songsFromFile = performAction(userChoice);
                    break;
                default:
                    System.out.println("Nie podano właściwego rozszerzenia, wpisz -> CSV <-   lub  -> XML <- :");
            }
            songsAfterComparison = compareSongsFromFileWihSongsInMemory(songs, songsFromFile);

            Messages.showUserChooseMessage("WCZYTAĆ inny plik, spróbować znaleźć plik z innym " +
                    "rozszerzeniem", " wrócić do głównego MENU:");

            userChoice = inputProvider.provideStringHandlingEmptyInput();
        }
        while (userChoice.toLowerCase().equals("t"));
        return songsAfterComparison;
    }

    private File[] loadCustomFilesFromFolder(String filetype) {
        File file = new File(SongsCollection.ORIGIN_PATH);
        return file.listFiles((file1, name) -> name.endsWith(filetype));
    }

    private void showCustomFiles(String filetype) {
        File[] files = loadCustomFilesFromFolder(filetype);
        for (File file : files) {
            System.out.println(file);
        }
    }

    private boolean checkIfAnyfileFits(String filetype) {
        boolean isAnyfileCorrect = true;
        if (loadCustomFilesFromFolder(filetype).length == 0) {
            isAnyfileCorrect = false;
        }
        return isAnyfileCorrect;
    }

    private List<Song> compareSongsFromFileWihSongsInMemory(List<Song> songsInMemory, List<Song> songsFromFile) {
        int numberOfLoops = songsInMemory.size();
        if (songsInMemory.isEmpty()) {
            songsInMemory.addAll(songsFromFile);
        } else {
            for (int i = 0; i < numberOfLoops; i++) {
                for (Song song : songsFromFile) {
                    if (songsInMemory.get(i).getTitle().equals(song.getTitle())
                            && songsInMemory.get(i).getAuthor().equals(song.getAuthor())) {
                        int tempVotes = songsInMemory.get(i).getVote();
                        songsInMemory.get(i).setVote(tempVotes + song.getVote());
                    } else {
                        if (!songsInMemory.contains(song))
                            songsInMemory.add(song);
                    }
                }
            }
        }
        return songsInMemory;
    }

    private List<Song> performAction(String userChoice){
        List<Song> songsFromFile=new ArrayList<>();
        String fileExtension="";
        if(userChoice.equals("CSV")){
             fileExtension = ".csv";
        }else if(userChoice.equals("XML")){
            fileExtension=".xml";
        }
        if (checkIfAnyfileFits(fileExtension)) {
            System.out.println("\nZawartość katalogu z plikami:\n");
            showCustomFiles(fileExtension);
            System.out.println("\nPodaj plik do wczytania (bez rozszerzenia):");
            File file;
            do {
                String fileName = inputProvider.provideStringHandlingEmptyInput();
                file = new File(SongsCollection.ORIGIN_PATH + "\\" + fileName + fileExtension);
                if (file.exists()) {
                    try {
                        if(userChoice.equals("CSV")){
                            songsFromFile = CSVHandler.readCSVFromList(file);
                        }else if(userChoice.equals("XML")){
                            songsFromFile = XMLHandler.parseXMLFileToList(file);
                        }
                    } catch (Exception e) {
                        System.out.println("Niepoprawny format danych wewnątrz pliku, brak możliwości otworzenia");
                    }
                } else {
                    System.out.println("Niepoprawna nazwa pliku do odczytu, podaj właściwą nazwę (bez rozszerzenia):");
                }
            } while (!file.exists());
        } else {
            System.out.println("Brak plików o podanym rozszerzeniu w folderze");
        }
        return songsFromFile;
    }
}



