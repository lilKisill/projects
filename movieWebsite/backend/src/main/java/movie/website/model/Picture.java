package movie.website.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Picture {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String title;
    private LocalDate releaseDate;
    private float rating;
    private String imagePath;
    private String pictureType;
    private String showPlace;
    private int limitations;
    private String description;
    @ManyToMany
    @JoinTable(
            name = "picture_genre",
            joinColumns = @JoinColumn(name = "picture_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre> genres;

    public Picture() {
    }

    public Picture(String title, LocalDate releaseDate, float rating, String imagePath, String pictureType, String showPlace, int limitations, String description, List<Genre> genres) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.imagePath = imagePath;
        this.pictureType = pictureType;
        this.showPlace = showPlace;
        this.limitations = limitations;
        this.description = description;
        this.genres = genres;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getPictureType() {
        return pictureType;
    }

    public void setPictureType(String pictureType) {
        this.pictureType = pictureType;
    }

    public String getShowPlace() {
        return showPlace;
    }

    public void setShowPlace(String showPlace) {
        this.showPlace = showPlace;
    }

    public int getLimitations() {
        return limitations;
    }

    public void setLimitations(int limitations) {
        this.limitations = limitations;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }
}
