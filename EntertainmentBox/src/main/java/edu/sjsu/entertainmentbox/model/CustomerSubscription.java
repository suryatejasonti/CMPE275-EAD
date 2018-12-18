package edu.sjsu.entertainmentbox.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class CustomerSubscription {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private int subscriptionId;
	@ManyToOne
    private Customer customer;
    private SubscriptionType subscriptionType;
    @OneToOne
    private Movie movie;
    private int price;
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date subscriptionTS;
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date subscriptionStartDate;
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date subscriptionEndDate;

    public CustomerSubscription() {
    }

    public CustomerSubscription(SubscriptionType subscriptionType, int price, Date subscriptionTS, Date subscriptionStartDate, Date subscriptionEndDate) {
        this.subscriptionType = subscriptionType;
        this.price = price;
        this.subscriptionTS = subscriptionTS;
        this.subscriptionStartDate = subscriptionStartDate;
        this.subscriptionEndDate = subscriptionEndDate;
    }
   
    public int getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(int subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Column(name = "SUBSCRPTN_TYPE", nullable = false)
    public SubscriptionType getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(SubscriptionType subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

    public int getPrice() {
        return price;
    }

	public void setPrice(int price) {
        this.price = price;
    }

    public Date getSubscriptionTS() {
        return subscriptionTS;
    }

    public void setSubscriptionTS(Date subscriptionTS) {
        this.subscriptionTS = subscriptionTS;
    }

    public Date getSubscriptionStartDate() {
        return subscriptionStartDate;
    }

    public void setSubscriptionStartDate(Date subscriptionStartDate) {
        this.subscriptionStartDate = subscriptionStartDate;
    }

    public Date getSubscriptionEndDate() {
        return subscriptionEndDate;
    }

    public void setSubscriptionEndDate(Date subscriptionEndDate) {
        this.subscriptionEndDate = subscriptionEndDate;
    }
}
