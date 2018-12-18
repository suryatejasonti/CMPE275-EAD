package edu.sjsu.entertainmentbox.dao.impl;

import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.springframework.stereotype.Repository;

import edu.sjsu.entertainmentbox.dao.CustomerDao;
import edu.sjsu.entertainmentbox.model.Customer;
import edu.sjsu.entertainmentbox.model.CustomerSubscription;
import edu.sjsu.entertainmentbox.model.Genre;
import edu.sjsu.entertainmentbox.model.Movie;
import edu.sjsu.entertainmentbox.model.MovieAvailability;
import edu.sjsu.entertainmentbox.model.Rating;
import edu.sjsu.entertainmentbox.model.User;
import edu.sjsu.entertainmentbox.model.UserRole;
import edu.sjsu.entertainmentbox.model.VerificationToken;

@Repository
public class CustomerDaoImpl implements CustomerDao{
	@Override
	public Customer getCustomer(String emailAddress) {
		Configuration con = new Configuration().configure()
				.addAnnotatedClass(Customer.class)
				.addAnnotatedClass(CustomerSubscription.class)
				.addAnnotatedClass(Movie.class);
		ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
		SessionFactory sf = con.buildSessionFactory(reg);
		Session session = sf.openSession();
		Customer customer = (Customer) session.get(Customer.class, emailAddress);
		session.close();
		return customer;
	}

	@Override
	public void saveSubscription(CustomerSubscription customerSubscription, Customer customer) {
		Configuration con = new Configuration().configure()
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
		session.close();
	}

	@Override
	public void saveCustomer(Customer customer) {
		Configuration con = new Configuration().configure()
				.addAnnotatedClass(Customer.class)
				.addAnnotatedClass(CustomerSubscription.class)
				.addAnnotatedClass(Movie.class);
		ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
		SessionFactory sf = con.buildSessionFactory(reg);
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(customer);
		tx.commit();
		session.close();
	}

	@Override
	public List<Movie> searchMovie(String searchText) {
		Configuration con = new Configuration().configure()
				.addAnnotatedClass(Genre.class)
				.addAnnotatedClass(MovieAvailability.class)
				.addAnnotatedClass(Movie.class);
		ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
		SessionFactory sf = con.buildSessionFactory(reg);
		Session session = sf.openSession();
		List<Movie> allMovies = session.createCriteria(Movie.class).list();
		session.close();
		return allMovies;
	}

	@Override
	public List<CustomerSubscription> getAllSubscriptions() {
		Configuration con = new Configuration().configure()
				.addAnnotatedClass(Customer.class)
				.addAnnotatedClass(CustomerSubscription.class)
				.addAnnotatedClass(Movie.class);
		ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
		SessionFactory sf = con.buildSessionFactory(reg);
		Session session = sf.openSession();
		List<CustomerSubscription> customerSubscriptions = session.createCriteria(CustomerSubscription.class).list();
		return customerSubscriptions;
	}

	@Override
	public Movie getMovie(int movieId) {
		Configuration con = new Configuration().configure()
				.addAnnotatedClass(Movie.class);
		ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
		SessionFactory sf = con.buildSessionFactory(reg);
		Session session = sf.openSession();
		Movie movie = (Movie) session.get(Movie.class, movieId);
		session.close();
		return movie;
	}

	@Override
	public void submitRating(Rating rating) {
		Configuration con = new Configuration().configure()
				.addAnnotatedClass(Rating.class)
				.addAnnotatedClass(User.class)
				.addAnnotatedClass(Movie.class)
				.addAnnotatedClass(VerificationToken.class)
				.addAnnotatedClass(UserRole.class);
		ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
		SessionFactory sf = con.buildSessionFactory(reg);
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(rating);
		tx.commit();
		session.close();
	}

	@Override
	public List<Rating> getRatings() {
		Configuration con = new Configuration().configure()
				.addAnnotatedClass(Rating.class)
				.addAnnotatedClass(Movie.class)
				.addAnnotatedClass(User.class)
				.addAnnotatedClass(VerificationToken.class)
				.addAnnotatedClass(UserRole.class);
		ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
		SessionFactory sf = con.buildSessionFactory(reg);
		Session session = sf.openSession();
		List<Rating> ratings = session.createCriteria(Rating.class).list();
		return ratings;
	}

}
