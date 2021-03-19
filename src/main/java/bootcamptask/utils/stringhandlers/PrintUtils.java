package bootcamptask.utils.stringhandlers;

import bootcamptask.model.Genre;
import bootcamptask.model.Song;

import java.util.List;
import java.util.Locale;

public class PrintUtils {

    public static void printSongsOnConsole(List<Song> songsToPrint, int numberOfRecordsToShow) {
        int tempMaxVotes = songsToPrint.get(0).getVote();
        int rank = 1;
        int songsPool = songsToPrint.size();

        printHeading();
        for (int i = 0; i < numberOfRecordsToShow; i++) {
            if (songsPool == 0) {
                break;
            }
            System.out.println("\n-------------------------------------------------------------> MIEJSCE " + rank +
                    " <-----------------------------------------------------------\n");
            for (Song song : songsToPrint) {
                if (tempMaxVotes == song.getVote()) {
                    printSingleRecord(song.getTitle(), song.getAuthor(),
                            song.getAlbum(), song.getCategory(), song.getVote());
                    songsPool--;
                } else if (tempMaxVotes > song.getVote()) {
                    tempMaxVotes = song.getVote();
                    rank++;
                    break;
                }
            }
        }
        if (rank < numberOfRecordsToShow) {
            System.out.println("\n--------------------------------------> Brak większej ilości utworów " +
                    "do wyświetlenia z listy <---------------------------------------\n");
        }
        printEnding();
    }

    public static void printSingleRecord(String title, String author, String album, Genre category, int votes) {
        System.out.format(Locale.GERMAN, "%-25s| %-24s| %-41s| %-20s| %-16d\n",
                title, author, album, category, votes);

    }

    public static void printHeading() {
        System.out.println("-------------------------+-------------------------+" +
                "------------------------------------------+---------------------+----------------\n" +
                "          Tytuł          |          Autor          |                  Płyta                   " +
                "|       Gatunek       |  Ilość głosów   \n" +
                "-------------------------+-------------------------+------------------------------------------+" +
                "---------------------+----------------");
    }

    private static void printAlbumRecrds(List<Song> songs) {
        for (Song song : songs) {
            printSingleRecord(song.getTitle(), song.getAuthor(), song.getAlbum(), song.getCategory(), song.getVote());
        }
    }

    public static void printEnding() {
        System.out.println("-------------------------+-------------------------+" +
                "------------------------------------------+---------------------+----------------");
    }

    public static void printSongsCollectionOnConsole(List<Song> songs) {
        printHeading();
        printAlbumRecrds(songs);
        printEnding();
    }
}
