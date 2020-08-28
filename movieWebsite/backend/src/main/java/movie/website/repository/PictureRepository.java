package movie.website.repository;

import movie.website.model.Genre;
import movie.website.model.Picture;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PictureRepository extends JpaRepository<Picture, Integer> {
    List<Picture> findAllByShowPlace(String showPlace);
    Page<Picture> findAllByShowPlace(String showPlace, Pageable pageable);

    Page<Picture> findAllByShowPlaceAndPictureType(String showplace, String pictureType, Pageable pageable);
    List<Picture> findAllByShowPlaceAndPictureType(String showplace, String pictureType);


}

