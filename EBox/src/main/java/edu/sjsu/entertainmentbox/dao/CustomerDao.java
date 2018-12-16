package edu.sjsu.entertainmentbox.dao;

import java.util.List;

import edu.sjsu.entertainmentbox.model.Customer;
import edu.sjsu.entertainmentbox.model.CustomerSubscription;
import edu.sjsu.entertainmentbox.model.Movie;

public interface CustomerDao {
	public Customer getCustomer(String emailaddress);
	public void saveSubscription(CustomerSubscription customerSubscription, Customer customer);
	public void saveCustomer(Customer customer);
	public Movie searchMovie(String searchText);
}
