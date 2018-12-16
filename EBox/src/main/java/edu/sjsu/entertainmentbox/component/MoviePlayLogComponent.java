package edu.sjsu.entertainmentbox.component;

import edu.sjsu.entertainmentbox.model.Movie;
import edu.sjsu.entertainmentbox.model.MoviePlayLog;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class MoviePlayLogComponent {

    private Movie movie;
    private Set<MoviePlayLog> moviePlayLogs;


    public MoviePlayLogComponent() {
    }

    public MoviePlayLogComponent(Movie movie, Set<MoviePlayLog> moviePlayLogs) {
        this.movie = movie;
        this.moviePlayLogs = moviePlayLogs;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Set<MoviePlayLog> getMoviePlayLogs() {
        return moviePlayLogs;
    }

    public void setMoviePlayLogs(Set<MoviePlayLog> moviePlayLogs) {
        this.moviePlayLogs = moviePlayLogs;
    }
}
