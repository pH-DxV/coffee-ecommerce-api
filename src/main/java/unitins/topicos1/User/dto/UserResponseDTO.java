package unitins.topicos1.User.dto;

import java.time.LocalDateTime;

import unitins.topicos1.User.model.User;

public record UserResponseDTO(

    Long id,
    String username,
    String email,
    String password,
    String type,
    LocalDateTime dateRegister,
    LocalDateTime dateChange

){

    public static UserResponseDTO valueOf(User user){

        return new UserResponseDTO(
            
            user.getId(),
            user.getUsername(),
            user.getEmail(),
            user.getPassword(),
            user.getType(),
            user.getDateRegister(),
            user.getDateChange()
        
        );

    }

}