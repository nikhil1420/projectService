package dev.nikhil.projectservice.clients.authenticationclient.dto;

import lombok.Data;


@Data
public class ValidateTokenResponseDto {
    private UserDto user;
    private SessionStatus sessionStatus;
}