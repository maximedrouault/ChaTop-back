package org.chatop.chatopback.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final JwtEncoder jwtEncoder;


    public String generateToken(Authentication authentication) {

        Instant now = Instant.now();
        Instant expiration = now.plus(1, ChronoUnit.DAYS);

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("chatop-app")
                .issuedAt(now)
                .expiresAt(expiration)
                .subject(authentication.getName())
                .build();

        JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters.from(JwsHeader.with(MacAlgorithm.HS256).build(), claims);

        return this.jwtEncoder.encode(jwtEncoderParameters).getTokenValue();
    }
}
