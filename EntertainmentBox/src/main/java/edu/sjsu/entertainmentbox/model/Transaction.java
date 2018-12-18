package edu.sjsu.entertainmentbox.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Transaction {

    private int transactionId;
    @ManyToOne
    private Customer customer;
    private String transType;
    private int transAmt;
    private Date transactionStartTS;
    private Date transactionEndTS;
    private String transactionStatus;

    public Transaction() {
    }

    public Transaction(int transactionId, Customer customer, String transType, int transAmt, Date transactionStartTS, Date transactionEndTS, String transactionStatus) {
        this.transactionId = transactionId;
        this.customer = customer;
        this.transType = transType;
        this.transAmt = transAmt;
        this.transactionStartTS = transactionStartTS;
        this.transactionEndTS = transactionEndTS;
        this.transactionStatus = transactionStatus;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TRANS_ID", nullable = false, unique = true)
    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    @Column(name = "CUST_ID", nullable = false)
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Column(name = "TRANS_TYPE")
    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    @Column(name = "TRANS_AMT")
    public int getTransAmt() {
        return transAmt;
    }

    public void setTransAmt(int transAmt) {
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
}
