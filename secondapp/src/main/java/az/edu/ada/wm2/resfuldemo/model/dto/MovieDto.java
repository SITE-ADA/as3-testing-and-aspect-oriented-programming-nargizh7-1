package az.edu.ada.wm2.resfuldemo.model.dto;

import az.edu.ada.wm2.resfuldemo.model.entity.Movie;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MovieDto {

    private Long id;

    @NotBlank(message = "Movie name cannot be blank")
    @Size(max = 255, message = "Movie name must not exceed 255 characters")
    private String name;

    @NotBlank(message = "Country cannot be blank")
    @Size(max = 255, message = "Country name must not exceed 255 characters")
    private String country;

    @NotNull(message = "Number of wins must not be null")
    @Min(value = 0, message = "Number of wins cannot be negative")
    private Integer wins;

    public static MovieDto fromMovie(Movie movie) {
        MovieDto dto = new MovieDto();
        dto.setId(movie.getId());
        dto.setName(movie.getName());
        dto.setCountry(movie.getCountry());
        dto.setWins(movie.getWins());
        return dto;
    }
}
