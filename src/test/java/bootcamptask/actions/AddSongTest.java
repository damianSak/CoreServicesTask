package bootcamptask.actions;

import bootcamptask.model.Genre;
import bootcamptask.model.Song;
import bootcamptask.model.SongBuilder;
import bootcamptask.utils.stringhandlers.InputProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddSongTest {

    private static final String TITLE = "A";
    private static final String AUTHOR = "B";
    private static final String ALBUM = "C";
    private static final String GENRE = String.valueOf(Genre.Rock);

    private AddSong testObject;

    @Mock
    private InputProvider inputProvider;
    private List<Song> songList;

    @BeforeEach
    void initialize() {
        this.songList = new LinkedList<>();
        this.testObject = new AddSong(songList, inputProvider);
    }

    @Test
    void addNewSongToCollection_should_addNewSongToList_whenProperInputsGiven() {
        //given

        when(inputProvider.provideStringHandlingEmptyInputCustomErrorMessage(any(), any())).thenReturn(TITLE, AUTHOR, ALBUM, GENRE);
        when(inputProvider.provideStringHandlingEmptyInput()).thenReturn("n");

        //when
        testObject.addNewSongToCollection();

        //then
        assertThat(songList).hasSize(1);

        Song song = songList.get(0);
        assertThat(song.getTitle()).isEqualTo(TITLE);
        assertThat(song.getAuthor()).isEqualTo(AUTHOR);
        assertThat(song.getAlbum()).isEqualTo(ALBUM);
        assertThat(song.getCategory().toString()).isEqualTo(GENRE);
        assertThat(song.getVote()).isEqualTo(0);
    }

    @Test
    void addNewSongToCollection_should_notAddNewSongToList_when_WrongGenreGiven() {
        //given
        String wrongGenre = "Pop";
        when(inputProvider.provideStringHandlingEmptyInputCustomErrorMessage(any(), any())).thenReturn(TITLE, AUTHOR, ALBUM, wrongGenre);
        when(inputProvider.provideStringHandlingEmptyInput()).thenReturn("n");

        //when
        testObject.addNewSongToCollection();

        //then
        assertThat(songList).hasSize(0);
    }

    @Test
    void addNewSongToCollection_should_notAddNewSongToList_when_SongIsAlreadyOnList() {
        //given
        songList.add(SongBuilder.builder().title(TITLE).author(AUTHOR).album(ALBUM).category(Genre.valueOf(GENRE)).vote(0).build());

        when(inputProvider.provideStringHandlingEmptyInputCustomErrorMessage(any(), any())).thenReturn(TITLE, AUTHOR, ALBUM, GENRE);
        when(inputProvider.provideStringHandlingEmptyInput()).thenReturn("n");

        //when
        testObject.addNewSongToCollection();

        //then
        assertThat(songList).hasSize(1);
    }


}