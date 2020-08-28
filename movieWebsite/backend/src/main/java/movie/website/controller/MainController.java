package movie.website.controller;

import movie.website.model.Genre;
import movie.website.model.Picture;
import movie.website.repository.PictureRepository;
import movie.website.service.GenreService;
import movie.website.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    PictureService pictureService;

    @Autowired
    GenreService genreService;

    @GetMapping("/main")
    public String viewMainPage(Model model, @RequestParam(defaultValue = "0") int page){
        List<Picture> listOfReleaseInMovie = pictureService.listPictureOfSpecialShowPlace("movie");
        Page<Picture> listOfPictureAtHome = pictureService.listAllPicturePageRequest("home", PageRequest.of(page, 4));
        model.addAttribute("listOfPictureAtHome", listOfPictureAtHome);
        model.addAttribute("sylka", "main");
        model.addAttribute("listOfReleaseInMovie", listOfReleaseInMovie);
        return "main_page";
    }

    @GetMapping("/movies")
    public String viewMoviesPage(Model model, @RequestParam(defaultValue = "0") int page){
        List<Picture> listOfReleaseInMovie = pictureService.listPictureWithSpecialType("movie", "film");
        Page<Picture> listOfPictureAtHome = pictureService.listPictureWithSpecialTypePageRequest("home","film", PageRequest.of(page, 4));
        model.addAttribute("listOfPictureAtHome", listOfPictureAtHome);
        model.addAttribute("sylka", "movies");
        model.addAttribute("listOfReleaseInMovie", listOfReleaseInMovie);
        return "main_page";
    }

    @GetMapping("/cartoons")
    public String viewCartoonsPage(Model model, @RequestParam(defaultValue = "0") int page){
        List<Picture> listOfReleaseInMovie = pictureService.listPictureWithSpecialType("movie", "cartoon");
        Page<Picture> listOfPictureAtHome = pictureService.listPictureWithSpecialTypePageRequest("home","cartoon", PageRequest.of(page, 4));
        model.addAttribute("listOfPictureAtHome", listOfPictureAtHome);
        model.addAttribute("sylka", "cartoons");
        model.addAttribute("listOfReleaseInMovie", listOfReleaseInMovie);
        return "main_page";
    }

    @GetMapping("/series")
    public String viewSeriesPage(Model model, @RequestParam(defaultValue = "0") int page){
        List<Picture> listOfReleaseInMovie = pictureService.listPictureWithSpecialType("movie", "serial");
        Page<Picture> listOfPictureAtHome = pictureService.listPictureWithSpecialTypePageRequest("home","serial", PageRequest.of(page, 4));
        model.addAttribute("listOfPictureAtHome", listOfPictureAtHome);
        model.addAttribute("sylka", "series");
        model.addAttribute("listOfReleaseInMovie", listOfReleaseInMovie);
        return "main_page";
    }



    @GetMapping("/picture/{id}")
    public String showContentPicturePage(Model model, @PathVariable(name = "id") int id) {
        //ModelAndView modelAndView = new ModelAndView("picture_task");
        Picture picture = pictureService.getPicture(id);
        model.addAttribute("picture", picture);
        return "picture_page";
    }

    @GetMapping("/account")
    public String viewAccountPage(Model model){
        return "account_page";
    }

    /*@RequestMapping("/ai")
    public String viewLabPage(Model model){
        List<Genre> listOfGenres = genreService.listAllGenres();
        List<Picture> listOfPicture = null;
        model.addAttribute("listOfGenres", listOfGenres);
        return "laba_3";
    }

    @RequestMapping("/filter")
    public String filter(@RequestParam List<Genre> name, Model model){
        List<Picture> somePicture = pictureService.listAllPicture();
        List<Picture> listOfPictureWithGenres = new ArrayList<>();
        for (int i = 0; i < somePicture.size(); i++) {
            if(somePicture.get(i).getGenres().size() != 0){
                listOfPictureWithGenres.add(somePicture.get(i));
            }
        }

        List<Picture> listOfPictureEqualGenres = new ArrayList<>();
        List<Picture> listOfPictureSimilarGenres = new ArrayList<>();
        for (int i = 0; i < listOfPictureWithGenres.size(); i++) {
            int count = 0;
            for (int k = 0; k < name.size(); k++) {
                if(listOfPictureWithGenres.get(i).getGenres().contains(name.get(k))){
                    count++;
                }
            }
            if(count == name.size() & listOfPictureWithGenres.get(i).getGenres().size() == name.size()){
                listOfPictureEqualGenres.add(listOfPictureWithGenres.get(i));
            } else if (count > 0){
                listOfPictureSimilarGenres.add(listOfPictureWithGenres.get(i));
            }
        }
        List<Genre> listOfGenres = genreService.listAllGenres();
        model.addAttribute("listOfGenres", listOfGenres);
        model.addAttribute("listOfMatchedMovies", listOfPictureEqualGenres);
        model.addAttribute("listOfPictureSimilarGenres", listOfPictureSimilarGenres);
        model.addAttribute("pressedGenres", name);
        return "laba_3";
    }*/
}
