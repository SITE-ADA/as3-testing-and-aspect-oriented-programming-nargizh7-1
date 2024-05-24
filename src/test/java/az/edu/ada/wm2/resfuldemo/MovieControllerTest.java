package az.edu.ada.wm2.resfuldemo;

import az.edu.ada.wm2.resfuldemo.controller.MovieController;
import az.edu.ada.wm2.resfuldemo.model.dto.MovieDto;
import az.edu.ada.wm2.resfuldemo.service.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MovieController.class)
class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieService movieService;

    private MovieDto movieDto;

    @BeforeEach
    void setUp() {
        movieDto = new MovieDto();
        movieDto.setId(1L);
        movieDto.setName("Test Movie");
        movieDto.setCountry("Test Country");
        movieDto.setWins(10);
    }

    @Test
    void testGetIndexPage() throws Exception {
        mockMvc.perform(get("/movie/index"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    void testCreateNewMovie() throws Exception {
        mockMvc.perform(get("/movie/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("new"))
                .andExpect(model().attributeExists("movieDto"));
    }

    @Test
    void testSave() throws Exception {
        when(movieService.save(any(MovieDto.class))).thenReturn(movieDto);

        mockMvc.perform(post("/movie/")
                        .flashAttr("movieDto", movieDto))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/movie/"));
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(get("/movie/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/movie/"));
    }

    @Test
    void testShowUpdateForm() throws Exception {
        when(movieService.getDtoById(anyLong())).thenReturn(movieDto);

        mockMvc.perform(get("/movie/update/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("update"))
                .andExpect(model().attributeExists("movieDto"));
    }

    @Test
    void testUpdateMovie() throws Exception {
        when(movieService.save(any(MovieDto.class))).thenReturn(movieDto);

        mockMvc.perform(post("/movie/update/1")
                        .flashAttr("movieDto", movieDto))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/movie/"));
    }

    @Test
    void testGetWebMovies() throws Exception {
        when(movieService.getAllWebMovies(anyString())).thenReturn(List.of(movieDto));

        mockMvc.perform(get("/movie/filter/Test"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("movies"));
    }}
