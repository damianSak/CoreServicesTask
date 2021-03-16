package bootcamp_task.model;

import bootcamp_task.utils.CategoryColumnCsvConverter;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;

public class Song {

    @CsvBindByName(column = "Title", required = true)

//   @CsvBindByPosition(position = 0)
    private String title;

    @CsvBindByName(column = "Author", required = true)
//    @CsvBindByPosition(position = 1)
    private String author;

    @CsvBindByName(column = "Album", required = true)
//    @CsvBindByPosition(position = 2)
    private String album;

    @CsvCustomBindByName(column = "Category", required = true, converter = CategoryColumnCsvConverter.class)
//   @CsvBindByPosition(position = 3)
    private Genre category;

    @CsvBindByName(column = "Vote", required = true)
//    @CsvBindByPosition(position = 4)
    private int vote;

    public Song() {

    }

    public Song(String title, String author, String album, Genre category, int vote) {
        this.title = title;
        this.author = author;
        this.album = album;
        this.category = category;
        this.vote = vote;
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
        return "Song{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", album='" + album + '\'' +
                ", category=" + category +
                ", votes=" + vote +
                '}';
    }
}
