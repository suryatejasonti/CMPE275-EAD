package edu.sjsu.entertainmentbox.service;

import java.text.ParseException;
import java.util.List;

import edu.sjsu.entertainmentbox.model.Customer;
import edu.sjsu.entertainmentbox.model.CustomerSubscription;
import edu.sjsu.entertainmentbox.model.Movie;
import edu.sjsu.entertainmentbox.model.MovieInformation;
import edu.sjsu.entertainmentbox.model.Rating;
import edu.sjsu.entertainmentbox.model.SubscriptionType;

public interface CustomerService {
	public Customer getCustomer(String emailaddress);
	public void saveSubscription(String emailAddress, int price, int noOfMonths, SubscriptionType subscriptionType, Movie movie) throws ParseException;
	public List<MovieInformation> searchMovie(String emailAddress, String keywords, String year, String actors, String director, String[] genres, String[] mpaaRatings, String numberOfStars);
	public List<CustomerSubscription> getAllCustomerSubscriptions();
	public void submitRating(int movieId, String emailAddress, int stars) throws ParseException;
	public double getAverageStars(Movie movie, List<Rating> ratings);
	public boolean checkCustomer(String emailAddress, List<CustomerSubscription> customerSubscriptions);
	public Movie getMovie(int movieId);
	public List<MovieInformation> getHighlyRatedMovies(String emailAddress, int days);
}
