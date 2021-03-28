package bootcamptask.utils.parsers;

import bootcamptask.model.Song;
import bootcamptask.utils.CustomBeanToCSVMappingStrategy;
import com.opencsv.CSVWriter;
import com.opencsv.bean.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVHandler {

    public static List<Song> readCSVFromList(File fileName) {

        final CsvToBean<Song> beans;
        List songsSet = new ArrayList<>();

        try {
            HeaderColumnNameMappingStrategy ms = new HeaderColumnNameMappingStrategy();
            ms.setType(Song.class);

            beans = new CsvToBeanBuilder(new FileReader(fileName)).withThrowExceptions(false)
                    .withType(Song.class).withMappingStrategy(ms).build();

            songsSet = beans.parse();

            System.out.println("Wczytano utwory:");
            songsSet.forEach((Song) -> System.out.println(Song.toString()));

            if(beans.getCapturedExceptions().size()>0) {

                System.out.println("\nDo pamięci nie wczytano danych utworu z powodu " +
                        "braku danych dla co najmniej jednego pola lub niewłaściwego gatunku muzycznego:");
                beans.getCapturedExceptions().forEach((exception) -> System.out.println("Linia : " + exception.getLineNumber()
                        + " ,obecne wartości: " + Arrays.toString(exception.getLine())));
            }
            System.out.println("\nPomyślnie wczytano elementy z pliku do pamięci programu");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return songsSet;
    }

    public static void saveListToCSVFile(List<Song> songsToSave, File createdFile) {

        try (Writer writer = Files.newBufferedWriter(Paths.get(createdFile.getPath()))) {
            CustomBeanToCSVMappingStrategy<Song> mappingStrategy = new CustomBeanToCSVMappingStrategy<>();
            mappingStrategy.setType(Song.class);

            StatefulBeanToCsv<Song> csvWriter = new StatefulBeanToCsvBuilder<Song>(writer).withMappingStrategy(mappingStrategy)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .build();

            csvWriter.write(songsToSave);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
