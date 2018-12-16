package edu.sjsu.entertainmentbox.service;

import edu.sjsu.entertainmentbox.component.MoviePlayLogComponent;
import edu.sjsu.entertainmentbox.model.Customer;
import edu.sjsu.entertainmentbox.model.Movie;
import edu.sjsu.entertainmentbox.model.MoviePlayLog;

import java.util.List;
import java.util.Set;

public interface AdminService {

    void addMovie(Movie movie);

    void editMovie (Movie movie);

    void deleteMovie(Integer movieId);

    List<Movie> searchMovies();

    List<Customer> browseCustomers();

    Set<MoviePlayLog> getMoviePlayhistory(String emailId);

    //**For every movie, it can be counted as only one play for the same customer within 24 hours.
    List<String> getTopNCustomers(Integer timePeriod);

    //**For every movie, it can be counted as only one play for the same customer within 24 hours.
    Integer getNumberOfPlays(Integer timePeriod, Integer movieId);

    List<String> getTopNMovies(Integer timePeriod);

    public Customer browseCustomerByEmailId(String emailId);





}
