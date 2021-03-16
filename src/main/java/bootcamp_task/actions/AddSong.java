package bootcamp_task.actions;

import bootcamp_task.model.Genre;
import bootcamp_task.model.Song;
import bootcamp_task.utils.ConsoleInputProvider;
import bootcamp_task.utils.Messages;
import bootcamp_task.utils.StringUtils;

import java.util.List;

public class AddSong {

    private List<Song> songs;

    public AddSong(List<Song> songs) {
        this.songs = songs;
    }

    private boolean isSongAlreadyInCollectionValidation(List<Song> songs, String title, String author) {
        return songs.stream().anyMatch(h -> h.getTitle().equals(title) &&
                h.getAuthor().equals(author));
    }

    private void addSongToCollection(List<Song> songs, String title, String author, String album, Genre category) {
        songs.add(new Song(title, author, album, category, 0));
    }

    public void addSongToCollection(List<Song> songs) {

        String title;
        String author;
        String album;
        String categoryString;

        String userChoice;

        do {
            title = StringUtils.readStringFromUserHandlingEmptyInput("Podaj tytuł piosenki:",
                    "Nie podano żadnego tytułu");

            author = StringUtils.readStringFromUserHandlingEmptyInput("Podaj autora nagrania: ",
                    "Nie podano żadnego autora");

            if (!isSongAlreadyInCollectionValidation(songs, title, author)) {
                album = StringUtils.readStringFromUserHandlingEmptyInput("Podaj nazwę płyty: ",
                        "Nie podano żadnej nazwy");

                categoryString = StringUtils.readStringFromUserHandlingEmptyInput("Podaj gatunek muzyczny: ",
                        "Nie podano żadnego gatunku");

                Genre genreFromString = Genre.findByNameNoCaseSensvity(categoryString);

                if (genreFromString == null) {
                    System.out.println(" \nBrak kategorii muzycznej dla gatunku '" + categoryString + "', podany utwór nie zostanie dodany do listy \n");
                } else {
                    addSongToCollection(songs, title, author, album, genreFromString);
                    System.out.println("Dodano piosenkę do kolekcji: ");
                    StringUtils.printHeading();
                    StringUtils.printSingleRecord(title, author, album, genreFromString, 0);
                    StringUtils.printEnding();
                }

            } else {
                System.out.println("\nWprowadzona piosenka z podanym wykonawcą już istnieje na tej liście \n");
            }
            Messages.showEndingChooseMessage("dodać kolejną nową pozycję");
            userChoice = ConsoleInputProvider.readStringFromUserHandlingEmptyInput();
        }
        while (userChoice.toLowerCase().equals("t"));
    }

}
