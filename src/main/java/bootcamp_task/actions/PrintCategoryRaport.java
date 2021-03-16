package bootcamp_task.actions;

import bootcamp_task.model.Genre;
import bootcamp_task.model.Song;
import bootcamp_task.utils.ConsoleInputProvider;
import bootcamp_task.utils.Messages;
import bootcamp_task.utils.SaveRaport;
import bootcamp_task.utils.StringUtils;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PrintCategoryRaport {

    private List<Song> songs;

    public PrintCategoryRaport(List<Song> songs) {
        this.songs = songs;
    }

    public void printRaportByCategory(List<Song> songs) {

        String userChoiceFromMenu;
        String userChoice;
        List<Song> listToSave;

        do {
            int numberList = 1;

            System.out.println("-------------------------------------");

            for (Genre genre : Genre.values()) {
                System.out.println(numberList++ + ". " + genre);
            }
            System.out.println("-------------------------------------");
            System.out.println("Wpisz nazwę gatunku, dla którego utwory mają być wyświetlone:");

            userChoiceFromMenu = ConsoleInputProvider.readStringFromUserHandlingEmptyInput();

           if(isGenreInCollectionValidation(songs,userChoiceFromMenu)){

               listToSave=sortSongsByVotesAndGenre(songs,userChoiceFromMenu);
               StringUtils.printSongsCollectionOnConsole(listToSave);
               System.out.println("\n Wprowadź 'T/t' jeśli chcesz zapisać listę do pliku");
               userChoice = ConsoleInputProvider.readStringFromUserHandlingEmptyInput();
               if(userChoice.toLowerCase().equals("t")){
                   SaveRaport.saveDbToFile(listToSave);
               }

           }else{
               System.out.println("Nie wybrano właściwej nazwy z listy gatunków");
           }

            Messages.showEndingChooseMessage("aby wyświetlić listę utworów dla innej kategori","głównego MENU:");

            userChoice = ConsoleInputProvider.readStringFromUserHandlingEmptyInput();
        }
        while (userChoice.toLowerCase().equals("t"));
    }

    private boolean isGenreInCollectionValidation(List<Song>songs, String genreName) {
        return songs.stream().anyMatch(h -> h.getCategory().equals(Genre.findByName(genreName)));
    }

    private List<Song> sortSongsByVotesAndGenre(List<Song> songs,String genreName) {
        return songs.stream().filter(h -> h.getCategory().equals(Genre.findByName(genreName)))
                .sorted(Comparator.comparing(Song::getVote).reversed()).collect(Collectors.toList());
    }
}
