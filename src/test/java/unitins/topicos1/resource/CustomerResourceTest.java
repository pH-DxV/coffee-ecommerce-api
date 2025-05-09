package unitins.topicos1.resource;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import unitins.topicos1.Customer.dto.CustomerDTO;
import unitins.topicos1.Customer.dto.CustomerUpdateDTO;
import unitins.topicos1.User.dto.UserDTO;

import java.time.LocalDate;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;

@QuarkusTest
public class CustomerResourceTest {

    private String getJsonCustomerDTO(String name, String cpf, LocalDate dateOfBirth, String ddd, String phoneNumber, String username, String password) {
        return "{" +
                "\"name\": \"" + name + "\"," +
                "\"addressList\": []," +
                "\"cpf\": \"" + cpf + "\"," +
                "\"dateOfBirth\": \"" + dateOfBirth + "\"," +
                "\"ddd\": \"" + ddd + "\"," +
                "\"phoneNumber\": \"" + phoneNumber + "\"," +
                "\"username\": \"" + username + "\"," +
                "\"password\": \"" + password + "\"" +
                "}";
    }

    private String getJsonCustomerUpdateDTO(String name, LocalDate dateOfBirth) {
        return "{" +
                "\"name\": \"" + name + "\"," +
                "\"dateOfBirth\": \"" + dateOfBirth + "\"" +
                "}";
    }

    @Test
    public void testCreateCustomer() {
        String customerDTO = getJsonCustomerDTO("Teste", "12345678910", LocalDate.of(2000, 1, 1), "63", "999999999", "testeuser", "password");

        given()
                .contentType(ContentType.JSON)
                .body(customerDTO)
                .when()
                .post("/customers")
                .then()
                .statusCode(200)
                .body("name", is("Teste"))
                .body("cpf", is("12345678910"));
    }

    @Test
    public void testDeleteCustomer() {
        String customerDTO = getJsonCustomerDTO("TesteDelete", "12345678911", LocalDate.of(2000, 1, 1), "63", "999999999", "testeuserdelete", "password");

        Response response = given()
                .contentType(ContentType.JSON)
                .body(customerDTO)
                .when()
                .post("/customers");

        Long id = response.then().extract().path("id");

        given()
                .pathParam("id", id)
                .when()
                .delete("/customers/{id}")
                .then()
                .statusCode(204);

        given()
                .pathParam("id", id)
                .when()
                .get("/customers/{id}")
                .then()
                .statusCode(404);
    }

    @Test
    public void testUpdateCustomer() {
        String customerDTO = getJsonCustomerDTO("TesteUpdate", "12345678912", LocalDate.of(2000, 1, 1), "63", "999999999", "testeuserupdate", "password");
        Response response = given()
                .contentType(ContentType.JSON)
                .body(customerDTO)
                .when()
                .post("/customers");

        Long id = response.then().extract().path("id");
        String customerUpdateDTO = getJsonCustomerUpdateDTO("TesteUpdated", LocalDate.of(2001, 1, 1));

        given()
                .contentType(ContentType.JSON)
                .pathParam("id", id)
                .body(customerUpdateDTO)
                .when()
                .put("/customers/{id}")
                .then()
                .statusCode(204);

        given()
                .pathParam("id", id)
                .when()
                .get("/customers/{id}")
                .then()
                .statusCode(200)
                .body("name", is("TesteUpdated"))
                .body("dateOfBirth", is("2001-01-01"));
    }

    //TODO: Implementar testes para os demais endpoints da classe CustomerResource
    @Test
    public void testFindAllCustomers() {
        String customerDTO1 = getJsonCustomerDTO("Teste1", "12345678901", LocalDate.of(2000, 1, 1), "63", "999999991", "testeuser1", "password");
        String customerDTO2 = getJsonCustomerDTO("Teste2", "12345678902", LocalDate.of(2001, 1, 1), "63", "999999992", "testeuser2", "password");

        given()
                .contentType(ContentType.JSON)
                .body(customerDTO1)
                .when()
                .post("/customers");
        given()
                .contentType(ContentType.JSON)
                .body(customerDTO2)
                .when()
                .post("/customers");

        given()
                .when()
                .get("/customers")
                .then()
                .statusCode(200)
                .body("", hasSize(2));
    }
}

