package com.qweed.backend.jpa;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "weedperiod")
@JsonIgnoreProperties("customer")
public class Weedperiod {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private String name;
    private Date startDate;
    private Date endDate;
    private Double averageGramPerJoint;
    private Double costPerGram;

    private Long averageJointsSmokedPerWeek;
    private Long averageDurationPerWeek;

    private Boolean isInitial;

    @Transient
    private Long totalJoints;
    @Transient
    private double totalCosts;
    @Transient
    private Long totalTime;
    @Transient
    private double costPerJoint;
    @Transient
    private double averageCostPerWeek;
    @Transient
    public String customerName;

    @OneToMany(mappedBy = "weedperiod", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Smokesession> smokesessions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Double getAverageGramPerJoint() {
        return averageGramPerJoint;
    }

    public void setAverageGramPerJoint(Double averageGramPerJoint) {
        this.averageGramPerJoint = averageGramPerJoint;
    }

    public Double getCostPerGram() {
        return costPerGram;
    }

    public void setCostPerGram(Double costPerGram) {
        this.costPerGram = costPerGram;
    }

    public Long getAverageJointsSmokedPerWeek() {
        return averageJointsSmokedPerWeek;
    }

    public void setAverageJointsSmokedPerWeek(Long averageJointsSmokedPerWeek) {
        this.averageJointsSmokedPerWeek = averageJointsSmokedPerWeek;
    }

    public Long getAverageDurationPerWeek() {
        return averageDurationPerWeek;
    }

    public void setAverageDurationPerWeek(Long averageDurationPerWeek) {
        this.averageDurationPerWeek = averageDurationPerWeek;
    }

    public Boolean getIsInitial() {
        return isInitial;
    }

    public void setIsInitial(Boolean isInitial) {
        this.isInitial = isInitial;
    }

    public Long getTotalJoints() {
        return totalJoints;
    }

    public void setTotalJoints(Long totalJoints) {
        this.totalJoints = totalJoints;
    }

    public double getTotalCosts() {
        return totalCosts;
    }

    public void setTotalCosts(double totalCosts) {
        this.totalCosts = totalCosts;
    }

    public Long getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Long totalTime) {
        this.totalTime = totalTime;
    }

    public double getCostPerJoint() {
        return costPerJoint;
    }

    public void setCostPerJoint(double costPerJoint) {
        this.costPerJoint = costPerJoint;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public double getAverageCostPerWeek() {
        return averageCostPerWeek;
    }

    public void setAverageCostPerWeek(double averageCostPerWeek) {
        this.averageCostPerWeek = averageCostPerWeek;
    }

    public List<Smokesession> getSmokesessions() {
        return smokesessions;
    }

    public void setSmokesessions(List<Smokesession> smokesessions) {
        this.smokesessions = smokesessions;
    }
}