package bootcamp_task.utils;

import bootcamp_task.model.Genre;
import bootcamp_task.model.Song;

import java.util.List;
import java.util.Locale;


public class StringUtils {

    /**
     * by putting as a char end of the line ('c = \n' ) you
     * can count how many end of lines are in choosed string;
     */

    public static int countChar(String str, char c) {
        int count = 0;

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == c)
                count++;
        }
        return count;
    }

    public static void printSingleRecord(String title, String author, String album, Genre category, int votes) {
        System.out.format(Locale.GERMAN, "%-25s|%-25s|%-42s| %-20s| %-16d\n",
                title, author, album, category,votes);

    }
    public static String selectDbPatch(){
        return "D:\\Album DB\\";
    }

    public static void printHeading() {
        System.out.println( "-------------------------+-------------------------+------------------------------------------+---------------------+----------------\n" +
                "          Tytuł          |          Autor          |                  Płyta                   |       Gatunek       |  Ilość głosów   \n" +
                "-------------------------+-------------------------+------------------------------------------+---------------------+----------------");
    }

    private static void printAlbumRecrds(List<Song> songs) {
        for (Song song : songs) {
            StringUtils.printSingleRecord(song.getTitle(), song.getAuthor(), song.getAlbum(), song.getCategory(),song.getVote());
        }
    }

    public static void printEnding() {
        System.out.println("-------------------------+-------------------------+------------------------------------------+---------------------+----------------");
    }

    public static void printSongsCollectionOnConsole(List<Song> songs) {

        printHeading();
        printAlbumRecrds(songs);
        printEnding();
    }

    public static String readStringFromUserHandlingEmptyInput(String mainMessage, String exceptionMessage) {
        String result;
        do {
            System.out.println(mainMessage);
            result = ConsoleInputProvider.readStringFromUserHandlingEmptyInput();
            if (result.isEmpty()) {
                System.out.println(exceptionMessage);
            }
        } while (result.isEmpty());

        return result;
    }
}
