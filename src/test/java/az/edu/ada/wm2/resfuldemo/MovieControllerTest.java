package az.edu.ada.wm2.resfuldemo;

import az.edu.ada.wm2.resfuldemo.controller.MovieController;
import az.edu.ada.wm2.resfuldemo.model.dto.MovieDto;
import az.edu.ada.wm2.resfuldemo.service.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class MovieControllerIntegrationTest {

    @Mock
    private MovieService movieService;

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;

    @Mock
    private RedirectAttributes redirectAttributes;

    @InjectMocks
    private MovieController movieController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetIndexPage() {
        // Arrange
        when(movieService.listDto(anyInt(), anyString(), anyString(), anyString(), anyString())).thenReturn(Page.empty());

        // Act
        String viewName = movieController.getIndexPage(model, 1, "name", "asc", "", "");

        // Assert
        assertEquals("index", viewName);
        verify(movieService, times(1)).listDto(anyInt(), anyString(), anyString(), anyString(), anyString());
        verify(model, times(1)).addAttribute(eq("movies"), anyList());
        verify(model, times(1)).addAttribute(eq("currentPage"), anyInt());
        verify(model, times(1)).addAttribute(eq("totalElements"), anyLong());
        verify(model, times(1)).addAttribute(eq("sortField"), anyString());
        verify(model, times(1)).addAttribute(eq("sortDir"), anyString());
        verify(model, times(1)).addAttribute(eq("filterField"), anyString());
        verify(model, times(1)).addAttribute(eq("filterValue"), anyString());
    }

    @Test
    void testCreateNewMovie() {
        // Act
        String viewName = movieController.createNewMovie(model);

        // Assert
        assertEquals("new", viewName);
        verify(model, times(1)).addAttribute(eq("movieDto"), any(MovieDto.class));
    }

    @Test
    void testSaveMovie() {
        // Arrange
        MovieDto movieDto = new MovieDto();
        when(bindingResult.hasErrors()).thenReturn(false);
        when(movieService.save(any(MovieDto.class))).thenReturn(movieDto);

        // Act
        String viewName = movieController.save(movieDto, bindingResult, redirectAttributes);

        // Assert
        assertEquals("redirect:/movie/", viewName);
        verify(movieService, times(1)).save(any(MovieDto.class));
    }

    @Test
    void testSaveMovieWithErrors() {
        // Arrange
        MovieDto movieDto = new MovieDto();
        when(bindingResult.hasErrors()).thenReturn(true);

        // Act
        String viewName = movieController.save(movieDto, bindingResult, redirectAttributes);

        // Assert
        assertEquals("new", viewName);
        verify(movieService, times(0)).save(any(MovieDto.class));
    }

    @Test
    void testDeleteMovie() {
        // Act
        String viewName = movieController.delete(1L);

        // Assert
        assertEquals("redirect:/movie/", viewName);
        verify(movieService, times(1)).deleteById(1L);
    }

    @Test
    void testShowUpdateForm() {
        // Arrange
        MovieDto movieDto = new MovieDto();
        when(movieService.getDtoById(anyLong())).thenReturn(movieDto);

        // Act
        String viewName = movieController.showUpdateForm(1L, model);

        // Assert
        assertEquals("update", viewName);
        verify(model, times(1)).addAttribute(eq("movieDto"), any(MovieDto.class));
    }

    @Test
    void testUpdateMovie() {
        // Arrange
        MovieDto movieDto = new MovieDto();
        when(bindingResult.hasErrors()).thenReturn(false);
        when(movieService.save(any(MovieDto.class))).thenReturn(movieDto);

        // Act
        String viewName = movieController.updateMovie(1L, movieDto, bindingResult, model);

        // Assert
        assertEquals("redirect:/movie/", viewName);
        verify(movieService, times(1)).save(any(MovieDto.class));
    }

    @Test
    void testUpdateMovieWithErrors() {
        // Arrange
        MovieDto movieDto = new MovieDto();
        when(bindingResult.hasErrors()).thenReturn(true);

        // Act
        String viewName = movieController.updateMovie(1L, movieDto, bindingResult, model);

        // Assert
        assertEquals("update", viewName);
        verify(movieService, times(0)).save(any(MovieDto.class));
    }
}
