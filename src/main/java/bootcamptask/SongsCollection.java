package bootcamptask;

import bootcamptask.actions.*;
import bootcamptask.model.Song;
import bootcamptask.utils.stringhandlers.ConsoleInputProvider;
import bootcamptask.utils.stringhandlers.InputProvider;

import java.util.ArrayList;
import java.util.List;

public class SongsCollection {

    public static final String ORIGIN_PATH = "test";

    private List<Song> songs;

    /**
     * Actions
     */
    private AddSong addSong;
    private AddVoice addVoice;
    private DeleteVoices deleteVoices;
    private PrintRankRaport printRankRaport;
    private PrintCategoryReport printCategoryRaport;
    private LoadSongsList loadSongsList;

    private InputProvider inputProvider;

    SongsCollection() {
        this(new ConsoleInputProvider(), new ArrayList<>());
    }

    private SongsCollection(InputProvider inputProvider, List<Song> songs) {
        this.songs = songs;
        this.inputProvider = inputProvider;
        initializeActions(new ArrayList<>());
    }

    private void initializeActions(List<Song> songs) {
        this.addVoice = new AddVoice(songs, inputProvider);
        this.addSong = new AddSong(songs, inputProvider);
        this.deleteVoices = new DeleteVoices(songs, inputProvider);
        this.printRankRaport = new PrintRankRaport(songs, inputProvider);
        this.printCategoryRaport = new PrintCategoryReport(songs, inputProvider);
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
                    printRankRaport();
                    break;
                case 6:
                    printCategoryRaport();
                    break;
                case 7:
                    endProgramm();
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

    private void printRankRaport() {
        this.printRankRaport.printReportByRank();
    }

    private void printCategoryRaport() {
        this.printCategoryRaport.printReportByCategory();
    }

    private void loadCollection() {
        this.songs = this.loadSongsList.loadListFromFile();
    }

    private void endProgramm() {
        ConsoleInputProvider.closeScanner();
        System.exit(0);
    }

}
