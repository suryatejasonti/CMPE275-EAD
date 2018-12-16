package edu.sjsu.entertainmentbox.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import edu.sjsu.entertainmentbox.component.PaidMoviesComponent;
import edu.sjsu.entertainmentbox.dao.MovieSearchDao;
import edu.sjsu.entertainmentbox.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import edu.sjsu.entertainmentbox.EntertainmentBoxApplication;
import edu.sjsu.entertainmentbox.service.CustomerService;

@Controller
public class CustomerController {
	private static final Logger logger = LoggerFactory.getLogger(EntertainmentBoxApplication.class);
	@Autowired
	private CustomerService customerService;
	@Autowired
	private MovieSearchDao moviesearch;
	
	@RequestMapping(value="/user/subscribe", method= {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView subscribe(ModelMap model,@RequestParam(value="emailaddress", required=false) String emailAddress,@RequestParam(value="noOfMonths", required=false) String noOfMonths, @RequestParam(value="price", required=false) String price) throws ParseException {
		logger.info("*******************inside CustomerController:subscribe method");
		logger.info("*********price*****" + price + "***************8");
		logger.info("***********noOfMonths********" + noOfMonths + "*********noOfMonths*************");
		ModelAndView mv= new ModelAndView("subscribe");
		int price2=0;
		int noOfMonth =0;
		int cost =0;
		if(price!=null && noOfMonths!=null) {
			 price2 = Integer.parseInt(price);
			 noOfMonth = Integer.parseInt(noOfMonths);
			 cost =noOfMonth* price2;
		}else {
			 price2=10;
			 noOfMonth =1;
			 cost =10;
		}
	  
		customerService.saveSubscription(emailAddress,cost,noOfMonth, SubscriptionType.SUBSCRIPTION_ONLY, null);
		
		return mv;
	}

	@RequestMapping(value="/user/searchMovie", method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView searchMovie(ModelMap model,@RequestParam(value="searchText", required=false) String searchText, HttpServletRequest request) {
		ModelAndView mv= new ModelAndView("customer");
		List<Movie> movies = customerService.searchMovie(searchText);
		String emailAddress = request.getUserPrincipal().getName();
		boolean isCustomer=checkCustomer(emailAddress);
		List<MovieInformation> movieInfo= new ArrayList <MovieInformation>();
		for (Movie m:movies) {
			String title=m.getTitle();
			String link=m.getMovieurl();
			String disabled="";
			logger.info("************movie availaibility******* "+m.getAvailability()+"*******************************");
			if(!isCustomer && m.getAvailability()==MovieAvailability.PAID) {
				link="";
			}
			MovieInformation mInfo=new MovieInformation(title,link,disabled);
			movieInfo.add(mInfo);
		}
		logger.info("***************************************************");
		logger.info(Arrays.toString(movies.toArray()));
		logger.info("***************************************************");
	//	mv.addObject("movieList", movies);
		mv.addObject("movieInformationList", movieInfo);
		return mv;
	}
	
	
	public boolean checkCustomer(String emailAddress) {
		logger.info("*********************CheckCustomer"+emailAddress+"******************************");

		if(customerService.getCustomer(emailAddress)!=null) {
			return true;
		}else {
			return false;
		}	
 
	}

	/********REST API SERVICES*********/
	@ResponseBody
	@RequestMapping(value="/eb/user/subscribe", method= {RequestMethod.POST,RequestMethod.GET})
	public ResponseEntity<String> ebSubscribe(
			@RequestParam(value="username", required=false) String username,
			@RequestParam(value="noOfMonths", required=false) String noOfMonths,
			@RequestParam(value="subscriptionType", required=false) String subscriptionType,
			@RequestParam(value="price", required=false) String price,
			@RequestParam(value="movieId", required=false) Integer movieId,
			HttpSession session,
			WebRequest request
	) {
		CustomerSubscription customerSubscription = null;
		System.out.println("username:::"+username);
		System.out.println("SubscriptionType:::"+SubscriptionType.valueOf(subscriptionType));
		if(SubscriptionType.valueOf(subscriptionType)!=null)
			customerSubscription= customerService.startSubscription(username,Integer.parseInt(noOfMonths) , SubscriptionType.valueOf(subscriptionType),Integer.parseInt(price),(movieId));
		else
			return new ResponseEntity<>("INVALID SUBSCRIPTION TYPE", HttpStatus.OK);

		if(customerSubscription!=null)
			return new ResponseEntity<>("SUCCESS", HttpStatus.OK);

		return new ResponseEntity<>("FAILURE", HttpStatus.OK);
	}


	@ResponseBody
	@RequestMapping(path = "/movies/isSubscribed" , method= {RequestMethod.POST,RequestMethod.GET})
	public ResponseEntity<String> isCustomerSubscribed(
			@RequestParam(value="username", required=false) String username,
			HttpSession session,
			WebRequest request){

		if(customerService.isCustomerSubscribed(username))
		{
			return new ResponseEntity("YES",HttpStatus.OK);
		}
		return new ResponseEntity("NO",HttpStatus.OK);

	}

	@ResponseBody
	@RequestMapping(path = "/movies/customerPaidMovies" , method= {RequestMethod.POST,RequestMethod.GET})
	public ResponseEntity<?> isCustomerSubscribed(
			@RequestParam(value="username", required=false) String username,
			WebRequest request){
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		if(customerService.getPaidMoviesByUserName(username)!=null)
		{
			try {
				return new ResponseEntity(new ObjectMapper().writeValueAsString(customerService.getPaidMoviesByUserName(username)),HttpStatus.OK);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return new ResponseEntity(null,HttpStatus.OK);

	}

	@ResponseBody
	@RequestMapping(value="/eb/user/moviesearch", method= {RequestMethod.POST})
	public ResponseEntity<?> ebSubscribe(
			@RequestParam(value="searchtext", required=false) String searchtext,
			HttpSession session,
			WebRequest request
	) {
		List<Movie> results = moviesearch.searchMovieByKeywordQuery(searchtext);
		if(results!=null)
			return new ResponseEntity<>(results, HttpStatus.OK);
		return new ResponseEntity<>("FAILURE", HttpStatus.OK);
	}
}
