package bootcamptask.actions;

import bootcamptask.model.Song;
import bootcamptask.utils.filewriters.SaveReport;
import bootcamptask.utils.listhandler.ListHandler;
import bootcamptask.utils.stringhandlers.ConsoleInputProvider;
import bootcamptask.utils.stringhandlers.InputProvider;
import bootcamptask.utils.stringhandlers.Messages;
import bootcamptask.utils.stringhandlers.PrintUtils;

import java.util.ArrayList;
import java.util.List;

public class PrintRankRaport {

    private List<Song> songs;
    private InputProvider inputProvider;

    public PrintRankRaport(List<Song> songs, InputProvider inputProvider) {
        this.songs = songs;
        this.inputProvider = inputProvider;
    }

    public void printReportByRank() {
        if (!songs.isEmpty()) {
            int userChoiceFromMenu;
            String userChoice;
            do {
                System.out.println("-------------------------------------");
                System.out.println("1. Wyświetl top 10 utworów");
                System.out.println("2. Wyświetl top 3 utworów");
                System.out.println("3. Wyświetl listę wszystkich utworów wg głosów");
                System.out.println("-------------------------------------");
                System.out.println("Podaj swój wybór:");
                userChoiceFromMenu = inputProvider.provideIntHandlingEmptyInput();
                switch (userChoiceFromMenu) {
                    case 1:
                        performAction(songs, 10);
                        break;
                    case 2:
                        performAction(songs, 3);
                        break;
                    case 3:
                        performAction(songs, songs.size());
                        break;
                    default:
                        System.out.println("\nNie wybrano poprawnej liczby z MENU\n");
                }
                Messages.showUserChooseMessage("WYŚWIETLIĆ listę wg innych kryteriów",
                        "wrócić do głównego MENU:");
                userChoice = inputProvider.provideStringHandlingEmptyInput();
            }
            while (userChoice.toLowerCase().equals("t"));
        } else {
            Messages.showNoElementsInCollectionMessage("do wyświetlenia");
            ConsoleInputProvider.waitForPressedEnter();
        }
    }

    private List<Song> createTopListByVotes(List<Song> songs, int numberOfPositionToShow) {
        List<Song> sortedSongsList = ListHandler.sortSongsByVotes(songs);
        List<Song> songsListToPrintAndSave = new ArrayList<>();
        int songsPool = songs.size();
        int tempMaxVotes = sortedSongsList.get(0).getVote();
        for (int i = 0; i < numberOfPositionToShow; i++) {
            if (songsPool == 0) {
                break;
            }
            for (Song song : sortedSongsList) {
                int votesToCompare = song.getVote();
                if (votesToCompare == tempMaxVotes) {
                    songsListToPrintAndSave.add(song);
                    songsPool--;
                } else if (votesToCompare < tempMaxVotes) {
                    tempMaxVotes = song.getVote();
                    break;
                }
            }
        }
        return songsListToPrintAndSave;
    }

    private void performAction(List<Song> songs, int numberOfPositionToShow) {
        String userChoice;
        List<Song> listToSave;
        listToSave = createTopListByVotes(songs, numberOfPositionToShow);
        PrintUtils.printSongsOnConsole(listToSave, numberOfPositionToShow);
        Messages.showUserChooseMessage("ZAPISAĆ listę do pliku", "wrócić do poprzedniego MENU:");
        userChoice = inputProvider.provideStringHandlingEmptyInput();
        if (userChoice.toLowerCase().equals("t")) {
            SaveReport.saveDbToFile(listToSave, inputProvider);
        }
    }
}

