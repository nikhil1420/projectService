package dev.nikhil.projectservice.clients.authenticationclient;

import dev.nikhil.projectservice.clients.authenticationclient.dto.ValidateTokenRequestDto;
import dev.nikhil.projectservice.clients.authenticationclient.dto.ValidateTokenResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;


@Component
public class AuthenticationClient {
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    public ValidateTokenResponseDto validate(String token, UUID userId) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ValidateTokenRequestDto request = new ValidateTokenRequestDto();
        request.setToken(token);
        request.setUserId(userId);

        ResponseEntity<ValidateTokenResponseDto> l = restTemplate.postForEntity(
                "http://localhost:9000/auth/validate",
                request,
                ValidateTokenResponseDto.class
        );

        return l.getBody();
    }
}
