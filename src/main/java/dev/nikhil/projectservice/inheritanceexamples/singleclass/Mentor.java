package dev.nikhil.projectservice.inheritanceexamples.singleclass;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "st_mentor")
@DiscriminatorValue(value = "3")
public class Mentor extends User {
    private int numberOfSessions;
    private int numberOfMentees;
}
