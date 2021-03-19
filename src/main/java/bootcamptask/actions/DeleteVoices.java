package bootcamptask.actions;

import bootcamptask.model.Song;
import bootcamptask.utils.listhandler.ListHandler;
import bootcamptask.utils.listhandler.validators.SongValidator;
import bootcamptask.utils.stringhandlers.*;

import java.util.List;

public class DeleteVoices {

    private List<Song> songs;
    private InputProvider inputProvider;

    public DeleteVoices(List<Song> songs, InputProvider inputProvider) {
        this.songs = songs;
        this.inputProvider = inputProvider;
    }

    public void deleteVoicesFromSongs() {
        if (songs.size() > 0) {
            String title;
            String author;
            int userChoiceFromMenu;
            String userChoice;
            String userDecision;
            do {
                PrintUtils.printSongsCollectionOnConsole(songs);
                System.out.println("-------------------------------------");
                System.out.println("1. Usuń głosy jednej piosence");
                System.out.println("2. Usuń głosy wszystkim piosenkom");
                System.out.println("-------------------------------------");
                System.out.println("Podaj swój wybór:");

                userChoiceFromMenu = inputProvider.provideIntHandlingEmptyInput();

                switch (userChoiceFromMenu) {
                    case 1:
                        title = inputProvider.provideStringHandlingEmptyInputCustomErrorMessage("Podaj tytuł piosenki:",
                                "Nie podano żadnego tytułu");
                        if (ListHandler.songsSubListByTitle(title, songs).size() > 1) {
                            System.out.println("W kolekcji znajduje się więcej niż jedna piosenka pod podanym tytułem");
                            author = inputProvider.provideStringHandlingEmptyInputCustomErrorMessage("Podaj autora " +
                                            "nagrania dla poszukiwanej piosenki ",
                                    "Nie podano żadnej nazwy");
                            if (SongValidator.isSongAlreadyInCollectionValidation(songs, title, author)) {
                                Messages.showUserChooseMessage("DEFINITYWNIE usunąć głosy",
                                        "cofnąć wybór i wrócić do poprzedniego MENU:");
                                userDecision = inputProvider.provideStringHandlingEmptyInput();
                                if (userDecision.toLowerCase().equals("t")) {
                                    deleteVoicesByTitleAndAuthor(songs, title, author);
                                    System.out.println("Usunięto wszystkie głosy dla wybranej piosenki");
                                }
                            } else {
                                System.out.println("Dla podanego wykonawcy brak utowru o takim tytule lub " +
                                        "bład pisowni przy wprowadzaniu danych");
                            }
                        } else if (ListHandler.songsSubListByTitle(title, songs).size() == 1) {
                            Messages.showUserChooseMessage("DEFINITYWNIE usunąć głosy",
                                    "cofnąć wybór i wrócić do poprzedniego MENU:");
                            userDecision = inputProvider.provideStringHandlingEmptyInput();
                            if (userDecision.toLowerCase().equals("t")) {
                                deleteVoicesByTitle(songs, title);
                                System.out.println("Usunięto wszystkie głosy dla wybranego utworu");
                            }
                        }
                        break;
                    case 2:
                        Messages.showUserChooseMessage("DEFINITYWNIE usunąć głosy",
                                "cofnąć wybór i wrócić do poprzedniego MENU:");
                        userDecision = inputProvider.provideStringHandlingEmptyInput();
                        if (userDecision.toLowerCase().equals("t")) {
                            deleteAllVotesFromCollection(songs);
                            System.out.println("Usunięto wszystkie głosy dla utworów znajdujących się na liście");
                        }
                        break;

                    default:
                        System.out.println("W kolekcji nie odnaleziono utworów o podanym tytule");
                        break;
                }
                Messages.showUserChooseMessage("USUNĄĆ GŁOSY innemu utworowi, wszystkim utworom",
                        "wrócić do głównego MENU:");
                userChoice = inputProvider.provideStringHandlingEmptyInput();
            }
            while (userChoice.toLowerCase().equals("t"));
        } else {
            Messages.showNoElementsInCollectionMessage(",którym można usunąć głosy");
            ConsoleInputProvider.waitForPressedEnter();
        }
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
