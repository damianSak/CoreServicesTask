package bootcamptask.utils.listhandler.validators;

import bootcamptask.model.Genre;
import bootcamptask.model.Song;
import bootcamptask.model.SongBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SongValidatorTest {

    private List<Song> testList = new ArrayList<>();

    @BeforeEach
    void initList() {
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
    void isSongAlreadyInCollectionValidation_should_returnTrue_when_properTitleProperAuthorAndProperListGiven() {

        //given
        String title = "A";
        String author = "A";

        //when
        boolean result = SongValidator.isSongAlreadyInCollectionValidation(testList, title, author);

        //then
        assertThat(result).isTrue();

    }

    @Test
    void isSongAlreadyInCollectionValidation_should_returnFalse_when_properTitleAndWrongAuthorGiven() {

        //given
        String title = "A";
        String author = "Z";

        //when
        boolean result = SongValidator.isSongAlreadyInCollectionValidation(testList, title, author);

        //then
        assertThat(result).isFalse();

    }

    @Test
    void isSongAlreadyInCollectionValidation_should_returnFalse_when_wrongTitleAndProperAuthorGiven() {

        //given
        String title = "Z";
        String author = "A";

        //when
        boolean result = SongValidator.isSongAlreadyInCollectionValidation(testList, title, author);

        //then
        assertThat(result).isFalse();

    }
    @Test
    void isSongAlreadyInCollectionValidation_should_returnFalse_when_wrongTitleAndWrongAuthorGiven() {

        //given
        String title = "Z";
        String author = "Z";

        //when
        boolean result = SongValidator.isSongAlreadyInCollectionValidation(testList, title, author);

        //then
        assertThat(result).isFalse();

    }

    @Test
    void isSongAlreadyInCollectionValidation_should_returnFalse_when_properTitleProperAuthorAndEmptyListGiven() {

        //given
        String title = "A";
        String author = "A";
        List<Song> emptyList = new ArrayList<>();

        //when
        boolean result = SongValidator.isSongAlreadyInCollectionValidation(emptyList, title, author);

        //then
        assertThat(result).isFalse();

    }

    @Test
    void isGenreInCollectionValidation_should_returnTrue_when_properGenreAndProperListGiven() {
        //given
        String genre = "Rock";

        //when
        boolean result = SongValidator.isGenreInCollectionValidation(testList,genre);

        //then
        assertThat(result).isTrue();
    }

    @Test
    void isGenreInCollectionValidation_should_returnFalse_when_wrongGenreAndProperListGiven() {
        //given
        String genre = "Pop";

        //when
        boolean result = SongValidator.isGenreInCollectionValidation(testList,genre);

        //then
        assertThat(result).isFalse();
    }

    @Test
    void isGenreInCollectionValidation_should_returnFalse_when_wrongGenreAndEmptyListGiven() {
        //given
        String genre = "Pop";
        List<Song>emptyList = new ArrayList<>();

        //when
        boolean result = SongValidator.isGenreInCollectionValidation(emptyList,genre);

        //then
        assertThat(result).isFalse();
    }

    @Test
    void isGenreInCollectionValidation_should_returnFalse_when_properGenreAndEmptyListGiven() {
        //given
        String genre = "Rock";
        List<Song>emptyList = new ArrayList<>();

        //when
        boolean result = SongValidator.isGenreInCollectionValidation(emptyList,genre);

        //then
        assertThat(result).isFalse();
    }

}
