package az.edu.ada.wm2.resfuldemo;

import az.edu.ada.wm2.resfuldemo.model.dto.MovieDto;
import az.edu.ada.wm2.resfuldemo.model.entity.Movie;
import az.edu.ada.wm2.resfuldemo.repo.MovieRepository;
import az.edu.ada.wm2.resfuldemo.service.MovieServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {

    @Mock
    private MovieRepository movieRepo;

    @InjectMocks
    private MovieServiceImpl movieService;

    private Movie movie;
    private MovieDto movieDto;

    @BeforeEach
    void setUp() {
        movie = new Movie();
        movie.setId(1L);
        movie.setName("Test Movie");
        movie.setCountry("Test Country");
        movie.setWins(10);

        movieDto = new MovieDto();
        movieDto.setId(1L);
        movieDto.setName("Test Movie");
        movieDto.setCountry("Test Country");
        movieDto.setWins(10);
    }

    @Test
    void testListDto() {
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("name"));
        Page<Movie> moviePage = new PageImpl<>(Arrays.asList(movie), pageRequest, 1);
        given(movieRepo.findAll(any(PageRequest.class))).willReturn(moviePage);

        Page<MovieDto> result = movieService.listDto(1, "name", "asc", "", "");

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        verify(movieRepo, times(1)).findAll(any(PageRequest.class));
    }

    @Test
    void testSave() {
        given(movieRepo.save(any(Movie.class))).willReturn(movie);

        MovieDto result = movieService.save(movieDto);

        assertNotNull(result);
        assertEquals(movieDto.getName(), result.getName());
        verify(movieRepo, times(1)).save(any(Movie.class));
    }

    @Test
    void testGetById() {
        given(movieRepo.findById(anyLong())).willReturn(Optional.of(movie));

        MovieDto result = movieService.getById(1L);

        assertNotNull(result);
        assertEquals(movie.getName(), result.getName());
        verify(movieRepo, times(1)).findById(1L);
    }

    @Test
    void testDeleteById() {
        doNothing().when(movieRepo).deleteById(anyLong());

        movieService.deleteById(1L);

        verify(movieRepo, times(1)).deleteById(1L);
    }

    @Test
    void testGetAllWebMovies() {
        given(movieRepo.findByCountryContainingIgnoreCase(anyString())).willReturn(Arrays.asList(movie));

        List<MovieDto> result = movieService.getAllWebMovies("Test");

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(movieRepo, times(1)).findByCountryContainingIgnoreCase("Test");
    }

    @Test
    void testGetAllMovies() {
        given(movieRepo.findAll()).willReturn(Arrays.asList(movie));

        List<MovieDto> result = movieService.getAllMovies();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(movieRepo, times(1)).findAll();
    }
}
