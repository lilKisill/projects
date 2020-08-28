package movie.website.service;

import movie.website.model.Picture;
import movie.website.repository.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PictureService {
    @Autowired
    private PictureRepository pictureRepository;

    public List<Picture> listAllPicture() {
        return pictureRepository.findAll();
    }

    public List<Picture> listPictureOfSpecialShowPlace(String showPlace){
        return pictureRepository.findAllByShowPlace(showPlace);
    }

    public Page<Picture> listAllPicturePageRequest(String showPlace, Pageable pageable){
        return pictureRepository.findAllByShowPlace(showPlace, pageable);
    }

    public Picture getPicture(int id) {
        return pictureRepository.findById(id).get();
    }

    public Page<Picture> listPictureWithSpecialTypePageRequest(String showplace, String pictureType, Pageable pageable){
        return pictureRepository.findAllByShowPlaceAndPictureType(showplace, pictureType, pageable);
    }

    public List<Picture> listPictureWithSpecialType(String showplace, String pictureType){
        return pictureRepository.findAllByShowPlaceAndPictureType(showplace, pictureType);
    }



    /*public List<Picture> findFilm(int str){
        return pictureRepository.findSomePicture(str);
    }*/

    /*public void save(Product product) {
        repo.save(product);
    }

    public Product get(long id) {
        return repo.findById(id).get();
    }

    public void delete(long id) {
        repo.deleteById(id);
    }*/
}
