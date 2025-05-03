package unitins.topicos1.AuthUser.resource;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import unitins.topicos1.AuthUser.dto.AuthRequestDTO;
import unitins.topicos1.AuthUser.dto.AuthResponseDTO;
import unitins.topicos1.AuthUser.dto.PasswordResetRequestDTO;
import unitins.topicos1.AuthUser.dto.RegisterUserDTO;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

@QuarkusTest
public class AuthUserResourceTest {

    private static final String BASE_PATH = "/auth";

    // Teste de login com credenciais válidas
    public void testLoginSuccess() {

        @Test
        AuthRequestDTO request = new AuthRequestDTO("admin@email.com", "admin123");

        given()
            .contentType(ContentType.JSON)
            .body(request)
        .when()
            .post(BASE_PATH + "/login")
        .then()
            .statusCode(200)
            .body("token", notNullValue())
            .body("expiresIn", notNullValue())
            .body("userType", notNullValue());
    }

    // Teste de login com credenciais inválidas
    @Test
    public void testLoginInvalidCredentials() {
        AuthRequestDTO request = new AuthRequestDTO("admin@email.com", "wrongpassword");

        given()
            .contentType(ContentType.JSON)
            .body(request)
        .when()
            .post(BASE_PATH + "/login")
        .then()
            .statusCode(401)
            .body("message", containsString("Credenciais inválidas"));
    }

    // Teste de registro de novo usuário
    @Test
    public void testRegisterNewUser() {
        RegisterUserDTO newUser = new RegisterUserDTO(
            "newuser@email.com",
            "StrongPassword123",
            "customer" // userType
        );

        given()
            .contentType(ContentType.JSON)
            .body(newUser)
        .when()
            .post(BASE_PATH + "/register")
        .then()
            .statusCode(201)
            .body("id", notNullValue());
    }

    // Teste de solicitação de reset de senha
    @Test
    public void testRequestPasswordReset() {
        given()
            .contentType(ContentType.JSON)
            .queryParam("email", "user@example.com")
        .when()
            .post(BASE_PATH + "/request-password-reset")
        .then()
            .statusCode(200)
            .body("message", containsString("Reset token enviado"));
    }

    // Teste de reset de senha com token válido
    @Test
    public void testResetPasswordWithValidToken() {
        PasswordResetRequestDTO request = new PasswordResetRequestDTO(
            "valid-reset-token",
            "NewSecurePassword123"
        );

        given()
            .contentType(ContentType.JSON)
            .body(request)
        .when()
            .post(BASE_PATH + "/reset-password")
        .then()
            .statusCode(200)
            .body("message", containsString("Senha atualizada"));
    }

    // Teste de acesso a rota protegida sem token
    @Test
    public void testAccessProtectedRouteWithoutToken() {
        given()
            .contentType(ContentType.JSON)
        .when()
            .get("/protected-route")
        .then()
            .statusCode(401);
    }

    // Teste de refresh token
    @Test
    public void testRefreshToken() {
        String refreshToken = "valid-refresh-token";

        given()
            .contentType(ContentType.JSON)
            .body("{\"refreshToken\":\"" + refreshToken + "\"}")
        .when()
            .post(BASE_PATH + "/refresh-token")
        .then()
            .statusCode(200)
            .body("token", notNullValue());
    }
}