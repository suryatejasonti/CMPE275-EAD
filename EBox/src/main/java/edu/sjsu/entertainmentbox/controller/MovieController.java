package edu.sjsu.entertainmentbox.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.sjsu.entertainmentbox.dao.MovieRepository;
import edu.sjsu.entertainmentbox.model.*;

import edu.sjsu.entertainmentbox.service.CustomerService;
import edu.sjsu.entertainmentbox.service.MovieService;
import edu.sjsu.entertainmentbox.service.impl.CustomerServiceImpl;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URI;
import java.util.*;


@RestController
@CrossOrigin(origins = "*")
public class MovieController {
    @Autowired
    private MovieService movieService;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    CustomerServiceImpl customerService;
    @Autowired
    MovieRepository movieRepository;

    @GetMapping("/movies/{movieId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Movie> getMovie(@PathVariable Integer movieId) throws Exception{
        Movie movie = movieService.getMovie(movieId);
        return new ResponseEntity<Movie>(movie, HttpStatus.OK);
    }


    @GetMapping("/movies/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getUser() throws Exception{
        List<Movie> movies = movieService.findAllMovies();
        String result = new ObjectMapper().writeValueAsString(movies);
        return new ResponseEntity(result,HttpStatus.OK);
    }

    @GetMapping("/actorMovies/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Movie> getActorMovies() throws Exception{
        List<Movie> movies = new ArrayList<>(); //= actorService.findAllMovies();
        return movies;
    }

    @DeleteMapping("/movies/{movieId}")
    public void deleteUser(@PathVariable Integer movieId) {
        movieService.deleteMovie(movieId);
    }



    @PostMapping(path = "/movies/addMovies" , consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createMovie(@RequestBody String movieReq) throws JSONException, IOException, JsonParseException, JsonMappingException {
        System.out.println(movieReq);
        JSONObject jsonObject = new JSONObject(movieReq);
        System.out.println(jsonObject.getJSONObject("data"));
        jsonObject = jsonObject.getJSONObject("data");

        String actorSet = jsonObject.getString("actors");
        String title = jsonObject.getString("title");   
        String studio = jsonObject.getString("studio");
        String synopsis =  jsonObject.getString("synopsis");
        String image = jsonObject.getString("image");
        String movieurl = jsonObject.getString("movieUrl");
        String genre = jsonObject.getString("genre");
        String director = jsonObject.getString("directorName");
        String country = jsonObject.getString("country");
        String movieType = jsonObject.getString("movieType");
        String mpaaRating = jsonObject.getString("mpparating");
        Integer year = jsonObject.getInt("year");
        Integer price = jsonObject.getInt("price");

        Movie movie  = new Movie(title, genre, year, studio, synopsis, image, movieurl, mpaaRating, actorSet, director, country, MovieAvailability.valueOf(movieType), price);
        Movie savedUser = movieService.addMovie(movie);

        return new ResponseEntity("SUCCESS",HttpStatus.OK);

    }


    @PostMapping("/movies/startMovie")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> startMovie(@RequestParam(value="movieId", required=false) Integer movieId,
                                        @RequestParam(value="userName", required=false) String userName) throws Exception{
        MoviePlayLog moviePlayLog = customerService.updateMovieStartStatus(movieId,userName);
        String result = new ObjectMapper().writeValueAsString(moviePlayLog);
        return new ResponseEntity(result,HttpStatus.OK);
    }

    @PostMapping("/movies/stopMovie")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> stopMovie(@RequestParam(value="logId", required=false) Integer logId) throws Exception{
        MoviePlayLog moviePlayLog = customerService.updateMovieStopStatus(logId);
        String result = new ObjectMapper().writeValueAsString(moviePlayLog);
        return new ResponseEntity(result,HttpStatus.OK);
    }

    @PostMapping("/movies/saveRating")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> saveRating(@RequestParam(value="logId", required=false) Integer logId,
                                        @RequestParam(value="movieId", required=false) Integer movieId,
                                        @RequestParam(value="userName", required=false) String userName,
                                        @RequestParam(value="rating", required=false) Double rating,
                                        @RequestParam(value="review", required=false) String review) throws Exception{
        Rating savedRating = customerService.saveReview(movieId,logId,userName,review,rating);
        String result = new ObjectMapper().writeValueAsString(savedRating);
        return new ResponseEntity(result,HttpStatus.OK);
    }






   /* @PutMapping("/movies/{movieId}")
    public ResponseEntity<Object> updateMovie(@RequestBody Movie movie, @PathVariable Integer movieId) {
        Movie savedUser = movieService.updateMovie(movieId, movie);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{movieid}")
                .buildAndExpand(savedUser.getMovieId()).toUri();

        System.out.println(location);

        return ResponseEntity.noContent().build();


    }


    @PostMapping(path = "/startMovie/{movieId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> startMovie(@PathVariable Integer movieId, HttpSession session) {

        String response = "";
        Integer customerId = Integer.parseInt (session.getAttribute("customerId").toString());
        Customer customer = customerService.updateMovieStartStatus(movieId, customerId);
        if(customer!=null)
        {
            response = "SUCCESS";
        }

        return new ResponseEntity(response,HttpStatus.OK);
    }

    @PostMapping(path = "/stopMovie/{logId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> stopMovie(@PathVariable Integer logId, HttpSession session) {

        String response = "";
        Integer customerId = Integer.parseInt (session.getAttribute("customerId").toString());
        MoviePlayLog moviePlayLog = customerService.updateMovieStopStatus(logId, customerId);
        if(moviePlayLog!=null)
        {
            response = "SUCCESS";
        }

        return new ResponseEntity(response,HttpStatus.OK);
    }

    @PostMapping(path = "/movies/customerSubscription/price/{price}/duration/{duration}/type/{type}/movie/{movieId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> startSubscription(@PathVariable Integer price,@PathVariable Integer duration,@PathVariable String type, @PathVariable Integer movieId, HttpSession session) {

        String response = "";
        Integer customerId = Integer.parseInt (session.getAttribute("customerId").toString());
       CustomerSubscription customerSubscription = customerService.startSubscription(customerId,duration,type,price,movieId);
        if(customerSubscription!=null)
        {
            response = "SUCCESS";
        }

        return new ResponseEntity(customerSubscription,HttpStatus.OK);
    }

    @PostMapping(path = "/movies/checkSubscriptionExpiry")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<String>> startSubscription(HttpSession session) {

        String response = "";
        Integer customerId = Integer.parseInt (session.getAttribute("customerId").toString());
        List<String> subscriptionExpiry = customerService.viewBillingStatus(customerId);
        if(subscriptionExpiry!=null)
        {
            response = "SUCCESS";
        }

        return new ResponseEntity(subscriptionExpiry,HttpStatus.OK);
    }

    @PostMapping(path = "/movies/{title}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Movie> movieByName(@PathVariable String title,HttpSession session) {

        String response = "";
        Integer customerId = Integer.parseInt (session.getAttribute("customerId").toString());
        Movie movie = new Movie();
        Optional<Movie> movie1 = movieRepository.findByTitle(title);
        if(movie1.isPresent())
        {
            movie = movie1.get();
        }

        return new ResponseEntity(movie,HttpStatus.OK);
    }
*/
}
