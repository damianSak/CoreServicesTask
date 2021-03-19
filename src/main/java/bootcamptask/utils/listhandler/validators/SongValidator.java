package bootcamptask.utils.listhandler.validators;

import bootcamptask.model.Song;
import bootcamptask.utils.parsers.GenreUtils;

import java.util.List;

public class SongValidator {

    public static boolean isSongAlreadyInCollectionValidation(List<Song> songs, String title, String author) {
        return songs.stream().anyMatch(h -> h.getTitle().toLowerCase().equals(title.toLowerCase()) &&
                h.getAuthor().toLowerCase().equals(author.toLowerCase()));
    }

    public static boolean isGenreInCollectionValidation(List<Song> songs, String genreName) {
        return songs.stream().anyMatch(h -> h.getCategory().equals(GenreUtils.findByNameCaseInsensitive(genreName.toLowerCase())));
    }

}
