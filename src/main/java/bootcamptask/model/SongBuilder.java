package bootcamptask.model;

public final class SongBuilder {
    private String title;
    private String author;
    private String album;
    private Genre category;
    private int vote;

    private SongBuilder() {
    }

    public static SongBuilder builder() {
        return new SongBuilder();
    }

    public SongBuilder title(String title) {
        this.title = title;
        return this;
    }

    public SongBuilder author(String author) {
        this.author = author;
        return this;
    }

    public SongBuilder album(String album) {
        this.album = album;
        return this;
    }

    public SongBuilder category(Genre category) {
        this.category = category;
        return this;
    }

    public SongBuilder vote(int vote) {
        this.vote = vote;
        return this;
    }

    public Song build() {
        Song song = new Song();
        song.setTitle(title);
        song.setAuthor(author);
        song.setAlbum(album);
        song.setCategory(category);
        song.setVote(vote);
        return song;
    }
}
