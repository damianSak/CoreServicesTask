package bootcamptask.actions;

import bootcamptask.model.Genre;
import bootcamptask.model.Song;
import bootcamptask.model.SongBuilder;
import bootcamptask.utils.listhandler.validators.SongValidator;
import bootcamptask.utils.parsers.GenreUtils;
import bootcamptask.utils.stringhandlers.*;

import java.util.List;

public class AddSong {

    private List<Song> songs;
    private InputProvider inputProvider;

    public AddSong(List<Song> songs, InputProvider inputProvider) {
        this.songs = songs;
        this.inputProvider = inputProvider;
    }

    private void addNewSongToCollection(List<Song> songs, String title, String author, String album, Genre category) {
        songs.add(SongBuilder.builder().title(title).author(author).album(album).category(category).vote(0).build());
    }

    public void addNewSongToCollection() {
        String title;
        String author;
        String album;
        String categoryString;
        String userChoice;
        do {
            title = inputProvider.provideStringHandlingEmptyInputCustomErrorMessage("Podaj tytuł utworu:",
                    "Nie podano żadnego tytułu");
            author = inputProvider.provideStringHandlingEmptyInputCustomErrorMessage("Podaj wykonawcę nagrania: ",
                    "Nie podano żadnego autora");
            if (!SongValidator.isSongAlreadyInCollectionValidation(songs, title, author)) {
                album = inputProvider.provideStringHandlingEmptyInputCustomErrorMessage("Podaj nazwę płyty: ",
                        "Nie podano żadnej nazwy");
                System.out.println("Dostępne gatunki muzyczne:\n");
                for (Genre genre : Genre.values()) {
                    System.out.println(genre);
                }
                categoryString = inputProvider.provideStringHandlingEmptyInputCustomErrorMessage("\nPodaj gatunek muzyczny:",
                        "Nie podano żadnego gatunku");
                Genre genreFromString = GenreUtils.findByNameCaseInsensitive(categoryString);
                if (genreFromString == null) {
                    System.out.println(" \nBrak kategorii muzycznej dla gatunku '" + categoryString +
                            "', podany utwór nie zostanie dodany do listy \n");
                } else {
                    addNewSongToCollection(songs, title, author, album, genreFromString);
                    System.out.println("Dodano piosenkę do kolekcji: ");
                    PrintUtils.printHeading();
                    PrintUtils.printSingleRecord(title, author, album, genreFromString, 0);
                    PrintUtils.printEnding();
                }
            } else {
                System.out.println("\nWprowadzona piosenka z podanym wykonawcą już istnieje na tej liście \n");
            }
            Messages.showUserChooseMessage("DODAĆ KOLEJNĄ nową pozycję", "wrócić do głównego MENU:");
            userChoice = inputProvider.provideStringHandlingEmptyInput();
        }
        while (userChoice.toLowerCase().equals("t"));
    }

}
