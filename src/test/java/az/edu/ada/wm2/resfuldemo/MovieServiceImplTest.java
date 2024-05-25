package az.edu.ada.wm2.resfuldemo;

import az.edu.ada.wm2.resfuldemo.model.dto.MovieDto;
import az.edu.ada.wm2.resfuldemo.model.entity.Movie;
import az.edu.ada.wm2.resfuldemo.repo.MovieRepository;
import az.edu.ada.wm2.resfuldemo.service.MovieServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class MovieServiceImplTest {

    @Mock
    private MovieRepository movieRepo;

    @InjectMocks
    private MovieServiceImpl movieService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListDto() {
        Movie movie = new Movie();
        movie.setId(1L);
        movie.setName("Test Movie");
        Page<Movie> moviePage = new PageImpl<>(Collections.singletonList(movie));

        when(movieRepo.findAll(any(PageRequest.class))).thenReturn(moviePage);

        Page<MovieDto> result = movieService.listDto(1, "name", "asc", "", "");

        assertThat(result).isNotNull();
        assertThat(result.getContent()).hasSize(1);
        assertThat(result.getContent().get(0).getName()).isEqualTo("Test Movie");
    }

    @Test
    void testSave() {
        Movie movie = new Movie();
        movie.setId(1L);
        movie.setName("Test Movie");
        when(movieRepo.save(any(Movie.class))).thenReturn(movie);

        MovieDto movieDto = new MovieDto();
        movieDto.setName("Test Movie");

        MovieDto result = movieService.save(movieDto);

        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("Test Movie");
    }

    @Test
    void testGetById() {
        Movie movie = new Movie();
        movie.setId(1L);
        movie.setName("Test Movie");
        when(movieRepo.findById(1L)).thenReturn(Optional.of(movie));

        MovieDto result = movieService.getById(1L);

        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("Test Movie");
    }

    @Test
    void testDeleteById() {
        doNothing().when(movieRepo).deleteById(1L);

        movieService.deleteById(1L);

        verify(movieRepo, times(1)).deleteById(1L);
    }

    @Test
    void testGetAllWebMovies() {
        Movie movie = new Movie();
        movie.setName("Test Movie");
        when(movieRepo.findByCountryContainingIgnoreCase(anyString())).thenReturn(Collections.singletonList(movie));

        List<MovieDto> result = movieService.getAllWebMovies("Test");

        assertThat(result).isNotNull();
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getName()).isEqualTo("Test Movie");
    }

    @Test
    void testGetAllMovies() {
        Movie movie = new Movie();
        movie.setName("Test Movie");
        when(movieRepo.findAll()).thenReturn(Collections.singletonList(movie));

        List<MovieDto> result = movieService.getAllMovies();

        assertThat(result).isNotNull();
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getName()).isEqualTo("Test Movie");
    }
}
