package edu.sjsu.entertainmentbox.component;

import edu.sjsu.entertainmentbox.model.Movie;
import org.springframework.stereotype.Component;

@Component
public class MoviesByRatingComponent {

    private Movie movieName;
    private Double averageRating;

    public MoviesByRatingComponent() {
    }

    public MoviesByRatingComponent(Movie movieName, Double averageRating) {
        this.movieName = movieName;
        this.averageRating = averageRating;
    }
}
