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
class AddVoiceTest {

    private static final String TITLE = "A";
    private static final String AUTHOR = "B";
    private static final String ALBUM = "C";
    private static final String GENRE = String.valueOf(Genre.Rock);
    private static final int VOTES = 10;

    private AddVoice testObject;

    @Mock
    private InputProvider inputProvider;
    private List<Song> songList;

    @BeforeEach
    void initialize() {
        this.songList = new LinkedList<>();
        this.testObject = new AddVoice(songList, inputProvider);
    }

    @Test
    void addVoicesToSong_should_addVotesToSongInList_whenProperInputsGiven() {
        //given
        songList.add(SongBuilder.builder().title(TITLE).author(AUTHOR).album(ALBUM).category(Genre.valueOf(GENRE)).vote(0).build());
        when(inputProvider.provideStringHandlingEmptyInputCustomErrorMessage(any(), any())).thenReturn(TITLE, AUTHOR);
        when(inputProvider.provideIntHandlingEmptyInput()).thenReturn(VOTES);
        when(inputProvider.provideStringHandlingEmptyInput()).thenReturn("n");

        //when
        testObject.addVoicesToSong();

        //then
        assertThat(songList).hasSize(1);

        Song song = songList.get(0);
        assertThat(song.getTitle()).isEqualTo(TITLE);
        assertThat(song.getAuthor()).isEqualTo(AUTHOR);
        assertThat(song.getAlbum()).isEqualTo(ALBUM);
        assertThat(song.getCategory().toString()).isEqualTo(GENRE);
        assertThat(song.getVote()).isEqualTo(10);
    }

    @Test
    void addVoicesToSong_should_notAddVotesToSongInList_whenNegativVotesGiven() {
        //given
        int negativVotes = -10;
        songList.add(SongBuilder.builder().title(TITLE).author(AUTHOR).album(ALBUM).category(Genre.valueOf(GENRE)).vote(0).build());
        when(inputProvider.provideStringHandlingEmptyInputCustomErrorMessage(any(), any())).thenReturn(TITLE, AUTHOR);
        when(inputProvider.provideIntHandlingEmptyInput()).thenReturn(negativVotes);
        when(inputProvider.provideStringHandlingEmptyInput()).thenReturn("n");

        //when
        testObject.addVoicesToSong();

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
    void addVoicesToSong_should_addVotesToSongInList_whenWrongTitleGiven() {
        //given
        String wrongTitle = "Z";
        songList.add(SongBuilder.builder().title(TITLE).author(AUTHOR).album(ALBUM).category(Genre.valueOf(GENRE)).vote(0).build());
        when(inputProvider.provideStringHandlingEmptyInputCustomErrorMessage(any(), any())).thenReturn(wrongTitle);
        when(inputProvider.provideStringHandlingEmptyInput()).thenReturn("n");

        //when
        testObject.addVoicesToSong();

        //then
        assertThat(songList).hasSize(1);

        Song song = songList.get(0);
        assertThat(song.getTitle()).isEqualTo(TITLE);
        assertThat(song.getVote()).isEqualTo(0);
    }

    @Test
    void addVoicesToSong_should_addVotesToOneSongInList_when_listWithTwoTheSameSongsTitleGiven() {
        //given
        String secondAuthor = "X";
        songList.add(SongBuilder.builder().title(TITLE).author(AUTHOR).album(ALBUM).category(Genre.valueOf(GENRE)).vote(0).build());
        songList.add(SongBuilder.builder().title(TITLE).author(secondAuthor).album(ALBUM).category(Genre.valueOf(GENRE)).vote(0).build());
        when(inputProvider.provideStringHandlingEmptyInputCustomErrorMessage(any(), any())).thenReturn(TITLE, AUTHOR);
        when(inputProvider.provideIntHandlingEmptyInput()).thenReturn(VOTES);
        when(inputProvider.provideStringHandlingEmptyInput()).thenReturn("n");

        //when
        testObject.addVoicesToSong();

        //then
        assertThat(songList).hasSize(2);

        Song song1 = songList.get(0);
        assertThat(song1.getTitle()).isEqualTo(TITLE);
        assertThat(song1.getAuthor()).isEqualTo(AUTHOR);
        assertThat(song1.getAlbum()).isEqualTo(ALBUM);
        assertThat(song1.getCategory().toString()).isEqualTo(GENRE);
        assertThat(song1.getVote()).isEqualTo(10);

        Song song2 = songList.get(1);
        assertThat(song2.getTitle()).isEqualTo(TITLE);
        assertThat(song2.getAuthor()).isEqualTo(secondAuthor);
        assertThat(song2.getAlbum()).isEqualTo(ALBUM);
        assertThat(song2.getCategory().toString()).isEqualTo(GENRE);
        assertThat(song2.getVote()).isEqualTo(0);


    }
}