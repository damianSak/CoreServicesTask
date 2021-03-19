package bootcamptask.utils.stringhandlers;

public interface InputProvider {

    String provideStringHandlingEmptyInput();

    String provideStringHandlingEmptyInputCustomErrorMessage(String mainMessage, String exceptionMessage);

    int provideIntHandlingEmptyInput();
}
