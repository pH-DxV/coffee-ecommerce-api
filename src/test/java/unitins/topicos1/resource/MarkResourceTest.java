package unitins.topicos1.resource;


import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import unitins.topicos1.Mark.dto.MarkDTO;
import unitins.topicos1.Mark.dto.MarkResponseDTO;
import unitins.topicos1.Mark.service.MarkService;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNull;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasItem;


@QuarkusTest
public class MarkResourceTest {
    
    @Inject
    MarkService service;

    @Test
    @TestSecurity(user = "tester", roles = "Employees")
    public void testFindAll(){

        given()
        .when()
            .get("/marks")
        .then()
            .statusCode(200)
            .body("name", hasItem(is("nike")));

    }

    @Test
    @TestSecurity(user = "tester", roles = "Employee")
    public void testFindById() {
        given()
        .when()
            .get("/marks/search/id/1")
        .then()
            .statusCode(200)
            .body("id", is(1));
    }

    @Test
    @TestSecurity(user = "tester", roles = "Employee")
    public void testFindByName(){
        given()
        .when()
            .get("marks/search/name/n")
        .then()
            .statusCode(200)
            .body("name", hasItem(is("nike")));
    }

    @Test
    @TestSecurity(user = "tester", roles = "Employee")
    public void testCreate(){
        MarkDTO dto = new MarkDTO("puma","fff");

        given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(dto)
        .when()
            .post("/marks")
        .then()
            .statusCode(200)
            .body("id", is(3));
    }

    @Test
    @TestSecurity(user = "tester", roles = "Employee")
    public void testUpdate(){
        MarkDTO dto = new MarkDTO("under armor","eee");

        given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(dto)
        .when()
            .put("/marks/3")
        .then()
            .statusCode(204);
    }

    @Test
    @TestSecurity(user = "tester", roles = "Employee")
    public void testDelete(){
        
        MarkResponseDTO response = service.create(new MarkDTO("mizuno","ddd"));

        given()
        .when()
            .pathParam("id", response.id())
            .delete("/marks/{id}")
        .then()
            .statusCode(204);

        service.delete(response.id());
        assertNull(service.findById(response.id()));
    }
}

