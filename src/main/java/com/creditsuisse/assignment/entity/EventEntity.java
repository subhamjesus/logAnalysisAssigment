package com.creditsuisse.assignment.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@EqualsAndHashCode
@Getter
@Setter
@Entity
@ToString
@Table(name = "EVENT")
public class EventEntity {

    @Id
    @Column(name = "ID")
    public String id;

    @Column(name = "DURATION")
    public long duration;

    @Column(name = "TYPE")
    public String type;

    @Column(name = "HOST")
    public String host;

    @Column(name = "ALERT")
    public Boolean alert;
}
