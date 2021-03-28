package bootcamptask.actions;

import bootcamptask.SongsCollection;
import bootcamptask.model.Song;
import bootcamptask.utils.stringhandlers.InputProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoadSongsListTest {

    private LoadSongsList testObject;

    @Mock
    private InputProvider inputProvider;
    private List<Song> songList;

    @BeforeEach
    void initialize() {
        this.songList = new LinkedList<>();
        this.testObject = new LoadSongsList(songList, inputProvider);
        SongsCollection.ORIGIN_PATH = "sharedResources";
    }

    @Test
    void loadListFromFile_should_loadSongsFromFile_when_properCSVFileGiven() {
        //given

        when(inputProvider.provideStringHandlingEmptyInput()).thenReturn("CSV", "goodCSVTestFile","n");

        //when
        songList = testObject.loadListFromFile();

        //then
        assertThat(songList).hasSize(3);

    }

    @Test
    void loadListFromFile_should_notLoadAllSongsFromFile_when_wrongSongInCSVFileGiven() {
        //given

        when(inputProvider.provideStringHandlingEmptyInput()).thenReturn("CSV", "badCSVTestFileNoField","n");

        //when
        songList = testObject.loadListFromFile();

        //then
        assertThat(songList).hasSize(2);

    }

    @Test
    void loadListFromFile_should_loadSongsFromFile_when_properXMLFileGiven() {
        //given

        when(inputProvider.provideStringHandlingEmptyInput()).thenReturn("XML", "goodXMLTestFile","n");


        //when
        songList = testObject.loadListFromFile();

        //then
        assertThat(songList).hasSize(3);

    }

    @Test
    void loadListFromFile_should_notLoadSongsFromFile_when_wrongSongInXMLFileGiven() {
        //given
        when(inputProvider.provideStringHandlingEmptyInput()).thenReturn("XML", "badXMLTestFileNoField","n");

        //when
        songList = testObject.loadListFromFile();

        //then
        assertThat(songList).hasSize(0);
    }
    @Test
    void loadListFromFile_should_notAddMultiplySongsFromFile_when_goodFileTwoTimesGiven() {
        //given
        when(inputProvider.provideStringHandlingEmptyInput()).thenReturn("XML", "goodXMLTestFile","t","XML", "goodXMLTestFile","n");

        //when
        songList = testObject.loadListFromFile();

        //then
        assertThat(songList).hasSize(3);
    }
}