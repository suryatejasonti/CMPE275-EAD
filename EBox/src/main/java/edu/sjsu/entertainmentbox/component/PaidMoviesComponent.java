package edu.sjsu.entertainmentbox.component;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import edu.sjsu.entertainmentbox.model.Movie;
import org.springframework.stereotype.Component;

import java.util.List;

@JsonSerialize
@Component
public class PaidMoviesComponent {

    private List<Movie> movies;

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
