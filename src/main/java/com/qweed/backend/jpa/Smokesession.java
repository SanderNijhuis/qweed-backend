package com.qweed.backend.jpa;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "smokesession")
@JsonIgnoreProperties("weedperiod")
public class Smokesession {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "weedperiod_id")
    private Weedperiod weedperiod;

    private String name;
    private Date startDate;

    private Long jointsSmoked;
    private Long duration;

    @Transient
    private Long weedperiodID;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Weedperiod getWeedperiod() {
        return weedperiod;
    }

    public void setWeedperiod(Weedperiod weedperiod) {
        this.weedperiod = weedperiod;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Long getJointsSmoked() {
        return jointsSmoked;
    }

    public void setJointsSmoked(Long jointsSmoked) {
        this.jointsSmoked = jointsSmoked;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Long getWeedperiodID() {
        return weedperiodID;
    }

    public void setWeedperiodID(Long weedperiodID) {
        this.weedperiodID = weedperiodID;
    }
}