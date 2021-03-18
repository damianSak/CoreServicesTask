package bootcamp_task.actions;

import bootcamp_task.model.Song;
import bootcamp_task.utils.listhandler.ListHandler;
import bootcamp_task.utils.stringhandlers.ConsoleInputProvider;
import bootcamp_task.utils.stringhandlers.Messages;
import bootcamp_task.utils.stringhandlers.PrintUtils;
import bootcamp_task.utils.stringhandlers.StringUtils;
import bootcamp_task.utils.validators.SongValidator;

import java.util.List;

public class AddVoice {

    private List<Song> songs;

    public AddVoice(List<Song> songs) {
        this.songs = songs;
    }

    public void addVoicesToSong(List<Song> songs) {

        String title;
        String author;
        String userChoice;

        int votes;

        do {
            PrintUtils.printSongsCollectionOnConsole(songs);

            title = StringUtils.readStringFromUserHandlingEmptyInput("Podaj tytuł utworu, do " +
                            "której mają być dodane głosy:",
                    "Nie podano żadnego tytułu");

            if (ListHandler.songsSubListByTitle(title, songs).size() > 1) {
                System.out.println("W kolekcji znajduje się więcej niż jeden utwór pod podanym tytułem");

                author = StringUtils.readStringFromUserHandlingEmptyInput("Podaj autora nagrania dla " +
                                "poszukiwanego utworu",
                        "Nie podano żadnego autora");

                if (SongValidator.isSongAlreadyInCollectionValidation(songs, title, author)) {

                    System.out.println("Podaj ile głosów ma być dodanych do wybranego tutyłu:");
                    votes = ConsoleInputProvider.readIntFromUserHandlingEmptyInput();

                    if (votes < 0) {
                        System.out.println("Głosy nie mogą być ujemne");
                    } else {
                        addVoiceBySongTileAndAuthor(songs, votes, title, author);
                        System.out.println("Dodano głosy do wybranej pozycji");
                    }
                }
            } else if (ListHandler.songsSubListByTitle(title, songs).size() == 1) {

                System.out.println("Podaj ile głosów ma być dodanych do wybranego tutyłu:");
                votes = ConsoleInputProvider.readIntFromUserHandlingEmptyInput();

                if (votes < 0) {
                    System.out.println("Głosy nie mogą być ujemne");
                } else {
                    addVoiceBySongTitle(songs, votes, title);
                    System.out.println("Dodano głosy do wybranej pozycji");
                }
            } else {
                System.out.println("\nBrak utowru o podanym tytule lub błąd pisowni");
            }
            Messages.showEndingChooseMessage("dodać ponownie głosy dla innego utworu", "głównego MENU:");
            userChoice = ConsoleInputProvider.readStringFromUserHandlingEmptyInput();
        }
        while (userChoice.toLowerCase().equals("t"));
    }

    private void addVoiceBySongTitle(List<Song> songs, int votes, String title) {
        for (Song song : songs) {
            if (song.getTitle().equals(title)) {
                int tempVotes = song.getVote();
                song.setVote(tempVotes + votes);
            }
        }
    }

    private void addVoiceBySongTileAndAuthor(List<Song> songs, int votes, String title, String author) {
        for (Song song : songs) {
            if (song.getTitle().equals(title) && song.getAuthor().equals(author)) {
                int tempVotes = song.getVote();
                song.setVote(tempVotes + votes);
            }
        }
    }



}


