package bootcamp_task.actions;

import bootcamp_task.model.Song;
import bootcamp_task.utils.ConsoleInputProvider;
import bootcamp_task.utils.Messages;
import bootcamp_task.utils.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

public class AddVoice {

    private List<Song> songs;

    public AddVoice(List<Song> songs) {
        this.songs = songs;
    }

    private boolean isSongAlreadyInCollectionValidation(List<Song> songs, String title, String author) {
        return songs.stream().anyMatch(h -> h.getTitle().equals(title) &&
                h.getAuthor().equals(author));
    }

    private List<Song> songsSubListBytitle (String title, List<Song>songs){
        return songs.stream().filter(h->h.getTitle().equals(title)).collect(Collectors.toList());
    }

    public void addVoicesToSong(List<Song> songs) {

        String title;
        String author;
        String userChoice;

        int votes;

        do {

            StringUtils.printSongsCollectionOnConsole(songs);

            title = StringUtils.readStringFromUserHandlingEmptyInput("Podaj tytuł piosenki, do której mają być dodane głosy:",
                    "Nie podano żadnego tytułu");

            if (songsSubListBytitle (title, songs).size() > 1) {
                System.out.println("W kolekcji znajduje się więcej niż jedna piosenka pod podanym tytułem");

                author = StringUtils.readStringFromUserHandlingEmptyInput("Podaj autora nagrania dla poszukiwanej piosenki ",
                        "Nie podano żadnej nazwy");

                if (isSongAlreadyInCollectionValidation(songs, title, author)) {

                    System.out.println("Podaj ile głosów ma być dodanych do wybranego tutyłu:");
                    votes = ConsoleInputProvider.readIntFromUserHandlingEmptyInput();
                    if (votes < 0) {

                        System.out.println("Głosy nie mogą być ujemne");
                    } else {
                        addVoiceBySongTileAndAuthor(songs, votes, title, author);
                        System.out.println("Dodano głosy do wybranej pozycji");
                    }
                }

            } else if (songsSubListBytitle (title, songs).size() == 1) {
                System.out.println("Podaj ile głosów ma być dodanych do wybranego tutyłu:");
                votes = ConsoleInputProvider.readIntFromUserHandlingEmptyInput();
                if (votes < 0) {
                    System.out.println("Głosy nie mogą być ujemne");
                } else {
                    addVoiceBySongTitle(songs, votes, title);
                    System.out.println("Dodano głosy do wybranej pozycji");
                }

            }else{
                System.out.println("Brak piosenki o podanym tytule lub błąd pisowni \n");
            }
            Messages.showEndingChooseMessage("dodać ponownie głosy dla piosenki","głównego MENU:");
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


