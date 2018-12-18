package edu.sjsu.entertainmentbox.service;

import java.util.List;

import edu.sjsu.entertainmentbox.model.Movie;
import edu.sjsu.entertainmentbox.model.MoviePlayLog;

public interface AdminService {

	public void addMovie(Movie movie);

	public void deleteMovie(Movie movie);
	
	public List<MoviePlayLog> getPlayLogs();
	
}
