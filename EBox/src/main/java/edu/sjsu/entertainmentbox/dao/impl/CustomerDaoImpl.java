package edu.sjsu.entertainmentbox.dao.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import edu.sjsu.entertainmentbox.dao.CustomerRepository;
import edu.sjsu.entertainmentbox.dao.CustomerSubscriptionRepository;
import edu.sjsu.entertainmentbox.dao.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.sjsu.entertainmentbox.dao.CustomerDao;
import edu.sjsu.entertainmentbox.model.Customer;
import edu.sjsu.entertainmentbox.model.CustomerSubscription;
import edu.sjsu.entertainmentbox.model.Genre;
import edu.sjsu.entertainmentbox.model.Movie;
import edu.sjsu.entertainmentbox.model.MovieAvailability;

@Repository
public class CustomerDaoImpl implements CustomerDao{

	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	CustomerSubscriptionRepository customerSubscriptionRepository;
	@Autowired
	MovieRepository movieRepository;


	@Override
	public Customer getCustomer(String emailAddress) {
		/*Configuration con = new Configuration().configure()
				.addAnnotatedClass(Customer.class)
				.addAnnotatedClass(CustomerSubscription.class)
				.addAnnotatedClass(Movie.class);
		ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
		 SessionFactory sf = con.buildSessionFactory(reg);
		Session session = sf.openSession();
		Customer customer = (Customer) session.get(Customer.class, emailAddress);
		session.close();
		return customer;*/
		Optional<Customer> optionalCustomer = customerRepository.findById(emailAddress);
		Customer customer = new Customer();
		if(optionalCustomer.isPresent())
		{
			customer = optionalCustomer.get();
		}

		return customer;
	}

	@Override
	public void saveSubscription(CustomerSubscription customerSubscription, Customer customer) {
		/*Configuration con = new Configuration().configure()
				.addAnnotatedClass(Customer.class)
				.addAnnotatedClass(CustomerSubscription.class)
				.addAnnotatedClass(Movie.class);
		ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
		SessionFactory sf = con.buildSessionFactory(reg);
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		session.save(customerSubscription);
		session.saveOrUpdate(customer);
		tx.commit();
		session.close();*/
		Optional<Customer> optionalCustomer = customerRepository.findByEmailAddress(customer.getEmailAddress());
		CustomerSubscription customerSubscription1 = customerSubscription;
		if(optionalCustomer.isPresent())
		{
			customerSubscription1.setCustomer(optionalCustomer.get());
			customerSubscriptionRepository.save(customerSubscription1);
		}
		else
		{
			customerRepository.save(customer);
			customerSubscription1.setCustomer(customer);
			customerSubscriptionRepository.save(customerSubscription1);
		}


	}

	@Override
	public void saveCustomer(Customer customer) {
	/*	Configuration con = new Configuration().configure()
				.addAnnotatedClass(Customer.class)
				.addAnnotatedClass(CustomerSubscription.class)
				.addAnnotatedClass(Movie.class);
		ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
		SessionFactory sf = con.buildSessionFactory(reg);
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(customer);
		tx.commit();
		session.close();*/
	System.out.println("Email:::::::::"+customer.getEmailAddress());
		customerRepository.save(customer);
	}

	@Override
	public Movie searchMovie(String searchText) {
		/*Configuration con = new Configuration().configure()
				.addAnnotatedClass(Genre.class)
				.addAnnotatedClass(MovieAvailability.class)
				.addAnnotatedClass(Movie.class);
		ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
		SessionFactory sf = con.buildSessionFactory(reg);
		Session session = sf.openSession();
		List<Movie> allMovies = session.createCriteria(Movie.class).list();
		session.close();
		return allMovies;*/
		Optional<Movie> movie = movieRepository.findByTitle(searchText);
		Movie movie1 = new Movie();
		if(movie.isPresent())
		{
			movie1 = movie.get();
		}

		return movie1;
	}

}
