package edu.sjsu.entertainmentbox.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import edu.sjsu.entertainmentbox.EntertainmentBoxApplication;
import edu.sjsu.entertainmentbox.model.Customer;
import edu.sjsu.entertainmentbox.model.CustomerSubscription;
import edu.sjsu.entertainmentbox.model.Movie;
import edu.sjsu.entertainmentbox.model.MovieAvailability;
import edu.sjsu.entertainmentbox.model.MovieInformation;
import edu.sjsu.entertainmentbox.model.SubscriptionType;
import edu.sjsu.entertainmentbox.service.CustomerService;
import edu.sjsu.entertainmentbox.service.UserService;

@Controller
public class CustomerController {
	private static final Logger logger = LoggerFactory.getLogger(EntertainmentBoxApplication.class);
	@Autowired
	private CustomerService customerService;
	@Autowired
	private UserService userService;

	@RequestMapping(value="/user/startSubscription", method={RequestMethod.POST})
	public ModelAndView startSubscription(HttpServletRequest request) {
		ModelAndView mv= new ModelAndView("startSubscription");
		try {
			int price2=0;
			int noOfMonth =0;
			int cost =0;
			String noOfMonths = request.getParameter("noOfMonths");
			if (noOfMonths == null || noOfMonths.equals("")) {
				mv= new ModelAndView("customer");
				mv.addObject("subscriptionErrMsg", "Please enter number of months");
				return mv;
			}
			String price = request.getParameter("price");
			String emailAddress = request.getUserPrincipal().getName();
			if(price!=null && noOfMonths!=null) {
				 price2 = Integer.parseInt(price);
				 noOfMonth = Integer.parseInt(noOfMonths);
				 cost =noOfMonth* price2;
			}else {
				 price2=10;
				 noOfMonth =1;
				 cost =10;
			}
			mv.addObject("emailAddress", emailAddress);
			mv.addObject("cost", cost);
			mv.addObject("noOfMonth", noOfMonth);
			mv.addObject("subscriptionType", SubscriptionType.SUBSCRIPTION_ONLY);
			mv.addObject("movie", null);
		} catch (Exception e) {
			mv.addObject("subscriptionErrMsg", "Error Occured. Make sure you have entered Number of months");
		}
		return mv;
	}
	
	@RequestMapping(value="/user/subscribe", method= {RequestMethod.POST})
	public ModelAndView subscribe(HttpServletRequest request) throws ParseException {
		ModelAndView mv= new ModelAndView("subscribe");
		int noOfMonth = Integer.parseInt( request.getParameter("noOfMonth") );
		String subscriptionType = request.getParameter("subscriptionType");
		String movie = request.getParameter("movie");
		int cost = Integer.parseInt(request.getParameter("cost"));
		String emailAddress = request.getUserPrincipal().getName();
		Movie mve = null;
		if(movie!=null && !movie.equals("")) {
			int movieId = Integer.parseInt(movie);
			mve = customerService.getMovie(movieId);
		}
		customerService.saveSubscription(emailAddress,cost,noOfMonth, SubscriptionType.valueOf(subscriptionType), mve);
		String msg = "Thank you for subscribing!";
		mv.addObject("subscriptionMsg", msg);
		return mv;
	}
	
	@RequestMapping(value="/user/submitRow", method= {RequestMethod.POST})
	public ModelAndView submitRating(HttpServletRequest request) throws ParseException {
		if (request.getParameter("action").equals("Rate")) {
			ModelAndView mv= new ModelAndView("successRatingSubmit");
			int stars = Integer.parseInt(request.getParameter("submitStars"));
			int movieId = Integer.parseInt(request.getParameter("movieId"));
			String emailAddress = request.getUserPrincipal().getName();
			customerService.submitRating(movieId, emailAddress, stars);
			return mv;
		} else {
			int movieId = Integer.parseInt(request.getParameter("movieId"));
			String emailAddress = request.getUserPrincipal().getName();
			List<CustomerSubscription> customerSubscriptions = customerService.getAllCustomerSubscriptions();
			boolean isCustomer = customerService.checkCustomer(emailAddress, customerSubscriptions);
			int cost = 10;
			if (isCustomer) {
				cost = 5;
			}
			ModelAndView mv= new ModelAndView("startSubscription");
			mv.addObject("emailAddress", emailAddress);
			mv.addObject("cost", cost);
			mv.addObject("noOfMonth", "N/A");
			mv.addObject("subscriptionType", SubscriptionType.PAY_PER_VIEW_ONLY);
			mv.addObject("noOfMonth", 1);
			mv.addObject("movie", movieId);
			return mv;
		}
	}

