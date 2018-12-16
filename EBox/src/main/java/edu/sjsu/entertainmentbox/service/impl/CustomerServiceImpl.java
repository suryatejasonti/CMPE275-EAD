package edu.sjsu.entertainmentbox.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import edu.sjsu.entertainmentbox.component.MoviesByRatingComponent;
import edu.sjsu.entertainmentbox.component.PaidMoviesComponent;
import edu.sjsu.entertainmentbox.dao.*;
import edu.sjsu.entertainmentbox.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.sjsu.entertainmentbox.service.CustomerService;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;


@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerDao customerDao;

	/* Start - Changes By Srivatsa Mulpuri*/
	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	CustomerSubscriptionRepository customerSubscriptionRepository;
	@Autowired
	TransactionRepository transactionRepository;
	@Autowired
	MoviePlayLogRepository moviePlayLogRepository;
	@Autowired
	RatingRepository ratingRepository;
	@Autowired
	MovieRepository movieRepository;
	/* End - Changes By Srivatsa Mulpuri*/
	@Transactional
	public Customer getCustomer(String emailAddress) {
		return customerDao.getCustomer(emailAddress);
	}

	//By Premal
	@Transactional
	public void saveSubscription(String emailAddress, int price, int noOfMonths, SubscriptionType subscriptionType, Movie movie) throws ParseException {
		CustomerSubscription customerSubscription = new CustomerSubscription();
		customerSubscription.setPrice(price);
		TimeZone.setDefault(TimeZone.getTimeZone("PST"));
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		String stDate = format.format( calendar.getTime() );
		Date startDate = format.parse(stDate);
		Date endDate = getEndDate(calendar, noOfMonths);
		customerSubscription.setSubscriptionStartDate(startDate);
		customerSubscription.setSubscriptionEndDate(endDate);
		customerSubscription.setSubscriptionTS(startDate);
		customerSubscription.setSubscriptionType(SubscriptionType.SUBSCRIPTION_ONLY);
		customerSubscription.setMovie(null);
		System.out.println("email::::;"+emailAddress);
		Customer customer = getCustomer(emailAddress);
		if (customer == null) {
			customer = new Customer();
			customer.setEmailAddress(emailAddress);
		}
		
		customerSubscription.setCustomer(customer);

		customerDao.saveSubscription(customerSubscription, customer);
	}

	//By Premal
	private Date getEndDate(Calendar calendar, int noOfMonths) {
		calendar.add(Calendar.MONTH, noOfMonths);
		calendar.add(Calendar.HOUR_OF_DAY, 24);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	//By premal
	@Override
	public List<Movie> searchMovie(String searchText) {
		List<Movie> result = new ArrayList<Movie>();
		Movie allMovies = customerDao.searchMovie(searchText);
		/*for(Movie movie: allMovies) {
			if (movie.getTitle().toLowerCase().contains(searchText.toLowerCase())) {
				result.add(movie);
			}
		}*/
		result.add(allMovies);
		return result;
	}



	/************************************** Changes for REST API - Srivatsa Mulpuri **********************************************************/

	//The subscription can start at any day, and the subscription fee for the current month ends at 12 am the same day next month.
	// If next month does not have the same day, then it ends at the last day of next month.
	// For example, if you start your subscription on Jan 30 and only paid $10 monthly fee, the fee is good until the last day of February,
	// and you must pay/renew your subscription by 12 AM March 1st.

	//Pending -- 12am is not handled yet
	@Override
	public CustomerSubscription startSubscription(String emailAddress, Integer noOfMonths, SubscriptionType subscriptionType, Integer price, Integer movieId) {

		CustomerSubscription subscription = new CustomerSubscription();

		Date currentDate = new Date();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0); //anything 0 - 23
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.add(Calendar.MONTH, noOfMonths);
		Date subscriptionEndDate = cal.getTime();


		Optional<Customer> customer = customerRepository.findByEmailAddress(emailAddress);

		CustomerSubscription customerSubscription = new CustomerSubscription(subscriptionType, "ACTIVE", noOfMonths*price, currentDate, currentDate,subscriptionEndDate);
        if(subscriptionType == SubscriptionType.PAY_PER_VIEW_ONLY)
        {
            Optional<Movie> movie = movieRepository.findByMovieId(movieId);
            if(movie.isPresent())
            {
                customerSubscription.setMovie(movie.get());
            }
        }
        else
		{
			Optional<Movie> movie = movieRepository.findByTitle("SUBSCRIPTION_MAPPING");
			if(movie.isPresent())
			{
				customerSubscription.setMovie(movie.get());
			}
		}

		if(customer.isPresent())
		{

		    System.out.println("**************SAVING CUSTOMERS****************");
			customerSubscription.setCustomer(customer.get());
			subscription = customerSubscriptionRepository.save(customerSubscription);

			//Save Transaction
			Transaction transaction = new Transaction(MovieAvailability.valueOf(subscriptionType.toString()) , noOfMonths*price, currentDate, subscriptionEndDate, "COMPLETED");
			transaction.setCustomer(customer.get());
			transaction.setSubscription(subscription);
			transactionRepository.save(transaction);
		}
		else
		{

		}

		return subscription;
	}



	//View billing status: a subscription user must be able to find out when his subscription is up for renewal.
	@Override
	public List<String> viewBillingStatus(String emailAddress) {

		String billingStatus = null;
		List<String> billingDetailsList= new ArrayList<>();

		Optional<Customer> customer = customerRepository.findById(emailAddress);
		if(customer.isPresent())
		{
			List<CustomerSubscription> customerSubscriptions = new ArrayList<CustomerSubscription>();//= customerSubscriptionRepository.findByCustomerCustomerIdAndSubscriptionStatus( customerId,"ACTIVE");
           /* Set<CustomerSubscription> subscriptions = customer.get().getSubscription();
            for (CustomerSubscription customerSubscription:subscriptions) {
                if(customerSubscription.getSubscriptionStatus().equalsIgnoreCase("SUBSCRIPTION"))
                {
                    billingStatus = customerSubscription.getSubscriptionEndDate().toString();
                }
            }*/
			billingDetailsList.add("SUBSCRIPTION TYPE ---------- SUBSCRIPTION EXPIRY");
			for (CustomerSubscription subscription:customerSubscriptions ) {
				billingDetailsList.add(subscription.getSubscriptionType()+" ---------- "+subscription.getSubscriptionEndDate());
			}
		}
		else
		{
			billingStatus = "Customer Not Registered";
		}
		return billingDetailsList;
	}

	//Filtering Features to be implemented in front end
	@Override
	public List<Movie> getAllMovies() {

		return null;
	}

	//Call On Click of play - Set the return value i.e logId to session to update EndTS appropriately
	@Override
	public MoviePlayLog updateMovieStartStatus(Integer movieId, String emailAddress) {

		MoviePlayLog moviePlayLog = null;
		MoviePlayLog playLog = null;
		Optional<Customer> customer = customerRepository.findByEmailAddress(emailAddress);
		//System.out.println("Before updated customer!!"+customer.get().getMoviePlayLogs().size());
		if(customer.isPresent())
		{
			Optional<Movie> movie = movieRepository.findByMovieId(movieId);
			if(movie.isPresent())
			{
				moviePlayLog = new MoviePlayLog("START", new Date(), null, movie.get());
				moviePlayLog.setCustomer(customer.get());
				System.out.println("/************SAVING MOVIE**************/");
				playLog=moviePlayLogRepository.save(moviePlayLog);
				if(playLog!=null)
				{
					return playLog;
				}
			}
		}
		else{
			System.out.println("/************CUSTOMER NOT FOUND**************/");
		}




		return playLog;
	}

	//fetch the loginId from the session and update the stop TS
	@Override
	public MoviePlayLog updateMovieStopStatus(Integer logId) {
		MoviePlayLog moviePlayLog = null;
		Optional<MoviePlayLog> playLog = moviePlayLogRepository.findByLogId(logId);
		if(playLog.isPresent())
		{
			playLog.get().setMveEndTS(new Date());
			moviePlayLog = moviePlayLogRepository.save(playLog.get());
			if(moviePlayLog!=null)
				return moviePlayLog;
		}
		else
		{
			System.out.println("/*************INVALID LOG ID******************/");
		}

		return moviePlayLog;
	}

	//A customer can review a movie after he started playing a movie, no matter he finished playing or not.
	@Override
	public boolean checkPlayStatus(Integer logId, Integer movieId) {
		boolean isStarted = false;
		Optional<MoviePlayLog> moviePlayLog = moviePlayLogRepository.findByLogIdAndMovieMovieId(logId, movieId);
		if(moviePlayLog.isPresent())
		{
			isStarted = true;
		}

		return isStarted;
	}

	@Override
	public Rating saveReview(Integer movieId, Integer logId, String emailAddress, String review, Double rating) {

		Rating savedRating = null;
		Optional<Customer> customer = customerRepository.findByEmailAddress(emailAddress);
		Rating movieRating = null;
		if(customer.isPresent())
		{
			if(checkPlayStatus(logId, movieId))
			{
				Optional<Movie> movie = movieRepository.findByMovieId(movieId);
				if(movie.isPresent())
				{
					movieRating = new Rating(customer.get(),movie.get(),rating,new Date(),review);
					savedRating = ratingRepository.save(movieRating);
					if(savedRating!=null)
						return savedRating;
				}
			}
			else
			{
				System.out.println("************Invalid Log Id and Movie Id Combination*******************");
			}
		}

		return savedRating;

	}

	//Save review
	//Pending Movie names
	@Override
	public List<MoviesByRatingComponent> getTopNMoviesByRatings() {

		java.util.Date date= new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int month = cal.get(Calendar.MONTH);

		List<MoviesByRatingComponent> ratings = ratingRepository.findByRating(month-1);
		return ratings;
	}


	public Customer createCustomer(String emailId)
	{
		Customer customer = new Customer();
		customer.setEmailAddress(emailId);
		return customerRepository.save(customer);
	}

	@Override
	public List<Rating> getMovieReviews(Integer movieId)
	{
		List<Rating> ratingsList = new ArrayList<>();
		Optional<Movie> movie = movieRepository.findById(movieId);

		if(movie.isPresent())
		{
			Optional<List<Rating>> ratings = ratingRepository.findRatingByMovieMovieId(movieId);
			if(ratings.isPresent())
			{
				ratingsList = ratings.get();
			}
		}


		return ratingsList;
	}


	public boolean isCustomerSubscribed(String emailAddress)
	{
		CustomerSubscription customerSubscription = customerSubscriptionRepository.findBySubscriptionTypeAndCustomerEmailAddress(SubscriptionType.SUBSCRIPTION_ONLY,emailAddress);
		if(customerSubscription!=null)
		{
			return true;
		}

		return false;
	}


	public List<Integer> getPaidMoviesByUserName(String username)
	{
		List<Integer> movies = new ArrayList<>(0);
		Set<CustomerSubscription> customer = customerSubscriptionRepository.findByCustomerEmailAddressAndSubscriptionType(username, SubscriptionType.PAY_PER_VIEW_ONLY);
		for (CustomerSubscription customerSubscription:customer) {
			Movie movie = customerSubscription.getMovie();
			System.out.println("PayPerView:::"+ movie);
			movies.add(movie.getMovieId());
		}



		return movies;//customerRepository.findByEmailAddressAndSubscriptionSubscriptionType(username, SubscriptionType.PAY_PER_VIEW_ONLY);
	}




}
