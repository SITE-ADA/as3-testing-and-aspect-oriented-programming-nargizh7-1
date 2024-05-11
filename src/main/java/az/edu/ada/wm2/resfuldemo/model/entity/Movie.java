package az.edu.ada.wm2.resfuldemo.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "MOVIES")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private Integer wins;

    public Movie(String name, String country, Integer wins) {
        this.name = name;
        this.country = country;
        this.wins = wins;
    }

    public Movie(String name, Integer wins) {
        this(name, "Not specified", wins);
    }

    @Override
    public String toString() {
        return "Movie: " + this.name + ": " + this.country;
    }

    public Movie(Long id) {
        this.id = id;
    }
}
