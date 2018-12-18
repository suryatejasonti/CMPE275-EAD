package edu.sjsu.entertainmentbox.dao;

import java.util.List;

import edu.sjsu.entertainmentbox.model.Customer;
import edu.sjsu.entertainmentbox.model.CustomerSubscription;
import edu.sjsu.entertainmentbox.model.Movie;
import edu.sjsu.entertainmentbox.model.Rating;
import edu.sjsu.entertainmentbox.model.User;

public interface CustomerDao {
	public Customer getCustomer(String emailaddress);
	public void saveSubscription(CustomerSubscription customerSubscription, Customer customer);
	public void saveCustomer(Customer customer);
	public List<Movie> searchMovie(String searchText);
	public List<CustomerSubscription> getAllSubscriptions();
	public Movie getMovie(int movieId);
	public void submitRating(Rating rating);
	public List<Rating> getRatings();
}
