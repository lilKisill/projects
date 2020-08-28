package movie.website.service;

import movie.website.model.Genre;
import movie.website.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GenreService {
    @Autowired
    private GenreRepository genreRepository;

    public List<Genre> listAllGenres() {
        return genreRepository.findAll();
    }
}
