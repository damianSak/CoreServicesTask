package bootcamptask.actions;

import bootcamptask.model.Song;
import bootcamptask.utils.listhandler.ListHandler;
import bootcamptask.utils.stringhandlers.*;
import bootcamptask.utils.listhandler.validators.SongValidator;

import java.util.List;

public class AddVoice {

    private List<Song> songs;
    private InputProvider inputProvider;

    public AddVoice(List<Song> songs, InputProvider inputProvider) {
        this.songs = songs;
        this.inputProvider = inputProvider;
    }

    public void addVoicesToSong() {
        String title;
        String author;
        String userChoice;
        int votes;
        if (songs.size() > 0)
            do {
                PrintUtils.printSongsCollectionOnConsole(songs);
                title = inputProvider.provideStringHandlingEmptyInputCustomErrorMessage("Podaj tytuł utworu, do " +
                                "której mają być dodane głosy:",
                        "Nie podano żadnego tytułu");
                if (ListHandler.songsSubListByTitle(title, songs).size() > 1) {
                    System.out.println("\nW kolekcji znajduje się więcej niż jeden utwór pod podanym tytułem");
                    author = inputProvider.provideStringHandlingEmptyInputCustomErrorMessage("Podaj autora nagrania dla " +
                                    "poszukiwanego utworu",
                            "Nie podano żadnego autora");
                    if (SongValidator.isSongAlreadyInCollectionValidation(songs, title, author)) {
                        System.out.println("Podaj ile głosów ma być dodanych do wybranego tutyłu:");
                        votes = inputProvider.provideIntHandlingEmptyInput();
                        if (votes < 0) {
                            System.out.println("Głosy nie mogą być ujemne");
                        } else {
                            addVoiceBySongTileAndAuthor(songs, votes, title, author);
                            System.out.println("Dodano głosy do wybranej pozycji");
                        }
                    }
                } else if (ListHandler.songsSubListByTitle(title, songs).size() == 1) {
                    System.out.println("Podaj ile głosów ma być dodanych do wybranego tutyłu:");
                    votes = inputProvider.provideIntHandlingEmptyInput();
                    if (votes < 0) {
                        System.out.println("Głosy nie mogą być ujemne");
                    } else {
                        addVoiceBySongTitle(songs, votes, title);
                        System.out.println("Dodano głosy do wybranej pozycji");
                    }
                } else {
                    System.out.println("\nBrak utowru o podanym tytule lub błąd pisowni");
                }
                Messages.showUserChooseMessage("DODAĆ PONOWNIE głosy dla innego utworu",
                        "wrócić do głównego MENU:");
                userChoice = inputProvider.provideStringHandlingEmptyInput();
            }
            while (userChoice.toLowerCase().equals("t"));
        else {
            Messages.showNoElementsInCollectionMessage("do wyświetlenia");
            ConsoleInputProvider.waitForPressedEnter();
        }
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


