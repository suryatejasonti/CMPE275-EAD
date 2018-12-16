package edu.sjsu.entertainmentbox.service;

import java.text.ParseException;
import java.util.List;
import java.util.Set;

import edu.sjsu.entertainmentbox.component.MoviesByRatingComponent;
import edu.sjsu.entertainmentbox.component.PaidMoviesComponent;
import edu.sjsu.entertainmentbox.model.*;

public interface CustomerService {
	public Customer getCustomer(String emailaddress);
	public void saveSubscription(String emailAddress, int price, int noOfMonths, SubscriptionType subscriptionType, Movie movie) throws ParseException;
	public List<Movie> searchMovie(String searchText);

	//The subscription can start at any day, and the subscription fee for the current month ends at 12 am the same day next month.
	// If next month does not have the same day, then it ends at the last day of next month.
	// For example, if you start your subscription on Jan 30 and only paid $10 monthly fee, the fee is good until the last day of February,
	// and you must pay/renew your subscription by 12 AM March 1st.
	CustomerSubscription startSubscription(String emailAddress, Integer noOfMonths, SubscriptionType subscriptionType, Integer price, Integer movieId);


	//View billing status: a subscription user must be able to find out when his subscription is up for renewal.
	public List<String> viewBillingStatus(String emailAddress);

	//Filtering Features to be implemented in front end
	List<Movie> getAllMovies();

	//Call On Click of play - Set the return value i.e loginId to session to update EndTS appropriately
	MoviePlayLog updateMovieStartStatus(Integer movieId, String emailAddress);

	//fetch the loginId from the session and update the stop TS
	MoviePlayLog updateMovieStopStatus(Integer logId);

	//A customer can review a movie after he started playing a movie, no matter he finished playing or not.
	boolean checkPlayStatus(Integer logId, Integer movieId);

	//Save review
	Rating saveReview(Integer movieId, Integer logId, String emailAddress, String review, Double rating);

	List<MoviesByRatingComponent> getTopNMoviesByRatings();

	List<Rating> getMovieReviews(Integer movieId);

	Customer createCustomer(String emailAddress);
	boolean isCustomerSubscribed(String emailAddress);

	List<Integer> getPaidMoviesByUserName(String username);


}
