package bootcamp_task;

import bootcamp_task.actions.*;
import bootcamp_task.model.Song;
import bootcamp_task.utils.ConsoleInputProvider;

import java.util.ArrayList;
import java.util.List;

class SongsCollection {

    private List<Song> songs;

    private AddSong addSong;
    private AddVoice addVoice;
    private DeleteVoices deleteVoices;
    private PrintRankRaport printRankRaport;
    private PrintCategoryRaport printCategoryRaport;
    private LoadSongsList loadSongsList;

    SongsCollection() {
        this(new ArrayList<>());
    }

    private SongsCollection(List<Song> songs) {
        initializeActions(songs);
    }

    private void initializeActions(List<Song> songs) {

        this.songs = songs;
        this.addVoice = new AddVoice(songs);
        this.addSong = new AddSong(songs);
        this.deleteVoices = new DeleteVoices(songs);
        this.printRankRaport = new PrintRankRaport(songs);
        this.printCategoryRaport = new PrintCategoryRaport(songs);
        this.loadSongsList = new LoadSongsList();
    }

    void start() {

        int number;
        do {
            welcomeMenu();
            number = ConsoleInputProvider.readIntFromUserHandlingEmptyInput();
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
        } while (number!=7);
    }

    private void welcomeMenu() {
        System.out.println("\nObsługa menu głównego i wewnętrznych poprzez wybranie odpowiednich cyfr");
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
        this.addVoice.addVoicesToSong(songs);
    }

    private void addSong() {
        this.addSong.addSongToCollection(songs);
    }

    private void deleteVoices() {
        this.deleteVoices.deleteVoicesFromSongs(songs);
    }

    private void printRankRaport() {
        this.printRankRaport.printRaportByRank(songs);
    }

    private void printCategoryRaport() {
        this.printCategoryRaport.printRaportByCategory(songs);
    }

    private void loadCollection() {
        this.songs = this.loadSongsList.loadListFromFile(songs);
    }

    private void endProgramm() {
        ConsoleInputProvider.closeScanner();
        System.exit(0);
    }

}
