package bootcamp_task.utils.listhandler;

import bootcamp_task.model.Genre;
import bootcamp_task.model.Song;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ListHandlerTest {

    List<Song> testList = new ArrayList<>();

    @BeforeEach
    void initList(){
        this.testList = new ArrayList<>();
        Song song1 = new Song("A","A","A", Genre.Rock,10);
        Song song2 = new Song("B","B","B", Genre.Rock,2);
        Song song3 = new Song("C","C","C", Genre.Rock,300);
        Song song4 = new Song("A","D","D", Genre.Rock,40);
        testList.add(song1);
        testList.add(song2);
        testList.add(song3);
        testList.add(song4);
    }


    @Test
    void songsSubListByTitle_should_returnCorrectList_when_properTitleGiven() {

        //given
        List<Song> result;
        String title = "A";

        //when
        result=ListHandler.songsSubListByTitle(title, testList);

        //then
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getAuthor()).isEqualTo("A");
        assertThat(result.get(1).getAuthor()).isEqualTo("D");

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
}