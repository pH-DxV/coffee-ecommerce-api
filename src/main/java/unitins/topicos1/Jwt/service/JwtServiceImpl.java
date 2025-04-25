package unitins.topicos1.Jwt.service;

import java.time.Duration;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import unitins.topicos1.AuthUser.dto.AuthUserDTO;
import unitins.topicos1.User.dto.UserResponseDTO;

@ApplicationScoped
public class JwtServiceImpl implements JwtService {

    private static final Duration EXPIRATION_TIME = Duration.ofHours(1);

    @Override
    public String generateJwt(AuthUserDTO authDTO, UserResponseDTO dto){

        Instant expiryDate = Instant.now().plus(EXPIRATION_TIME);

        Set<String> roles = new HashSet<String>();

        if (authDTO.type() == 1){

            roles.add("Customer");

        } else if (authDTO.type() == 2){

            roles.add("Employee");
        }

        return Jwt.issuer("cafeteria-jwt")
                .claim("userId", dto.id())
                .claim("type", authDTO.type())
                .subject(dto.username())
                .groups(roles)
                .expiresAt(expiryDate)
                .sign();

    }
    
}
