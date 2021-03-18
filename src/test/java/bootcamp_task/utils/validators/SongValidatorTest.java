package bootcamp_task.utils.validators;

import bootcamp_task.model.Genre;
import bootcamp_task.model.Song;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SongValidatorTest {

    List<Song> testList = new ArrayList<>();

    @BeforeEach
    void initList() {
        this.testList = new ArrayList<>();
        Song song1 = new Song("A", "A", "A", Genre.Rock, 10);
        Song song2 = new Song("B", "B", "B", Genre.Rock, 2);
        Song song3 = new Song("C", "C", "C", Genre.Rock, 300);
        Song song4 = new Song("A", "D", "D", Genre.Rock, 40);
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

}
