package bootcamptask.model;

import bootcamptask.utils.converters.CategoryColumnCsvConverter;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvCustomBindByName;
import com.opencsv.bean.CsvCustomBindByPosition;

import java.util.Objects;

/**
 * Model class representing Song in application.
 * <p>
 * NameBinders are used during loading from csv process.
 * OrderBinders are used during saving to csv process.
 */
public class Song {

    @CsvBindByName(column = "Title", required = true)
    @CsvBindByPosition(position = 0)
    private String title;

    @CsvBindByName(column = "Author", required = true)
    @CsvBindByPosition(position = 1)
    private String author;

    @CsvBindByName(column = "Album", required = true)
    @CsvBindByPosition(position = 2)
    private String album;

    @CsvCustomBindByName(column = "Category", required = true, converter = CategoryColumnCsvConverter.class)
    @CsvCustomBindByPosition(position = 3, converter = CategoryColumnCsvConverter.class)
    private Genre category;

    @CsvBindByName(column = "Vote", required = true)
    @CsvBindByPosition(position = 4)
    private int vote;

    public Song() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public Genre getCategory() {
        return category;
    }

    public void setCategory(Genre category) {
        this.category = category;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int votes) {
        this.vote = votes;
    }

    @Override
    public String toString() {
        return "Tytuł='" + title + '\'' +
                ", Autor='" + author + '\'' +
                ", Album='" + album + '\'' +
                ", Gatunek=" + category +
                ", Ilość głosów=" + vote;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Song)) return false;
        Song song = (Song) o;
        return Objects.equals(title, song.title) &&
                Objects.equals(author, song.author) &&
                Objects.equals(album, song.album) &&
                category == song.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getAuthor(), getAlbum(), getCategory(), getVote());
    }

}
