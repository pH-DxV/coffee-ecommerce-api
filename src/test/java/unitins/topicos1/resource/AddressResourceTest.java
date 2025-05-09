package unitins.topicos1.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import unitins.topicos1.Address.dto.AddressDTO;
import unitins.topicos1.Address.repository.AddressRepository;

@QuarkusTest
public class AddressResourceTest {

    @Inject
    AddressRepository addressRepository;

    @BeforeEach
    @Transactional
    public void setup() {
        addressRepository.deleteAll();
    }

    @Test
    public void testCreateAddress() {
        AddressDTO validDTO = new AddressDTO("77001000", "Rua 1", "Complemento 1");

        given()
                .contentType(ContentType.JSON)
                .body(validDTO)
                .when().post("/address")
                .then()
                .statusCode(200) // Testa se o status code é 200 (OK)
                .body("cep", equalTo("77001000"))
                .body("street", equalTo("Rua 1"))
                .body("complement", equalTo("Complemento 1"));
    }

    @Test
    public void testCreateAddressWithInvalidCep() {
        AddressDTO invalidDTO = new AddressDTO("123", "Rua A", "Comp A"); // CEP inválido (menos de 8 caracteres)

        given()
                .contentType(ContentType.JSON)
                .body(invalidDTO)
                .when().post("/address")
                .then()
                .statusCode(400); // Testa se o status code é 400 (Bad Request) para CEP inválido
    }

    @Test
    public void testCreateAddressWithNullCep() {
        AddressDTO invalidDTO = new AddressDTO(null, "Rua B", "Comp B");

        given()
                .contentType(ContentType.JSON)
                .body(invalidDTO)
                .when().post("/address")
                .then()
                .statusCode(400);
    }

     @Test
    public void testCreateAddressWithEmptyCep() {
        AddressDTO invalidDTO = new AddressDTO("", "Rua B", "Comp B");

        given()
                .contentType(ContentType.JSON)
                .body(invalidDTO)
                .when().post("/address")
                .then()
                .statusCode(400);
    }

    @Test
    public void testCreateAddressWithNullStreet() {
        AddressDTO invalidDTO = new AddressDTO("77001000", null, "Comp C");

        given()
                .contentType(ContentType.JSON)
                .body(invalidDTO)
                .when().post("/address")
                .then()
                .statusCode(400);
    }

     @Test
    public void testCreateAddressWithEmptyStreet() {
        AddressDTO invalidDTO = new AddressDTO("77001000", "", "Comp C");

        given()
                .contentType(ContentType.JSON)
                .body(invalidDTO)
                .when().post("/address")
                .then()
                .statusCode(400);
    }

    @Test
    public void testCreateAddressWithNullComplement() {
        AddressDTO invalidDTO = new AddressDTO("77001000", "Rua D", null);

        given()
                .contentType(ContentType.JSON)
                .body(invalidDTO)
                .when().post("/address")
                .then()
                .statusCode(400);
    }

    @Test
    public void testCreateAddressWithEmptyComplement() {
        AddressDTO invalidDTO = new AddressDTO("77001000", "Rua D", "");

        given()
                .contentType(ContentType.JSON)
                .body(invalidDTO)
                .when().post("/address")
                .then()
                .statusCode(400);
    }
}