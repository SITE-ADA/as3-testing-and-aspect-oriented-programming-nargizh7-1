package az.edu.ada.wm2.resfuldemo.model.mapper;

import az.edu.ada.wm2.resfuldemo.model.dto.MovieDto;
import az.edu.ada.wm2.resfuldemo.model.entity.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MovieMapper {

    MovieMapper INSTANCE = Mappers.getMapper(MovieMapper.class);

    @Mapping(target = "wins", source = "wins")
    MovieDto movieToMovieDto(Movie movie);

    Movie movieDtoToMovie(MovieDto movieDto);
}
