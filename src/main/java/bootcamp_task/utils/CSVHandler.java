package bootcamp_task.utils;

import bootcamp_task.model.Song;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CSVHandler {

    String file = "D:\\test\\CSVTest.txt";

    public static List<Song> readCSVFromList(File fileName) {

        List<Song> songsSet = new ArrayList<>();

        try {
            songsSet = new CsvToBeanBuilder(new FileReader(fileName))
                    .withType(Song.class).build().parse();

            System.out.println("\n Pomyślnie wczytano elementy z pliku\n");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return songsSet;
    }

    static void saveListToCSVFile(List<Song> songsToSave, File createdFile) {

            try (
                    Writer writer = Files.newBufferedWriter(Paths.get(createdFile.getPath()))
            ) {

                StatefulBeanToCsv<Song> csvWriter = new StatefulBeanToCsvBuilder(writer)
                        .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                        .build();


                csvWriter.write(songsToSave);
            }

        catch (Exception e) {
            e.printStackTrace();
        }


    }
}
