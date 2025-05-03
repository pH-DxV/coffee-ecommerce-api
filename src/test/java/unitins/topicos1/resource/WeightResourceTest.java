package unitins.topicos1.resource;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.http.ContentType;
import unitins.topicos1.Weight.dto.WeightDTO;

@QuarkusTest
public class WeightResourceTest {

    private static final String BASE_PATH = "/weights";

    @Test
    @TestSecurity(user = "admin", roles = {"Employees"})
    public void testCreateWeight() {
        WeightDTO dto = new WeightDTO(

            .setWeightValue(1.5);
            .setUnitOfMeasurement();
            .setPackageType("Caixa");
            .setDescription("Pacote Pequeno");
            
        );

        
        given()
            .contentType(ContentType.JSON)
            .body(dto)
        .when()
            .post(BASE_PATH)
        .then()
            .statusCode(201)
            .body("description", equalTo("Pacote Pequeno"))
            .body("weightValue", equalTo(1.5f));
    }

    @Test
    @TestSecurity(user = "admin", roles = {"Funcionario"})
    public void testUpdateWeight() {
        // Primeiro cria um peso para depois atualizar
        WeightDTO createDto = new WeightDTO("Pacote Médio", 5.0, "kg", "Caixa");
        Long id = given()
                    .contentType(ContentType.JSON)
                    .body(createDto)
                    .post(BASE_PATH)
                    .then()
                    .extract().path("id");

        WeightDTO updateDto = new WeightDTO("Pacote Grande", 10.0, "kg", "Caixa");

        given()
            .contentType(ContentType.JSON)
            .body(updateDto)
            .put(BASE_PATH + "/" + id)
        .then()
            .statusCode(204);
    }

    @Test
    public void testFindById() {
        // Assumindo que existe um peso com ID 1 no banco de testes
        given()
            .when()
            .get(BASE_PATH + "/1")
        .then()
            .statusCode(200)
            .body("id", equalTo(1));
    }

    @Test
    public void testFindAllPaginated() {
        given()
            .queryParam("page", 0)
            .queryParam("pageSize", 10)
        .when()
            .get(BASE_PATH)
        .then()
            .statusCode(200)
            .body("size()", lessThanOrEqualTo(10));
    }

    @Test
    public void testFindByWeightValue() {
        given()
            .when()
            .get(BASE_PATH + "/search/value/1.5")
        .then()
            .statusCode(200)
            .body("[0].weightValue", equalTo(1.5f));
    }

    @Test
    public void testFindByUnitOfMeasurement() {
        given()
            .when()
            .get(BASE_PATH + "/search/unit/kg")
        .then()
            .statusCode(200)
            .body("[0].unitOfMeasurement", equalTo("kg"));
    }

    @Test
    @TestSecurity(user = "admin", roles = {"Employees"})
    public void testDeleteWeight() {
        // Primeiro cria um peso para depois deletar
        WeightDTO dto = new WeightDTO("Pacote Temporário", 2.0, "kg", "Saco");
        Long id = given()
            .contentType(ContentType.JSON)
            .body(dto)
            .post(BASE_PATH)
            .then()
            .extract().path("id");

        given()
            .when()
            .delete(BASE_PATH + "/" + id)
        .then()
            .statusCode(204);
    }
}
