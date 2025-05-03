package unitins.topicos1.resource;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.transaction.Transactional;
import unitins.topicos1.Coffee.dto.CoffeeDTO;
import unitins.topicos1.Coffee.model.Coffee;
import unitins.topicos1.Mark.model.Mark;

@QuarkusTest
class CoffeeResourceTest {

    private Mark mark;

    @BeforeEach
    @Transactional
    void setup() {
        Coffee.deleteAll();
        Mark.deleteAll();

        mark = new Mark();
        mark.setName("Café Supremo");
        mark.setLogo("logo_supremo.png");
        mark.persist();
    }

    @Test
    void testCreateCoffeeWithFullMark() {
        String requestBody = """
        {
            "name": "Café Gourmet",
            "quantity": 30,
            "weight": 1.5,
            "pricePurchase": 22.90,
            "priceSale": 35.00,
            "mark": {
                "id": %d,
                "name": "%s",
                "logo": "%s"
            }
        }
        """.formatted(mark.id, mark.getName(), mark.getLogo());

        given()
            .contentType(ContentType.JSON)
            .body(requestBody)
        .when()
            .post("/coffees")
        .then()
            .statusCode(201)
            .body("id", notNullValue())
            .body("name", equalTo("Café Gourmet"))
            .body("quantity", equalTo(30))
            .body("weight", equalTo(1.5f))
            .body("pricePurchase", equalTo(22.90f))
            .body("priceSale", equalTo(35.00f))
            .body("mark.id", equalTo(mark.id.intValue()))
            .body("mark.name", equalTo("Café Supremo"))
            .body("mark.logo", equalTo("logo_supremo.png"));
    }

    @Test
    void testGetCoffeeByIdWithMarkDetails() {
        Coffee coffee = new Coffee();
        coffee.setName("Café Forte");
        coffee.setQuantity(60);
        coffee.setWeight(1.0);
        coffee.setPricePurchase(18.0);
        coffee.setPriceSale(28.0);
        coffee.setMark(mark);

        coffee.persist();

        given()
        .when()
            .get("/coffees/" + coffee.id)
        .then()
            .statusCode(200)
            .body("name", equalTo("Café Forte"))
            .body("mark.name", equalTo(mark.getName()))
            .body("mark.logo", equalTo(mark.getLogo()));
    }
}