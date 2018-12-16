package edu.sjsu.entertainmentbox.component;

import edu.sjsu.entertainmentbox.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerSubscriptionComponent {
    private Integer year;
    private Integer month;
    private Customer customer;

    public CustomerSubscriptionComponent() {
    }

    public CustomerSubscriptionComponent(Integer year, Integer month, Customer customer) {
        this.year = year;
        this.month = month;
        this.customer = customer;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
