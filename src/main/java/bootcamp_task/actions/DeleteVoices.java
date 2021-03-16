package bootcamp_task.actions;

import bootcamp_task.model.Song;
import bootcamp_task.utils.ConsoleInputProvider;
import bootcamp_task.utils.Messages;
import bootcamp_task.utils.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

public class DeleteVoices {

    private List<Song> songs;

    public DeleteVoices(List<Song> songs) {
        this.songs = songs;
    }

    private boolean isSongAlreadyInCollectionValidation(List<Song> songs, String title, String author) {
        return songs.stream().anyMatch(h -> h.getTitle().equals(title) &&
                h.getAuthor().equals(author));
    }

    private List<Song> songsSubListBytitle(String title, List<Song> songs) {
        return songs.stream().filter(h -> h.getTitle().equals(title)).collect(Collectors.toList());
    }

    public void deleteVoicesFromSongs(List<Song> songs) {

        String title;
        String author;

        int userChoiceFromMenu;
        String userChoice;

        do {

            StringUtils.printSongsCollectionOnConsole(songs);

            System.out.println("-------------------------------------");
            System.out.println("1. Usuń głosy jednej piosence");
            System.out.println("2. Usuń głosy wszystkim piosenkom");
            System.out.println("-------------------------------------");
            System.out.println("Podaj swój wybór:");

            userChoiceFromMenu = ConsoleInputProvider.readIntFromUserHandlingEmptyInput();

            switch (userChoiceFromMenu) {

                case 1:
                    title = StringUtils.readStringFromUserHandlingEmptyInput("Podaj tytuł piosenki:",
                            "Nie podano żadnego tytułu");

                    if (songsSubListBytitle(title, songs).size() > 1) {
                        System.out.println("W kolekcji znajduje się więcej niż jedna piosenka pod podanym tytułem");

                        author = StringUtils.readStringFromUserHandlingEmptyInput("Podaj autora nagrania dla poszukiwanej piosenki ",
                                "Nie podano żadnej nazwy");

                        if (isSongAlreadyInCollectionValidation(songs, title, author)) {
                            deleteVoicesByTitleAndAuthor(songs, title, author);
                            System.out.println("Usunięto wszystkie głosy dla wybranej piosenki");

                        } else {
                            System.out.println("Dla podanego wykonawcy brak utowru o takim tytule lub bład pisowni przy wprowadzaniu danych");
                        }

                    } else if (songsSubListBytitle(title, songs).size() == 1) {
                        deleteVoicesByTitle(songs, title);
                        System.out.println("Usunięto wszystkie głosy dla wybranego utworu");
                    }
                    break;

                case 2:
                    deleteAllVotesFromCollection(songs);
                    System.out.println("Usunięto wszystkie głosy dla utworów znajdujących się na liście");
                    break;

                default:
                    System.out.println("W kolekcji nie odnaleziono piosenki o podanym tytule");
                    break;

            }

            Messages.showEndingChooseMessage("usunąć głosy innemu utworowi, wszystkim utworom","głównego MENU:");

            userChoice = ConsoleInputProvider.readStringFromUserHandlingEmptyInput();
        }
        while (userChoice.toLowerCase().equals("t"));
    }

    private void deleteVoicesByTitle(List<Song> songs, String title) {
        for (Song song : songs) {
            if (song.getTitle().equals(title)) {
                song.setVote(0);
            }
        }
    }

    private void deleteVoicesByTitleAndAuthor(List<Song> songs, String title, String author) {
        for (Song song : songs) {
            if (song.getTitle().equals(title) && song.getAuthor().equals(author)) {
                song.setVote(0);
            }
        }
    }

    private void deleteAllVotesFromCollection(List<Song> songs) {
        for (Song song : songs) {
            song.setVote(0);
        }
    }

}
