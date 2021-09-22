package com.creditsuisse.assignment.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode
@Getter
@Setter
@ToString
public class Event {
    public String id;
    public String state;
    public long timestamp;
    public String type;
    public String host;
}
