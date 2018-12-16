package edu.sjsu.entertainmentbox.dao;

import edu.sjsu.entertainmentbox.model.Customer;
import edu.sjsu.entertainmentbox.model.SubscriptionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {

    Optional<Customer> findByEmailAddress(String emailAddress);

    //Customer findByEmailAddressAndSubscriptionSubscriptionType(String emailAddress, SubscriptionType subscriptionType);

    //@Query("select c from Customer c where c.customerId = :customerId and c.movies.movieId = :movieId")
    //Optional<Customer> findCustomerByCustomerIdAndMoviePlayLogsMovieId(@Param("customerId") Integer customerId, @Param("movieId") Integer movieId);

    //@Query("select c from Customer c join c.movies m join m.moviePlayLogs p order by p.mveStartTs")
   // Optional<List<Customer>> findAllOrderByCustomerMoviesMoviePlayLogsMveStartTs();
   /* @Query("select c from Customer c join c.Movie")
    Optional<Customer> findByIdMovieLog(@Param("customerId") Integer customerId);*/
}
