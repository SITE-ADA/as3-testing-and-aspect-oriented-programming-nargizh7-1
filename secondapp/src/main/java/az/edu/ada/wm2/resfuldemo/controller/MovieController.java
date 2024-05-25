package az.edu.ada.wm2.resfuldemo.controller;

import az.edu.ada.wm2.resfuldemo.model.dto.MovieDto;
import az.edu.ada.wm2.resfuldemo.model.mapper.MovieMapper;
import az.edu.ada.wm2.resfuldemo.service.MovieService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/movie")
public class MovieController {

    private static final Logger logger = LoggerFactory.getLogger(MovieController.class);
    @Autowired
    private MovieService movieService;

    @Autowired
    private RestTemplate restTemplate;

    private final String BASE_URL = "http://localhost:8080/movies";

    @GetMapping("/index")
    public String getIndexPage(Model model,
                               @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                               @RequestParam(name = "sortField", defaultValue = "name") String sortField,
                               @RequestParam(name = "sortDir", defaultValue = "asc") String sortDir,
                               @RequestParam(name = "filterField", defaultValue = "") String filterField,
                               @RequestParam(name = "filterValue", defaultValue = "") String filterValue) {
        return getMovies(model, pageNo, sortField, sortDir, filterField, filterValue);
    }

    @GetMapping({"", "/", "/list"})
    public String getMovies(Model model,
                            @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                            @RequestParam(name = "sortField", defaultValue = "name") String sortField,
                            @RequestParam(name = "sortDir", defaultValue = "asc") String sortDir,
                            @RequestParam(name = "filterField", defaultValue = "") String filterField,
                            @RequestParam(name = "filterValue", defaultValue = "") String filterValue) {
        // Construct the URL with the appropriate parameters
        String url = BASE_URL + "?pageNo=" + pageNo + "&sortField=" + sortField + "&sortDir=" + sortDir +
                "&filterField=" + filterField + "&filterValue=" + filterValue;

        // Send GET request to the other application
        ResponseEntity<Page<MovieDto>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<Page<MovieDto>>() {});

        Page<MovieDto> moviesPage = responseEntity.getBody();

        model.addAttribute("movies", moviesPage.getContent());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalElements", moviesPage.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("filterField", filterField);
        model.addAttribute("filterValue", filterValue);

        return "index";
    }

    @GetMapping("/new")
    public String createNewMovie(Model model) {
        // Send GET request to retrieve the new movie form from the other application
        ResponseEntity<MovieDto> responseEntity = restTemplate.getForEntity(BASE_URL + "/new", MovieDto.class);
        MovieDto movieDto = responseEntity.getBody();
        model.addAttribute("movieDto", movieDto);
        return "new";
    }

    @PostMapping("/")
    public String save(@Valid @ModelAttribute("movieDto") MovieDto movieDto, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.movieDto", result);
            redirectAttributes.addFlashAttribute("movieDto", movieDto);
            return "new";  // Redirect back to the form page with errors
        }
        try {
            // Send POST request to save the movie data in the other application
            ResponseEntity<MovieDto> savedMovieResponse = restTemplate.postForEntity(BASE_URL, movieDto, MovieDto.class);
            return "redirect:/movie/";
        } catch (Exception e) {
            logger.error("Error saving movie: {}", movieDto.getName(), e);
            return "errorPage";
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        try {
            // Send DELETE request to delete the movie with the specified ID in the other application
            restTemplate.delete(BASE_URL + "/delete/" + id);
            return "redirect:/movie/";
        } catch (Exception e) {
            logger.error("Error deleting movie with ID: {}", id, e);
            return "errorPage";
        }
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        // Send GET request to retrieve the movie data to be updated from the other application
        ResponseEntity<MovieDto> responseEntity = restTemplate.getForEntity(BASE_URL + "/update/" + id, MovieDto.class);
        MovieDto movieDto = responseEntity.getBody();
        model.addAttribute("movieDto", movieDto);
        return "update";
    }

    @PostMapping("/update/{id}")
    public String updateMovie(@PathVariable Long id, @Valid @ModelAttribute("movieDto") MovieDto movieDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("movieDto", movieDto);
            model.addAttribute("org.springframework.validation.BindingResult.movieDto", result);
            return "update";  // Return directly to the update view with the form data and errors
        }
        // Send PUT request to update the movie data in the other application
        restTemplate.put(BASE_URL + "/update/" + id, movieDto);
        return "redirect:/movie/";  // Redirect after successful update
    }

    @GetMapping("/filter/{keyword}")
    public String getWebMovies(Model model, @PathVariable String keyword) {
        // Send GET request to retrieve the filtered movies from the other application
        ResponseEntity<List<MovieDto>> responseEntity = restTemplate.exchange(BASE_URL + "/filter/" + keyword, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<MovieDto>>() {});
        List<MovieDto> movies = responseEntity.getBody();
        model.addAttribute("movies", movies);
        return "index";
    }

}
