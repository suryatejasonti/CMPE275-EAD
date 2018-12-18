package edu.sjsu.entertainmentbox.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.sjsu.entertainmentbox.dao.AdminDao;
import edu.sjsu.entertainmentbox.dao.CustomerDao;
import edu.sjsu.entertainmentbox.model.CustomerSubscription;
import edu.sjsu.entertainmentbox.model.Movie;
import edu.sjsu.entertainmentbox.model.MoviePlayLog;
import edu.sjsu.entertainmentbox.model.Rating;
import edu.sjsu.entertainmentbox.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private AdminDao adminDao;

	@Autowired
	private CustomerDao customerDao;

	@Override
	public void addMovie(Movie movie) {
		adminDao.addMovie(movie);
	}

	@Override
	public void deleteMovie(Movie movie) {
		List<MoviePlayLog> moviePlayLogs = adminDao.getPlayLogs();
		List<Rating> ratings = adminDao.getRatings();
		List<CustomerSubscription> customerSubscriptions = customerDao.getAllSubscriptions();
		adminDao.deleteMovie(moviePlayLogs, ratings, customerSubscriptions, movie);
	}

	@Override
	public List<MoviePlayLog> getPlayLogs() {
		return adminDao.getPlayLogs();
	}

}
