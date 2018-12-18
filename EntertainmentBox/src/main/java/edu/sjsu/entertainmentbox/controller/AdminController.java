package edu.sjsu.entertainmentbox.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import edu.sjsu.entertainmentbox.model.Genre;
import edu.sjsu.entertainmentbox.model.MPAARating;
import edu.sjsu.entertainmentbox.model.Movie;
import edu.sjsu.entertainmentbox.model.MovieAvailability;
import edu.sjsu.entertainmentbox.model.MovieInformation;
import edu.sjsu.entertainmentbox.service.AdminService;
import edu.sjsu.entertainmentbox.service.CustomerService;

@Controller
public class AdminController {
	@Autowired
	private AdminService adminService;
	@Autowired
	private CustomerService customerService;

	@RequestMapping(value="/user/addMovie", method={RequestMethod.POST})
	public ModelAndView addMovie(HttpServletRequest request) {
		String title = request.getParameter("title");
		Genre genre = Genre.valueOf(request.getParameter("genre"));
		int year = Integer.parseInt(request.getParameter("year"));
		String actors = request.getParameter("actors");
		String director = request.getParameter("director");
		String studio = request.getParameter("studio");
		String synopsis = request.getParameter("synopsis");
		String image = request.getParameter("image");
		String movieLink = request.getParameter("movie");
		String country = request.getParameter("country");
		MPAARating mpaaRating = MPAARating.valueOf(request.getParameter("mpaaRating"));
		MovieAvailability availability = MovieAvailability.valueOf(request.getParameter("availability"));
		Movie movie = new Movie(title, genre, year, studio, synopsis, image, movieLink, actors, director, country, mpaaRating, availability, 10);
		adminService.addMovie(movie);
		ModelAndView mv = new ModelAndView("admin");
		mv.addObject("addMovieMsg", "Movie added successfully");
		return mv;
	}

	@RequestMapping(value="/user/searchMovieForAdmin", method={RequestMethod.POST})
	public ModelAndView searchMovie(HttpServletRequest request) {
		String emailAddress = request.getUserPrincipal().getName();
		String keywords = request.getParameter("keywords");
		String year = request.getParameter("year");
		String actors = request.getParameter("actors");
		String director = request.getParameter("director");
		String[] genres = request.getParameterValues("genre");
		String[] mpaaRatings = request.getParameterValues("mpaaRating");
		String numberOfStars = request.getParameter("numberOfStars");
		ModelAndView mv= new ModelAndView("admin");
		List<MovieInformation> movieInfo = customerService.searchMovie(emailAddress, keywords, year, actors, director, genres, mpaaRatings, numberOfStars);
		mv.addObject("movieInformationList", movieInfo);
		mv.addObject("editMovieDivStyle", "visibility: hidden");
		return mv;
	}

	@RequestMapping(value="/user/searchMovieForEdit", method={RequestMethod.POST})
	public ModelAndView searchMovieForEdit(HttpServletRequest request) {
		String emailAddress = request.getUserPrincipal().getName();
		String keywords = request.getParameter("keywords");
		String year = request.getParameter("year");
		String actors = request.getParameter("actors");
		String director = request.getParameter("director");
		String[] genres = request.getParameterValues("genre");
		String[] mpaaRatings = request.getParameterValues("mpaaRating");
		String numberOfStars = request.getParameter("numberOfStars");
		ModelAndView mv= new ModelAndView("editMovie");
		List<MovieInformation> movieInfo = customerService.searchMovie(emailAddress, keywords, year, actors, director, genres, mpaaRatings, numberOfStars);
		mv.addObject("movieInformationList", movieInfo);
		mv.addObject("editMovieDivStyle", "visibility: hidden");
		return mv;
	}

	@RequestMapping(value="/user/editMovie", method={RequestMethod.GET})
	public ModelAndView goToEditPage(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("editMovie");
		mv.addObject("editMovieDivStyle", "visibility: hidden");
		return mv;
	}

	@RequestMapping(value="/user/chooseMovieToEditOrDelete", method={RequestMethod.POST})
	public ModelAndView chooseMovieToEdit(HttpServletRequest request) {
		int movieId = Integer.parseInt(request.getParameter("movieId"));
		Movie movie = customerService.getMovie(movieId);
		ModelAndView mv = new ModelAndView("editMovie");
		if (request.getParameter("action").equals("Edit")) {
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
			return mv;
		} else {
			adminService.deleteMovie(movie);
			mv.addObject("submitEditedMovieMsg", "Movie deleted successfully");
			mv.addObject("editMovieDivStyle", "visibility: hidden");
			return mv;
		}
	}

	@RequestMapping(value="/user/submitEditedMovie", method={RequestMethod.POST})
	public ModelAndView submitEditedMovie(HttpServletRequest request) {
		int movieId = Integer.parseInt(request.getParameter("movieId"));
		Movie movie = customerService.getMovie(movieId);
		movie.setTitle(request.getParameter("title"));
		movie.setYear(Integer.parseInt(request.getParameter("year")));
		movie.setActors(request.getParameter("actors"));
		movie.setDirector(request.getParameter("director"));
		movie.setStudio(request.getParameter("studio"));
		movie.setSynopsis(request.getParameter("synopsis"));
		movie.setCountry(request.getParameter("country"));
		movie.setImage(request.getParameter("image"));
		movie.setMovie(request.getParameter("movie"));
		movie.setGenre(Genre.valueOf(request.getParameter("genre")));
		movie.setMpaaRating(MPAARating.valueOf(request.getParameter("mpaaRating")));
		movie.setAvailability(MovieAvailability.valueOf(request.getParameter("availability")));
		adminService.addMovie(movie);
		ModelAndView mv = new ModelAndView("editMovie");
		mv.addObject("submitEditedMovieMsg", "Movie edited successfully");
		mv.addObject("editMovieDivStyle", "visibility: hidden");
		return mv;
	}
}
