package edu.sjsu.entertainmentbox.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.sjsu.entertainmentbox.dao.CustomerDao;
import edu.sjsu.entertainmentbox.dao.UserDao;
import edu.sjsu.entertainmentbox.model.Customer;
import edu.sjsu.entertainmentbox.model.CustomerSubscription;
import edu.sjsu.entertainmentbox.model.Genre;
import edu.sjsu.entertainmentbox.model.MPAARating;
import edu.sjsu.entertainmentbox.model.Movie;
import edu.sjsu.entertainmentbox.model.MovieAvailability;
import edu.sjsu.entertainmentbox.model.MovieInformation;
import edu.sjsu.entertainmentbox.model.Rating;
import edu.sjsu.entertainmentbox.model.SubscriptionType;
import edu.sjsu.entertainmentbox.model.User;
import edu.sjsu.entertainmentbox.service.CustomerService;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private UserDao userDao;


	@Transactional
	public Customer getCustomer(String emailAddress) {
		return customerDao.getCustomer(emailAddress);
	}


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
		customerSubscription.setSubscriptionType(subscriptionType);
		customerSubscription.setMovie(movie);
		
		Customer customer = getCustomer(emailAddress);
		if (customer == null) {
			customer = new Customer();
			customer.setEmailAddress(emailAddress);
		}
		
		customerSubscription.setCustomer(customer);

		customerDao.saveSubscription(customerSubscription, customer);
	}


	private Date getEndDate(Calendar calendar, int noOfMonths) {
		calendar.add(Calendar.MONTH, noOfMonths);
		calendar.add(Calendar.HOUR_OF_DAY, 24);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}


	@Override
	public List<MovieInformation> searchMovie(String emailAddress, String keywords, String year, String actors, String director, String[] genres, String[] mpaaRatings, String numberOfStars) {
		List<Movie> allMovies = customerDao.searchMovie(keywords);
		keywords = keywords.toLowerCase().replaceAll(",", "").replaceAll("the", "").replaceAll("movie", "").replaceAll("  ", " ").trim();
		String[] keywordArray = new String[0];
		if (keywords != null && !keywords.equals("")) {
			keywordArray = keywords.toLowerCase().replaceAll(",", "").replaceAll("the", "").replaceAll("movie", "").replaceAll("  ", " ").trim().split(" ");
		}
		year = year.replaceAll("  ", " ").trim();
		int yearInt = 0;
		if ( year != null && !year.equals("")) yearInt = Integer.parseInt(year);
		String[] actorArray = null;
		actors = actors.toLowerCase().replaceAll(",", "").replaceAll("  ", " ").trim();
		if ( actors != null && !actors.equals("")) actorArray = actors.split(" ");
		director = director.toLowerCase().replaceAll(",", "").replaceAll("  ", " ").trim();
		
		List<Movie> filteredMoviesForKeywords = null;
		for (String keyword: keywordArray) {
			filteredMoviesForKeywords = new ArrayList<Movie>();
			for (Movie movie: allMovies) {
				if (movie.getActors().toLowerCase().contains(keyword) || movie.getDirector().toLowerCase().contains(keyword)
						|| movie.getSynopsis().toLowerCase().contains(keyword) || movie.getTitle().toLowerCase().contains(keyword)) {
					filteredMoviesForKeywords.add(movie);
				}
			}
		}
		List<Movie> filteredMoviesForYear = null;
		if (yearInt != 0) {
			filteredMoviesForYear = new ArrayList<Movie>();
			for (Movie movie: allMovies) {
				if (movie.getYear() == yearInt) {
					filteredMoviesForYear.add(movie);
				}
			}
		}
		List<Movie> filteredMoviesForActors = null;
		if (actorArray != null) {
			filteredMoviesForActors = new ArrayList<Movie>();
			for (String actor: actorArray) {
				for (Movie movie: allMovies) {
					if (movie.getActors().toLowerCase().contains(actor)) {
						filteredMoviesForActors.add(movie);
					}
				}
			}
		}
		List<Movie> filteredMoviesForDirector = null;
		if (director != null && !director.equals("")) {
			filteredMoviesForDirector = new ArrayList<Movie>();
			for (Movie movie: allMovies) {
				if (movie.getDirector().toLowerCase().contains(director)) {
					filteredMoviesForDirector.add(movie);
				}
			}
		}
		List<Movie> filteredMoviesForGenres = null;
		if (genres != null) {
			filteredMoviesForGenres = new ArrayList<Movie>();
			for (String genre: genres) {
				Genre gen = Genre.valueOf(genre.toUpperCase());
				for (Movie movie: allMovies) {
					if (movie.getGenre() == gen) {
						filteredMoviesForGenres.add(movie);
					}
				}
			}
		}
		List<Movie> filteredMoviesForMPAARatings = null;
		if (mpaaRatings != null) {
			filteredMoviesForMPAARatings = new ArrayList<Movie>();
			for (String mpaaRating: mpaaRatings) {
				MPAARating mpaa = MPAARating.valueOf(mpaaRating.toUpperCase());
				for (Movie movie: allMovies) {
					if (movie.getMpaaRating() == mpaa) {
						filteredMoviesForMPAARatings.add(movie);
					}
				}
			}
		}
		List<Movie> filteredMoviesForStars = null;
		if (numberOfStars!=null && !numberOfStars.equals("")) {
			int stars = Integer.parseInt(numberOfStars);
			List<Rating> ratings = customerDao.getRatings();
			filteredMoviesForStars = new ArrayList<Movie>();
			for (Movie movie: allMovies) {
				if (this.getAverageStars(movie, ratings) >= stars) {
					filteredMoviesForStars.add(movie);
				}
			}
		}

		List<Movie> filteredMovies = new ArrayList<Movie>();
		filteredMovies.addAll(allMovies);
		if ( !(filteredMoviesForKeywords == null && filteredMoviesForYear == null && filteredMoviesForActors == null
				&& filteredMoviesForDirector == null && filteredMoviesForGenres == null && filteredMoviesForMPAARatings == null && filteredMoviesForStars == null) ) {
			if (filteredMoviesForKeywords != null) filteredMovies.retainAll(filteredMoviesForKeywords);
			if (filteredMoviesForYear != null) filteredMovies.retainAll(filteredMoviesForYear);
			if (filteredMoviesForActors != null) filteredMovies.retainAll(filteredMoviesForActors);
			if (filteredMoviesForDirector != null) filteredMovies.retainAll(filteredMoviesForDirector);
			if (filteredMoviesForGenres != null) filteredMovies.retainAll(filteredMoviesForGenres);
			if (filteredMoviesForMPAARatings != null) filteredMovies.retainAll(filteredMoviesForMPAARatings);
			if (filteredMoviesForStars != null) filteredMovies.retainAll(filteredMoviesForStars);
		}
//		for(Movie movie: allMovies) {
//			if (movie.getTitle().toLowerCase().contains(keywords.toLowerCase())) {
//				filteredMovies.add(movie);
//			}
//		}
		List<CustomerSubscription> customerSubscriptions = this.getAllCustomerSubscriptions();
		boolean isCustomer=checkCustomer(emailAddress, customerSubscriptions);
		List<MovieInformation> movieInfo= new ArrayList <MovieInformation>();
		for (Movie m:filteredMovies) {
			int id = m.getMovieId();
			String title=m.getTitle();
			String link=m.getMovie();
			String disabled="";
			String note = getNote(m.getAvailability());
			String enable = "";
			boolean isMovieCustomer = checkMovieCustomer(emailAddress, m, customerSubscriptions);
			if (m.getAvailability() == MovieAvailability.FREE) {
				disabled="";
				enable = "display:none;";
			} else if (m.getAvailability()==MovieAvailability.SUBSCRIPTION_ONLY && !isCustomer) {
				disabled="pointer-events: none;";
				enable = "display:none;";
			} else if (m.getAvailability()==MovieAvailability.SUBSCRIPTION_ONLY && isCustomer) {
				disabled="";
				enable = "display:none;";
			} else if (m.getAvailability()==MovieAvailability.PAY_PER_VIEW_ONLY && !isMovieCustomer) {
				disabled="pointer-events: none;";
				enable = "";
			} else if (m.getAvailability()==MovieAvailability.PAY_PER_VIEW_ONLY && isMovieCustomer) {
				disabled="";
				enable = "display:none;";
			} else if(m.getAvailability()==MovieAvailability.PAID && !isCustomer) {
				disabled="pointer-events: none;";
				enable = "";
			} else if (m.getAvailability()==MovieAvailability.PAID && isCustomer) {
				disabled="";
				enable = "display:none;";
			}
			if (emailAddress.endsWith("sjsu.edu")) {
				disabled="";
				enable = "display:none;";
			}
			MovieInformation mInfo=new MovieInformation(id,title,link,disabled, note, enable);
			movieInfo.add(mInfo);
		}
		return movieInfo;
	}


	@Override
	public List<CustomerSubscription> getAllCustomerSubscriptions() {
		return customerDao.getAllSubscriptions();
	}

	@Override
	public boolean checkCustomer(String emailAddress, List<CustomerSubscription> customerSubscriptions) {
		boolean isCustomer = false;
		for (CustomerSubscription customerSubscription: customerSubscriptions) {
			Customer customer = customerSubscription.getCustomer();
			Date now = new Date();
			if(customer!=null && customer.getEmailAddress() != null && customer.getEmailAddress().equals(emailAddress)
					&& now.compareTo(customerSubscription.getSubscriptionEndDate()) < 0 && customerSubscription.getMovie()==null) {
				isCustomer = true;
				break;
			}
		}
		return isCustomer;
	}

	private boolean checkMovieCustomer(String emailAddress, Movie m, List<CustomerSubscription> customerSubscriptions) {
		boolean isMovieCustomer = false;
		for (CustomerSubscription customerSubscription: customerSubscriptions) {
			Customer customer = customerSubscription.getCustomer();
			Movie movie = customerSubscription.getMovie();
			Date now = new Date();
			if(customer!=null && customer.getEmailAddress() != null && customer.getEmailAddress().equals(emailAddress)
					&& now.compareTo(customerSubscription.getSubscriptionEndDate()) < 0 && movie!=null && movie.getMovieId()==m.getMovieId()) {
				isMovieCustomer = true;
				break;
			}
		}
		return isMovieCustomer;
	}

	private String getNote(MovieAvailability availability) {
		if (availability == MovieAvailability.FREE) return "Free to All";
		if(availability == MovieAvailability.PAID) return "Paid Movie, free for subscribers";
		if (availability == MovieAvailability.PAY_PER_VIEW_ONLY) return "Pay per view, subscribers pay half the price";
		return "Available to subscribers only";
	}


	@Override
	public void submitRating(int movieId, String emailAddress, int stars) throws ParseException {
		TimeZone.setDefault(TimeZone.getTimeZone("PST"));
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		String stDate = format.format( calendar.getTime() );
		Date timestamp = format.parse(stDate);
		Movie movie = customerDao.getMovie(movieId);
		User user = userDao.getUser(emailAddress);
		Rating rating = new Rating(movie, user, timestamp, stars);
		customerDao.submitRating(rating);
	}


	@Override
	public double getAverageStars(Movie movie, List<Rating> ratings) {
		int count = 0;
		double totalRating = 0;
		for (Rating rating: ratings) {
			if (rating.getMovie().getMovieId() == movie.getMovieId()) {
				totalRating += rating.getStars();
				count++;
			}
		}
		return totalRating/count;
	}

	@Override
	public Movie getMovie(int movieId) {
		return customerDao.getMovie(movieId);
	}

	@Override
	public List<MovieInformation> getHighlyRatedMovies(String emailAddress, int days) {
		List<Rating> ratings = customerDao.getRatings();
		TreeMap<Double, List<Integer>> ratingsAndMovies = new TreeMap<Double, List<Integer>>();
		Map<Integer, List<Integer>> movieIndividualRatings = new HashMap<Integer, List<Integer>>();
		Map<Integer, Double> movieAverageRatings = new HashMap<Integer, Double>();
		Calendar cal = Calendar.getInstance();
		for (Rating rating: ratings) {
			Date dateSubmitted = rating.getTimestamp();
			if(cal.getTime().getTime() - dateSubmitted.getTime() > days*24*60*60*1000) continue;
			int movieId = rating.getMovie().getMovieId();
			int stars = rating.getStars();
			if (movieIndividualRatings.containsKey(movieId)) {
				List<Integer> movieRatings = movieIndividualRatings.get(movieId);
				movieRatings.add(stars);
				movieIndividualRatings.put(movieId, movieRatings);
			} else {
				List<Integer> movieRatings = new ArrayList<Integer>();
				movieRatings.add(stars);
				movieIndividualRatings.put(movieId, movieRatings);
			}
		}
		for (int movieId: movieIndividualRatings.keySet()) {
			List<Integer> individualRatings = movieIndividualRatings.get(movieId);
			double d = 0;
			for (int i: individualRatings) {
				d+=i;
			}
			d = d/individualRatings.size();
			movieAverageRatings.put(movieId, d);
		}
		for (int movieId: movieAverageRatings.keySet()) {
			double avgRating = movieAverageRatings.get(movieId);
			if (ratingsAndMovies.containsKey(avgRating)) {
				List<Integer> movieIds = ratingsAndMovies.get(avgRating);
				movieIds.add(movieId);
				ratingsAndMovies.put(avgRating, movieIds);
			} else {
				List<Integer> movieIds = new ArrayList<Integer>();
				movieIds.add(movieId);
				ratingsAndMovies.put(avgRating, movieIds);
			}
		}
		List<CustomerSubscription> customerSubscriptions = this.getAllCustomerSubscriptions();
		boolean isCustomer=checkCustomer(emailAddress, customerSubscriptions);
		List<MovieInformation> movieInfo = new ArrayList<MovieInformation>();
		int count = 0;
		for (double d: ratingsAndMovies.keySet()) {
			List<Integer> movieIds = ratingsAndMovies.get(d);
			for (int movieId: movieIds) {
				Movie m = this.getMovie(movieId);
				int id = m.getMovieId();
				String title=m.getTitle();
				String link=m.getMovie();
				String disabled="";
				String note = getNote(m.getAvailability());
				String enable = "";
				boolean isMovieCustomer = checkMovieCustomer(emailAddress, m, customerSubscriptions);
				if (m.getAvailability() == MovieAvailability.FREE) {
					disabled="";
					enable = "display:none;";
				} else if (m.getAvailability()==MovieAvailability.SUBSCRIPTION_ONLY && !isCustomer) {
					disabled="pointer-events: none;";
					enable = "display:none;";
				} else if (m.getAvailability()==MovieAvailability.SUBSCRIPTION_ONLY && isCustomer) {
					disabled="";
					enable = "display:none;";
				} else if (m.getAvailability()==MovieAvailability.PAY_PER_VIEW_ONLY && !isMovieCustomer) {
					disabled="pointer-events: none;";
					enable = "";
				} else if (m.getAvailability()==MovieAvailability.PAY_PER_VIEW_ONLY && isMovieCustomer) {
					disabled="";
					enable = "display:none;";
				} else if(m.getAvailability()==MovieAvailability.PAID && !isCustomer) {
					disabled="pointer-events: none;";
					enable = "";
				} else if (m.getAvailability()==MovieAvailability.PAID && isCustomer) {
					disabled="";
					enable = "display:none;";
				}
				if (emailAddress.endsWith("sjsu.edu")) {
					disabled="";
					enable = "display:none;";
				}
				MovieInformation mInfo=new MovieInformation(id,title,link,disabled, note, enable);
				mInfo.setStars(d);
				movieInfo.add(0, mInfo);

				count++;
				if (count>=10) break;
			}
			if (count>=10) break;
		}
		return movieInfo;
	}

}
