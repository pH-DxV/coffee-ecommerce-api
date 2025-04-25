package unitins.topicos1.Jwt.service;

import unitins.topicos1.AuthUser.dto.AuthUserDTO;
import unitins.topicos1.User.dto.UserResponseDTO;

public interface JwtService {
    
    String generateJwt(AuthUserDTO authDTO, UserResponseDTO dto);
}
