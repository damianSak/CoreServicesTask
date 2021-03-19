package bootcamptask.utils.parsers;

import bootcamptask.model.Genre;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GenreUtilsTest {

    @Test
    void findByNameNoCaseSensitivity_shouldNot_returnNull_when_wrongGenreNameGiven() {
        //given
        String genreName = "Pop";
        Genre result;
        //when
        result = GenreUtils.findByNameCaseInsensitive(genreName);
        //then
        assertThat(result).isNull();
    }

    @Test
    void findByNameNoCaseSensitivity_should_returnProperGenre_when_properGenreNameGiven() {
        //given
        String genreName = "Rock";
        Genre expected = Genre.Rock;
        Genre result;
        //when
        result = GenreUtils.findByNameCaseInsensitive(genreName);
        //then
        assertThat(result).isEqualTo(expected);
    }
    @Test
    void findByNameNoCaseSensitivity_should_returnProperGenre_when_properGenreNameInLowCaseGiven() {
        //given
        String genreName = "rock";
        Genre expected = Genre.Rock;
        Genre result;
        //when
        result = GenreUtils.findByNameCaseInsensitive(genreName);
        //then
        assertThat(result).isEqualTo(expected);
    }
}