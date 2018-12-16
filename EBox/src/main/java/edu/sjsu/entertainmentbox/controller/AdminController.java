package edu.sjsu.entertainmentbox.controller;

import edu.sjsu.entertainmentbox.EntertainmentBoxApplication;
import edu.sjsu.entertainmentbox.model.Movie;
import edu.sjsu.entertainmentbox.model.MovieAvailability;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;

@Controller
public class AdminController {
    private static final Logger logger = LoggerFactory.getLogger(EntertainmentBoxApplication.class);

    @RequestMapping(value = "/movie/addMovie", method = RequestMethod.POST)
    public ModelAndView addMovie(ModelMap model,
                                  @RequestParam(value="title", required=false) String title,
                                  @RequestParam(value="genre", required=false) String genre,
                                  @RequestParam(value="year", required=false) String year,
                                  @RequestParam(value="studio", required=false) String studio,
                                  @RequestParam(value="synopsis", required=false) String synopsis,
                                  @RequestParam(value="imageUrl", required=false) String imageUrl,
                                  @RequestParam(value="movieUrl", required=false) String movieUrl,
                                  @RequestParam(value="actors", required=false) String actors,
                                  @RequestParam(value="director", required=false) String director,
                                  @RequestParam(value="country", required=false) String country,
                                  @RequestParam(value="rating", required=false) String rating,
                                  @RequestParam(value="availability", required=false) String availability,
                                  @RequestParam(value="price", required=false) String price) throws ParseException {

        Movie movie = new Movie(title,genre,Integer.parseInt(year),studio,synopsis,imageUrl,movieUrl,actors,director,country,rating,MovieAvailability.valueOf(availability) ,Integer.parseInt(price));

        ModelAndView mv= new ModelAndView("addMovieSuccess");
        return mv;
    }





}
