package bootcamp_task.actions;

import bootcamp_task.model.Genre;
import bootcamp_task.model.Song;
import bootcamp_task.utils.ConsoleInputProvider;
import bootcamp_task.utils.Messages;
import bootcamp_task.utils.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PrintCategoryRaport {

    private List<Song> songs;

    public PrintCategoryRaport(List<Song> songs) {
        this.songs = songs;
    }

    public void printRaportByCategory(List<Song> songs) {

        String userChoiceFromMenu;
        String userChoice;
        Set<Genre> genreSet = createGenreSet(songs);

        do {
            int numberList = 1;

            System.out.println("-------------------------------------");

            for (Genre genre : genreSet) {
                System.out.println(numberList++ + ". " + genre);
            }
            System.out.println("-------------------------------------");
            System.out.println("Wpisz nazwę gatunku, dla którego utwory mają być wyświetlone:");

            userChoiceFromMenu = ConsoleInputProvider.readStringFromUserHandlingEmptyInput();
           if(isGenreInCollectionValidation(genreSet,userChoiceFromMenu)){

               printSongsOnConsole(Genre.valueOf(userChoiceFromMenu),songs);

           }else{
               System.out.println("Nie wybrano właściwej nazwy z listy gatunków");
           }

            Messages.showEndingChooseMessage("aby wyświetlić listę utworów dla innej kategori");

            userChoice = ConsoleInputProvider.readStringFromUserHandlingEmptyInput();
        }
        while (userChoice.toLowerCase().equals("t"));
    }

    private void printSongsOnConsole(Genre genreToShow, List<Song> songs) {
        StringUtils.printHeading();
        for (Song song : songs) {
            if (song.getCategory()..equals(genreToShow))
                StringUtils.printSingleRecord(song.getTitle(), song.getAuthor(), song.getAlbum(), song.getCategory(), song.getVote());
        }
        StringUtils.printEnding();
    }

    private Set<Genre> createGenreSet(List<Song> songs) {
        Set<Genre> genreSet = new HashSet<>();
        for (Song song : songs) {
            genreSet.add(song.getCategory());
        }
        return genreSet;
    }

    private boolean isGenreInCollectionValidation(Set<Genre> genreSet, String genreName) {
        return genreSet.stream().anyMatch(h -> h.equals(genreName));
    }
}
