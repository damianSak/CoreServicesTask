package bootcamptask.utils.stringhandlers;

public class Messages {

    public static void showUserChooseMessage(String specificAction, String customMessage) {
        System.out.println("\nWprowadź 'T/t' aby " + specificAction + " lub 'N/n' i wciśnij ENTER aby "
                +customMessage);
    }

    public static void showNoElementsInCollectionMessage(String customMessage){
        System.out.println("\nBrak elementów w pamięci "+customMessage+", wciśnij ENTER aby powrócić do głównego MENU:");
    }

}
