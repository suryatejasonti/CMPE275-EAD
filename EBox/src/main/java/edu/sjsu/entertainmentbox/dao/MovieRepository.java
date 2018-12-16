package edu.sjsu.entertainmentbox.dao;

import edu.sjsu.entertainmentbox.model.Movie;
import edu.sjsu.entertainmentbox.model.SubscriptionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

   Optional<Movie>  findByTitle(String title);

   //Optional<Movie> findByTitleOrGenreOrStudioOrSynopsisOrActorsName(String detail);
   List<Movie> findByCustomersEmailAddressAndCustomerSubscriptionsSubscriptionType(String emailAddress, SubscriptionType subscriptionType);

   Optional<Movie> findByMovieId(Integer movieId);

}