	@RequestMapping(value="/user/searchMovie", method={RequestMethod.POST})
	public ModelAndView searchMovie(HttpServletRequest request) {
		String emailAddress = request.getUserPrincipal().getName();
		String keywords = request.getParameter("keywords");
		String year = request.getParameter("year");
		String actors = request.getParameter("actors");
		String director = request.getParameter("director");
		String[] genres = request.getParameterValues("genre");
		String[] mpaaRatings = request.getParameterValues("mpaaRating");
		String numberOfStars = request.getParameter("numberOfStars");
		ModelAndView mv= new ModelAndView("customer");
		List<MovieInformation> movieInfo = customerService.searchMovie(emailAddress, keywords, year, actors, director, genres, mpaaRatings, numberOfStars);
		mv.addObject("movieInformationList", movieInfo);
		return mv;
	}

	@RequestMapping(value="/user/watchMovie", method={RequestMethod.GET})
	public ModelAndView watchMovie(HttpServletRequest request) throws ParseException {
		ModelAndView mv = new ModelAndView("log");
		String emailAddress = request.getUserPrincipal().getName();
		String mve = request.getParameter("movieId");
		int movieId = Integer.parseInt(mve);
		Movie movie = customerService.getMovie(movieId);
		mv.addObject("movieId", movie.getMovieId());
		mv.addObject("title", movie.getTitle());
		mv.addObject("year", movie.getYear());
		mv.addObject("actors", movie.getActors());
		mv.addObject("director", movie.getDirector());
		mv.addObject("studio", movie.getStudio());
		mv.addObject("synopsis", movie.getSynopsis());
		mv.addObject("country", movie.getCountry());
		mv.addObject("image", movie.getImage());
		mv.addObject("movie", movie.getMovie());
		mv.addObject("genre", movie.getGenre());
		mv.addObject("mpaaRating", movie.getMpaaRating());
		mv.addObject("availability", movie.getAvailability());
		mv.addObject("editMovieDivStyle", "");
		userService.saveLog(emailAddress, movie);
		mv.addObject("movieLink", movie.getMovie());
		return mv;
	}

	@RequestMapping(value="/user/log", method={RequestMethod.GET})
	public ModelAndView log(HttpServletRequest request) throws ParseException {
		ModelAndView mv = new ModelAndView("log");
		String emailAddress = request.getUserPrincipal().getName();
		String movie = request.getParameter("movieId");
		int movieId = Integer.parseInt(movie);
		Movie mve = customerService.getMovie(movieId);
		userService.saveLog(emailAddress, mve);
		mv.addObject("movieLink", mve.getMovie());
		return mv;
	}

	@RequestMapping(value="/user/movieScoreboards", method={RequestMethod.GET})
	public ModelAndView movieScoreboards(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("movieScoreboards");
		return mv;
	}

	@RequestMapping(value="/user/viewHighlyRatedMovieCustomer", method= {RequestMethod.POST})
	public ModelAndView viewHighlyRatedMovieCustomer(HttpServletRequest request) {
		String emailAddress = request.getUserPrincipal().getName();
		int days = Integer.parseInt(request.getParameter("duration"));
		ModelAndView mv= new ModelAndView("movieScoreboards");
		List<MovieInformation> movieInfo = customerService.getHighlyRatedMovies(emailAddress, days);
		mv.addObject("movieInformationList", movieInfo);
		return mv;
	}
}
