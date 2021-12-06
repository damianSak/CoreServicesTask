package bootcamptask;

import bootcamptask.actions.*;
import bootcamptask.model.Song;
import bootcamptask.utils.stringhandlers.ConsoleInputProvider;
import bootcamptask.utils.stringhandlers.InputProvider;

import java.util.ArrayList;
import java.util.List;

public class SongsCollection {

    public static String ORIGIN_PATH;

    private List<Song> songs;

    /**
     * Actions
     */
    private AddSong addSong;
    private AddVoice addVoice;
    private DeleteVoices deleteVoices;
    private PrintRankRaport printRankReport;
    private PrintCategoryReport printCategoryReport;
    private LoadSongsList loadSongsList;

    private InputProvider inputProvider;

    SongsCollection(String originPath) {
        this(new ConsoleInputProvider(), new ArrayList<>(), originPath);
    }

    private SongsCollection(InputProvider inputProvider, List<Song> songs, String originPath) {
        this.songs = songs;
        this.inputProvider = inputProvider;
        initializeActions(this.songs);
        ORIGIN_PATH = originPath;
    }

    private void initializeActions(List<Song> songs) {
        this.addVoice = new AddVoice(songs, inputProvider);
        this.addSong = new AddSong(songs, inputProvider);
        this.deleteVoices = new DeleteVoices(songs, inputProvider);
        this.printRankReport = new PrintRankRaport(songs, inputProvider);
        this.printCategoryReport = new PrintCategoryReport(songs, inputProvider);
        this.loadSongsList = new LoadSongsList(songs, inputProvider);
    }

    void start() {
        int number;
        do {
            welcomeMenu();
            number = inputProvider.provideIntHandlingEmptyInput();
            switch (number) {
                case 1:
                    loadCollection();
                    break;
                case 2:
                    addVoice();
                    break;
                case 3:
                    addSong();
                    break;
                case 4:
                    deleteVoices();
                    break;
                case 5:
                    printRankReport();
                    break;
                case 6:
                    printCategoryReport();
                    break;
                case 7:
                    endProgram();
                    break;
                default:
                    System.out.println("To nie jest poprawnie wybrana opcja z MENU, wybierz właściwą cyfrę: ");
            }
        } while (number != 7);
    }

    private void welcomeMenu() {
        System.out.println("\nObsługa menu głównego i wewnętrznych poprzez wybranie odpowiednich LICZB");
        System.out.println("------------------------------------------------------------------------");
        System.out.println("1. Wczytaj listę utworów z pliku");
        System.out.println("2. Oddaj głos na utwór");
        System.out.println("3. Dodaj utwór do listy");
        System.out.println("4. Wyzeruj głosy dla utworu/wszystkich utworów");
        System.out.println("5. Raport z rankingu");
        System.out.println("6. Raport wg kategorii muzycznych");
        System.out.println("7. Zakończ program");
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Wybierz opcję:");
    }

    private void addVoice() {
        this.addVoice.addVoicesToSong();
    }

    private void addSong() {
        this.addSong.addNewSongToCollection();
    }

    private void deleteVoices() {
        this.deleteVoices.deleteVoicesFromSongs();
    }

    private void printRankReport() {
        this.printRankReport.printReportByRank();
    }

    private void printCategoryReport() {
        this.printCategoryReport.printReportByCategory();
    }

    private void loadCollection() {
        this.songs = this.loadSongsList.loadListFromFile();
    }

    private void endProgram() {
        ConsoleInputProvider.closeScanner();
        System.exit(0);
    }

}
