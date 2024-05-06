package dev.nikhil.projectservice.clients.authenticationclient.dto;

import dev.nikhil.projectservice.models.BaseModel;
import lombok.Data;

import java.util.UUID;

@Data
public class ValidateTokenRequestDto {
    private UUID userId;
    private String token;
}
