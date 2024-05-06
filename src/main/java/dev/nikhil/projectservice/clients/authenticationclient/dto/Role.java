package dev.nikhil.projectservice.clients.authenticationclient.dto;

import dev.nikhil.projectservice.models.BaseModel;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Role extends BaseModel {
    private String role;
}
