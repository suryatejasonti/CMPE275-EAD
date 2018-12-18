package edu.sjsu.entertainmentbox.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.springframework.stereotype.Repository;

import edu.sjsu.entertainmentbox.dao.AdminDao;
import edu.sjsu.entertainmentbox.model.CustomerSubscription;
import edu.sjsu.entertainmentbox.model.Movie;
import edu.sjsu.entertainmentbox.model.MoviePlayLog;
import edu.sjsu.entertainmentbox.model.Rating;
import edu.sjsu.entertainmentbox.model.User;
import edu.sjsu.entertainmentbox.model.UserRole;
import edu.sjsu.entertainmentbox.model.VerificationToken;

@Repository
public class AdminDaoImpl implements AdminDao{

	@Override
	public void addMovie(Movie movie) {
		Configuration con = new Configuration().configure()
				.addAnnotatedClass(Movie.class);
		ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
		SessionFactory sf = con.buildSessionFactory(reg);
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(movie);
		tx.commit();
		session.close();
	}

	@Override
	public void deleteMovie(List<MoviePlayLog> moviePlayLogs, List<Rating> ratings, List<CustomerSubscription> customerSubscriptions, Movie movie) {
		Configuration con = new Configuration().configure()
				.addAnnotatedClass(MoviePlayLog.class)
				.addAnnotatedClass(Rating.class)
				.addAnnotatedClass(Movie.class)
				.addAnnotatedClass(User.class)
				.addAnnotatedClass(VerificationToken.class)
				.addAnnotatedClass(UserRole.class);
		ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
		SessionFactory sf = con.buildSessionFactory(reg);
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		for (MoviePlayLog moviePlayLog: moviePlayLogs) {
			if (moviePlayLog.getMovie().getMovieId() == movie.getMovieId()) {
				session.delete(moviePlayLog);
			}
		}
		for (Rating rating: ratings) {
			if (rating.getMovie().getMovieId() == movie.getMovieId()) {
				session.delete(rating);
			}
		}
		for (CustomerSubscription customerSubscription: customerSubscriptions) {
			if (customerSubscription.getMovie() != null && customerSubscription.getMovie().getMovieId() == movie.getMovieId()) {
				session.delete(customerSubscription);
			}
		}
		session.delete(movie);
		tx.commit();
		session.close();
	}

	@Override
	public List<MoviePlayLog> getPlayLogs() {
		Configuration con = new Configuration().configure()
				.addAnnotatedClass(MoviePlayLog.class)
				.addAnnotatedClass(Movie.class)
				.addAnnotatedClass(User.class)
				.addAnnotatedClass(VerificationToken.class)
				.addAnnotatedClass(UserRole.class);
		ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
		SessionFactory sf = con.buildSessionFactory(reg);
		Session session = sf.openSession();
		List<MoviePlayLog> moviePlayLogs = session.createCriteria(MoviePlayLog.class).list();
		session.close();
		return moviePlayLogs;
	}

	@Override
	public List<Rating> getRatings() {
		Configuration con = new Configuration().configure()
				.addAnnotatedClass(Rating.class)
				.addAnnotatedClass(User.class)
				.addAnnotatedClass(VerificationToken.class)
				.addAnnotatedClass(UserRole.class)
				.addAnnotatedClass(Movie.class);
		ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
		SessionFactory sf = con.buildSessionFactory(reg);
		Session session = sf.openSession();
		List<Rating> ratings = session.createCriteria(Rating.class).list();
		session.close();
		return ratings;
	}
	
}
