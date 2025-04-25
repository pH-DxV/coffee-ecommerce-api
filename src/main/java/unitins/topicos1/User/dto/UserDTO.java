package unitins.topicos1.User.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserDTO (
    
    //teste

    @NotBlank(message = "Username is Required!")
    @Size(min = 3, max = 20, message = "The Username size must be between 3 and 20 characters!")
    String username,

    @NotBlank(message = "Email is Required!")
    @Email(message = "Invalid Email!")
    String email,

    @NotBlank(message = "Password is Required!")
    @Size(min = 6, message = "Password required 6 characters at minimum!")
    String password,

    @NotBlank(message = "Type is Required!")
    String type

){ }
