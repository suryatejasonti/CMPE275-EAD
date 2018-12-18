package edu.sjsu.entertainmentbox.model;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Customer {
    @Id
    private String emailAddress;
    @OneToMany(fetch = FetchType.EAGER, mappedBy="customer")
    private Set<CustomerSubscription> subscriptions = new HashSet<>();

	public Customer() {
    }

    public Customer(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Set<CustomerSubscription> getSubscription() {
		return subscriptions;
	}

	public void setSubscription(Set<CustomerSubscription> subscriptions) {
		this.subscriptions = subscriptions;
	}
}
