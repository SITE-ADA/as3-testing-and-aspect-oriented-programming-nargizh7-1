package az.edu.ada.wm2.resfuldemo.init;

import az.edu.ada.wm2.resfuldemo.model.entity.Movie;
import az.edu.ada.wm2.resfuldemo.repo.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class DBInitializer {

    @Bean
    @Autowired
    public CommandLineRunner init(MovieRepository movieRepo) {
        return (args) -> {
            Movie m1 = movieRepo.save(new Movie("test", "test 12345", 123));
            Movie m2 = movieRepo.save(new Movie("abc", "abc 12345", 234));

            Thread.sleep(1000 * 60);

            movieRepo.save(new Movie("latest", "latest movie created", 169));

            Thread.sleep(1000 * 90);

            movieRepo.save(m1);
            movieRepo.save(m2);
        };
    }

}
