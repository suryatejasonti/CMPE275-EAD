package edu.sjsu.entertainmentbox.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

@Entity
public class MoviePlayLog {

    private Integer logId;
    // private Integer customerId;
    private String playStatus;
    private Movie movie;
    private Date mveStartTs;
    private Date mveEndTS;

    private Customer customer;

    public MoviePlayLog() {
    }

    public MoviePlayLog(String playStatus,  Date mveStartTs, Date mveEndTS, Movie movie) {

        this.playStatus = playStatus;
        this.mveStartTs = mveStartTs;
        this.mveEndTS = mveEndTS;
        this.movie = movie;
    }

    public MoviePlayLog(Integer logId, String playStatus, Date mveStartTs, Date mveEndTS) {
        this.logId = logId;
        this.playStatus = playStatus;
        this.mveStartTs = mveStartTs;
        this.mveEndTS = mveEndTS;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "LOG_ID", nullable = false, unique = true)
    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    @Column(name = "PLAY_STATUS")
    public String getPlayStatus() {
        return playStatus;
    }

    public void setPlayStatus(String playStatus) {
        this.playStatus = playStatus;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "MVE_START_TS")
    public Date getMveStartTs() {
        return mveStartTs;
    }

    public void setMveStartTs(Date mveStartTs) {
        this.mveStartTs = mveStartTs;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "MVE_END_TS")
    public Date getMveEndTS() {
        return mveEndTS;
    }

    public void setMveEndTS(Date mveEndTS) {
        this.mveEndTS = mveEndTS;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "EMAIL_ID", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MOVIE_ID", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
