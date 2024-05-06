package dev.nikhil.projectservice.dto;

import lombok.Data;

@Data
public class ValidateTokenRequestDto {
    private Long userId;
    private String token;
}
