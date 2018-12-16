package edu.sjsu.entertainmentbox.service;

import edu.sjsu.entertainmentbox.component.MoviePlayLogComponent;
import edu.sjsu.entertainmentbox.dao.CustomerRepository;
import edu.sjsu.entertainmentbox.dao.MoviePlayLogRepository;
import edu.sjsu.entertainmentbox.dao.MovieRepository;
import edu.sjsu.entertainmentbox.model.Customer;
import edu.sjsu.entertainmentbox.model.Movie;
import edu.sjsu.entertainmentbox.model.MoviePlayLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    MovieRepository movieRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    MoviePlayLogRepository moviePlayLogRepository;


    @Override
    public void addMovie(Movie movie) {
            movieRepository.save(movie);
    }

    @Override
    public void editMovie(Movie movie) {

        movieRepository.save(movie);
    }

    @Override
    public void deleteMovie(Integer movieId) {
        movieRepository.deleteById(movieId);
    }

    @Override
    public List<Movie> searchMovies() {

        return movieRepository.findAll();
    }

    @Override
    public List<Customer> browseCustomers() {
        List<Customer> customers = customerRepository.findAll();

        return customers;
    }

    @Override
    public Customer browseCustomerByEmailId(String emailId) {
        Customer customerDetail = new Customer();
      Optional<Customer> customer = customerRepository.findByEmailAddress(emailId);
      if(customer.isPresent())
      {
          customerDetail = customer.get();
      }

        return customerDetail;
    }

    @Override
    public Set<MoviePlayLog> getMoviePlayhistory(String emailId) {
        Set<MoviePlayLog> moviePlayLogs = new HashSet<>();

        Optional<Set<MoviePlayLog>> optionalMoviePlayLogs = moviePlayLogRepository.findByCustomerEmailAddressOrderByMveStartTsDesc(emailId);
        if(optionalMoviePlayLogs.isPresent())
        {
            moviePlayLogs = optionalMoviePlayLogs.get();
        }


        return moviePlayLogs;
    }

    //**For every movie, it can be counted as only one play for the same customer within 24 hours.
    @Override
    public List<String> getTopNCustomers(Integer timePeriod) {

        return moviePlayLogRepository.getTop10CustomersByNoOfPlays(-timePeriod);
    }


    /*--------------------Movie Activity Report-------------------*/
    //**For every movie, it can be counted as only one play for the same customer within 24 hours.
    //timePeriod should be 1, 7 or 30
    @Override
    public Integer getNumberOfPlays(Integer timePeriod, Integer movieId) {

        Integer noOfPlays = 0;

        noOfPlays = moviePlayLogRepository.getNoOfPlaysByMovieId(movieId,-timePeriod);

        return  noOfPlays;
    }

    @Override
    public List<String> getTopNMovies(Integer timePeriod) {

        return moviePlayLogRepository.getTop10MoviesByNoOfPlays(timePeriod);
    }
}
