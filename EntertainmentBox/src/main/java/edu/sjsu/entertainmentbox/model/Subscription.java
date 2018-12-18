package edu.sjsu.entertainmentbox.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Subscription {

    private String type;
    private String name;
    private int discount;

    public Subscription(String type, String name, int discount) {
        this.type = type;
        this.name = name;
        this.discount = discount;
    }
    public Subscription() {
    	
    }

    @Column(name = "SUBSCRPTN_TYPE")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "SUBSCRPTN_NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "SUBSCRPTN_DISCOUNT")
    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
