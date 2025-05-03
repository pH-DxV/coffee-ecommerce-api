package unitins.topicos1.resource;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import unitins.topicos1.Address.dto.AddressDTO;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
public class AddressResourceTest {

    private static final String BASE_PATH = "/addresses";

    // Método auxiliar que retorna Long para IDs
    private Long createTestAddress() {

        AddressDTO dto = new AddressDTO(

            "12345678",
            "Main Street", 
            "Apartment 42"

        );

        return given()
                    .contentType(ContentType.JSON)
                    .body(dto)
                .when()
                    .post(BASE_PATH)
                .then()
                    .statusCode(200)
                    .extract()
                    .as(Long.class); // Extrai diretamente como Long
        }

    @Test
    public void testCreateAddressSuccess() {

        AddressDTO validDto = new AddressDTO
        (

            "87654321",
            "Oak Avenue",
            "Building B"

        );
        
        given()
            .contentType(ContentType.JSON)
            .body(validDto)
        .when()
            .post(BASE_PATH)
        .then()
            .statusCode(200)
            .body("cep", equalTo("87654321"));

    }

    @Test
    public void testFindByIdSuccess() {

        Long id = createTestAddress();
        
        given()
            .pathParam("id", id)
        .when()
            .get(BASE_PATH + "/{id}")
        .then()
            .statusCode(200)
            .body("id", equalTo(id.intValue())); // Converte Long para int para comparação

    }

    @Test
    public void testUpdateAddressSuccess() {

        Long id = createTestAddress();
        
        AddressDTO updateDto = new AddressDTO
        (
            "87654321",
            "Updated Street",
            "Updated Complement"

        );

        given()
            .contentType(ContentType.JSON)
            .body(updateDto)
            .pathParam("id", id)
        .when()
            .put(BASE_PATH + "/{id}")
        .then()
            .statusCode(204);

    }

    @Test
    public void testDeleteAddressSuccess() {

        Long id = createTestAddress();
        
        given()
            .pathParam("id", id)
        .when()
            .delete(BASE_PATH + "/{id}")
        .then()
            .statusCode(204);
            
    }

    // Para visualizar o log completo
    static {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}