package dev.nikhil.projectservice.inheritanceexamples.singleclass;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "st_instructor")
@DiscriminatorValue(value = "2")
public class Instructor extends User {
    private boolean isHandsome;
}
