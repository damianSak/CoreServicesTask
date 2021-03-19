package bootcamptask.utils.listhandler;

import bootcamptask.model.Genre;
import bootcamptask.model.Song;
import bootcamptask.model.SongBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ListHandlerTest {

    private List<Song> testList = new ArrayList<>();

    @BeforeEach
    void initList(){
        this.testList = new ArrayList<>();
        Song song1 = SongBuilder.builder().title("A").author("A").album("A").category(Genre.Rock).vote(10).build();
        Song song2 = SongBuilder.builder().title("B").author("B").album("B").category(Genre.Rock).vote(2).build();
        Song song3 = SongBuilder.builder().title("C").author("C").album("C").category(Genre.Rock).vote(300).build();
        Song song4 = SongBuilder.builder().title("D").author("D").album("D").category(Genre.Rock).vote(40).build();
        testList.add(song1);
        testList.add(song2);
        testList.add(song3);
        testList.add(song4);
    }


    @Test
    void songsSubListByTitle_should_returnCorrectList_when_properTitleGiven() {
        //given
        String title = "A";

        //when
        List<Song> result=ListHandler.songsSubListByTitle(title, testList);

        //then
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getAuthor()).isEqualTo("A");

    }

    @Test
    void songsSubListByTitle_should_returnEmptyList_when_wrongTitleGiven() {

        //given
        List<Song> result;
        String title = "Z";

        //when
        result=ListHandler.songsSubListByTitle(title, testList);

        //then
        assertThat(result).hasSize(0);

    }

    @Test
    void songsSubListByTitle_should_returnEmptyList_when_properTitleAndEmptyListGiven() {

        //given
        List<Song> emptyList = new ArrayList<>();
        List<Song> result;
        String title = "A";

        //when
        result=ListHandler.songsSubListByTitle(title, emptyList);

        //then
        assertThat(result).hasSize(0);

    }

    @Test
    void sortSongsByVotes_should_returnCorrectList_when_properListGiven() {
        //given
        List<Song> result;

        //when
        result=ListHandler.sortSongsByVotes(testList);

        //then
        assertThat(result).hasSize(4);
        assertThat(result.get(0).getAuthor()).isEqualTo("C");
        assertThat(result.get(0).getVote()).isEqualTo(300);
        assertThat(result.get(3).getAuthor()).isEqualTo("B");
        assertThat(result.get(3).getVote()).isEqualTo(2);
    }


    @Test
    void sortSongsByVotes_should_returnEmptyList_when_emptyListGiven() {
        //given
        List<Song> emptyList = new ArrayList<>();
        List<Song> result;

        //when
        result=ListHandler.sortSongsByVotes(emptyList);

        //then
        assertThat(result).hasSize(0);
    }

    @Test
    void sortSongsByVotesAndGenre_should_returnProperList_when_properGenreAndProperListGiven() {
        //given
        String searchedGenre = "Rock";
        List<Song> result;

        //when
        result=ListHandler.sortSongsByVotesAndGenre(testList,searchedGenre);

        //then
        assertThat(result.size()).isEqualTo(4);
        assertThat(result.get(0).getVote()).isEqualTo(300);
        assertThat(result.get(0).getCategory()).isEqualTo(Genre.Rock);
        assertThat(result.get(2).getVote()).isEqualTo(10);
    }

    @Test
    void sortSongsByVotesAndGenre_shouldNot_returnProperList_when_properGenreAndEmptyListGiven() {
        //given
        String searchedGenre = "Rock";
        List<Song> emptyList = new ArrayList<>();
        List<Song> result;

        //when
        result=ListHandler.sortSongsByVotesAndGenre(emptyList,searchedGenre);

        //then
        assertThat(result.size()).isEqualTo(0);

    }

    @Test
    void sortSongsByVotesAndGenre_shouldNot_returnProperList_when_wrongGenreAndProperListGiven() {
        //given
        String searchedGenre = "Pop";
        List<Song> result;

        //when
        result=ListHandler.sortSongsByVotesAndGenre(testList,searchedGenre);

        //then
        assertThat(result.size()).isEqualTo(0);

    }
    @Test
    void sortSongsByVotesAndGenre_shouldNot_returnProperList_when_wrongGenreAndEmptyListGiven() {
        //given
        String searchedGenre = "Pop";
        List<Song> emptyList = new ArrayList<>();
        List<Song> result;

        //when
        result=ListHandler.sortSongsByVotesAndGenre(emptyList,searchedGenre);

        //then
        assertThat(result.size()).isEqualTo(0);

    }
}