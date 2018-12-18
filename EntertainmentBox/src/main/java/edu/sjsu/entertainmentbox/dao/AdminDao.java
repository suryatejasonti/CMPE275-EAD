package edu.sjsu.entertainmentbox.dao;

import java.util.List;

import edu.sjsu.entertainmentbox.model.CustomerSubscription;
import edu.sjsu.entertainmentbox.model.Movie;
import edu.sjsu.entertainmentbox.model.MoviePlayLog;
import edu.sjsu.entertainmentbox.model.Rating;

public interface AdminDao {

	public void addMovie(Movie movie);

	public void deleteMovie(List<MoviePlayLog> moviePlayLogs, List<Rating> ratings, List<CustomerSubscription> customerSubscriptions, Movie movie);

	public List<MoviePlayLog> getPlayLogs();

	public List<Rating> getRatings();
}
