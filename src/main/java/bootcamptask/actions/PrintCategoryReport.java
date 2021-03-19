package bootcamptask.actions;

import bootcamptask.model.Genre;
import bootcamptask.model.Song;
import bootcamptask.utils.filewriters.SaveReport;
import bootcamptask.utils.listhandler.ListHandler;
import bootcamptask.utils.listhandler.validators.SongValidator;
import bootcamptask.utils.stringhandlers.ConsoleInputProvider;
import bootcamptask.utils.stringhandlers.InputProvider;
import bootcamptask.utils.stringhandlers.Messages;
import bootcamptask.utils.stringhandlers.PrintUtils;

import java.util.List;

public class PrintCategoryReport {

    private List<Song> songs;
    private InputProvider inputProvider;

    public PrintCategoryReport(List<Song> songs, InputProvider inputProvider) {
        this.songs = songs;
        this.inputProvider = inputProvider;
    }

    public void printReportByCategory() {
        if(songs.size()>0) {
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
                userChoiceFromMenu = inputProvider.provideStringHandlingEmptyInput();
                if (SongValidator.isGenreInCollectionValidation(songs, userChoiceFromMenu)) {
                    listToSave = ListHandler.sortSongsByVotesAndGenre(songs, userChoiceFromMenu);
                    PrintUtils.printSongsOnConsole(listToSave, listToSave.size());
                    Messages.showUserChooseMessage("zapisać listę do pliku", "poprzedniegoo MENU:");
                    userChoice = inputProvider.provideStringHandlingEmptyInput();
                    if (userChoice.toLowerCase().equals("t")) {
                        SaveReport.saveDbToFile(listToSave, inputProvider);
                    }
                } else {
                    System.out.println("\nNie wybrano właściwej nazwy z listy gatunków lub brak pozycji dla tego gatunku");
                }
                Messages.showUserChooseMessage("aby WYŚWIETLIĆ listę utworów dla innej kategori",
                        "wrócić do głównego MENU:");
                userChoice = inputProvider.provideStringHandlingEmptyInput();
            }
            while (userChoice.toLowerCase().equals("t"));
        }else{
            Messages.showNoElementsInCollectionMessage("do wyświetlenia");
            ConsoleInputProvider.waitForPressedEnter();
        }
    }
}
