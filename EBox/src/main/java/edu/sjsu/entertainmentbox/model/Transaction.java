package edu.sjsu.entertainmentbox.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Transaction {

    private Integer transactionId;
    private MovieAvailability transType;
    private Integer transAmt;
    private Date transactionStartTS;
    private Date transactionEndTS;
    private String transactionStatus;
    private CustomerSubscription subscription;
    private Customer customer;

    public Transaction() {
    }


    public Transaction(MovieAvailability transType, Integer transAmt, Date transactionStartTS, Date transactionEndTS, String transactionStatus) {
        this.transType = transType;
        this.transAmt = transAmt;
        this.transactionStartTS = transactionStartTS;
        this.transactionEndTS = transactionEndTS;
        this.transactionStatus = transactionStatus;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TRANS_ID", nullable = false, unique = true)
    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    @Column(name = "TRANS_TYPE")
    public MovieAvailability getTransType() {
        return transType;
    }

    public void setTransType(MovieAvailability transType) {
        this.transType = transType;
    }

    @Column(name = "TRANS_AMT")
    public Integer getTransAmt() {
        return transAmt;
    }

    public void setTransAmt(Integer transAmt) {
        this.transAmt = transAmt;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "TRANS_START_TS")
    public Date getTransactionStartTS() {
        return transactionStartTS;
    }

    public void setTransactionStartTS(Date transactionStartTS) {
        this.transactionStartTS = transactionStartTS;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "TRANS_END_TS")
    public Date getTransactionEndTS() {
        return transactionEndTS;
    }

    public void setTransactionEndTS(Date transactionEndTS) {
        this.transactionEndTS = transactionEndTS;
    }

    @Column(name = "TRANS_STATUS")
    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    @Enumerated(EnumType.STRING)
    public CustomerSubscription getSubscription() {
        return subscription;
    }

    public void setSubscription(CustomerSubscription subscription) {
        this.subscription = subscription;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMAIL_ID", nullable = false)
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
