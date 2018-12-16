package edu.sjsu.entertainmentbox.dao;

import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import edu.sjsu.entertainmentbox.model.Movie;

@Repository
public class MovieSearchDao {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Movie> searchMovieByKeywordQuery(String text) {
        System.out.println(text);
        Query keywordQuery = getQueryBuilder()
                .keyword()
                .wildcard()
                .onFields("title", "genre")
                .matching("*"+text+"*")
           .createQuery();

        List<Movie> results = getJpaQuery(keywordQuery).getResultList();

        return results;
    }
    private FullTextQuery getJpaQuery(org.apache.lucene.search.Query luceneQuery) {

        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        try {
            fullTextEntityManager.createIndexer().startAndWait();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return fullTextEntityManager.createFullTextQuery(luceneQuery, Movie.class);
    }
    private QueryBuilder getQueryBuilder() {

        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);

        return fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder()
                .forEntity(Movie.class)
                .get();
    }
}