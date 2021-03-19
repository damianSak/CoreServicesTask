package bootcamptask.utils.listhandler;

import bootcamptask.model.Song;
import bootcamptask.utils.parsers.GenreUtils;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ListHandler {

    public static List<Song> songsSubListByTitle(String title, List<Song> songs){
        return songs.stream().filter(h->h.getTitle().toLowerCase().equals(title.toLowerCase())).collect(Collectors.toList());
    }

    public static List<Song> sortSongsByVotes(List<Song> songs) {
        return songs.stream().sorted(Comparator.comparing(Song::getVote).reversed()).collect(Collectors.toList());
    }

    public static List<Song> sortSongsByVotesAndGenre(List<Song> songs, String genreName) {
        return songs.stream().filter(h -> h.getCategory().equals(GenreUtils.findByNameCaseInsensitive(genreName.toLowerCase())))
                .sorted(Comparator.comparing(Song::getVote).reversed()).collect(Collectors.toList());
    }
}
