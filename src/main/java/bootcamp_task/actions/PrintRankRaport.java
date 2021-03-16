package bootcamp_task.actions;

import bootcamp_task.model.Song;
import bootcamp_task.utils.ConsoleInputProvider;
import bootcamp_task.utils.Messages;
import bootcamp_task.utils.SaveRaport;
import bootcamp_task.utils.StringUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PrintRankRaport {

    private List<Song> songs;

    public PrintRankRaport(List<Song> songs) {
        this.songs = songs;
    }

    public void printRaportByRank(List<Song> songs) {

        int userChoiceFromMenu;
        String userChoice;
        List<Song> listToSave;

        do {
            System.out.println("-------------------------------------");
            System.out.println("1. Wyświetl top 10 utworów");
            System.out.println("2. Wyświetl top 3 utworów");
            System.out.println("3. Wyświetl listę wszystkich utworów wg głosów");
            System.out.println("-------------------------------------");
            System.out.println("Podaj swój wybór:");

            userChoiceFromMenu = ConsoleInputProvider.readIntFromUserHandlingEmptyInput();
            switch (userChoiceFromMenu) {
                case 1:
                    listToSave = printSongsOnConsole(10, songs);
                    System.out.println("\n Wprowadź 'T/t' jeśli chcesz zapisać listę do pliku");
                    userChoice = ConsoleInputProvider.readStringFromUserHandlingEmptyInput();
                    if(userChoice.toLowerCase().equals("t")){
                        SaveRaport.saveDbToFile(listToSave);
                    }
                    break;

                case 2:
                    listToSave = printSongsOnConsole(3, songs);
                    System.out.println("\n Wprowadź 'T/t' jeśli chcesz zapisać listę do pliku");
                    userChoice = ConsoleInputProvider.readStringFromUserHandlingEmptyInput();
                    if(userChoice.toLowerCase().equals("t")){
                        SaveRaport.saveDbToFile(listToSave);
                    }
                    break;

                case 3:
                    listToSave = printSongsOnConsole(songs.size(), songs);
                    System.out.println("\n Wprowadź 'T/t' jeśli chcesz zapisać listę do pliku");
                    userChoice = ConsoleInputProvider.readStringFromUserHandlingEmptyInput();
                    if(userChoice.toLowerCase().equals("t")){
                        SaveRaport.saveDbToFile(listToSave);
                    }
                    break;

                default:
                    System.out.println("\nNie wybrano poprawnej liczby z MENU\n");
            }

            Messages.showEndingChooseMessage("aby wyświetlić listę wg innych kryteriów","głównego MENU:");

            userChoice = ConsoleInputProvider.readStringFromUserHandlingEmptyInput();
        }
        while (userChoice.toLowerCase().equals("t"));
    }

    private List<Song> sortSongsByVotes(List<Song> songs) {
        return songs.stream().sorted(Comparator.comparing(Song::getVote).reversed()).collect(Collectors.toList());
    }

    private List<Song> printSongsOnConsole(int numberOfRecordsToShow, List<Song> songs) {
        List<Song> sortedSongsList = sortSongsByVotes(songs);
        StringUtils.printHeading();
        List<Song> listToSave = new ArrayList<>();
        try {
            for (int i = 0; i < numberOfRecordsToShow; i++) {
                listToSave.add(sortedSongsList.get(i));
                Song tempSong = sortedSongsList.get(i);
                StringUtils.printSingleRecord(tempSong.getTitle(), tempSong.getAuthor(), tempSong.getAlbum(), tempSong.getCategory(), tempSong.getVote());
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\n-------------------------------------------> Brak większej ilości utworów do wyświetlenia z listy <--------------------------------------------\n");
        }
        StringUtils.printEnding();
        return listToSave;
    }

    private List<Song> createTopListByVotes(List<Song> songs, int numberOfPositionToShow) {
        List<Song> sortedSongsList = sortSongsByVotes(songs);
        List<Song> songListToShow = new ArrayList<>();
        int tempMaxVotes = sortedSongsList.get(0).getVote();
        for (int i = 0; i < numberOfPositionToShow; i++) {
            for (int j = 0; i < songs.size(); j++) {
                int votesToCompare = sortedSongsList.get(j).getVote();
                if (votesToCompare == tempMaxVotes) {
                    songListToShow.add(sortedSongsList.get(i));
                    Song tempSong = sortedSongsList.get(i);
                    StringUtils.printSingleRecord(tempSong.getTitle(), tempSong.getAuthor(), tempSong.getAlbum(), tempSong.getCategory(), tempSong.getVote());
                } else {
                    tempMaxVotes = sortedSongsList.get(j).getVote();
                    break;
                }
            }
        }

        return songListToShow;

    }


}

