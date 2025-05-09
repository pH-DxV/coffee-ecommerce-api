package unitins.topicos1.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import unitins.topicos1.Category.model.Category;
import unitins.topicos1.Category.repository.CategoryRespository;
import unitins.topicos1.Coffee.dto.CoffeeDTO;
import unitins.topicos1.Coffee.repository.CoffeeRepository;
import unitins.topicos1.Coffee.service.CoffeeService;
import unitins.topicos1.Mark.model.Mark;
import unitins.topicos1.Mark.repository.MarkRepository;

@QuarkusTest
public class CoffeeResourceTest {

  @Inject
  MarkRepository markRepository;

  @Inject
  CategoryRespository categoryRepository;

  @Inject
  CoffeeRepository coffeeRepository;


  Long markId;
  Long categoryId;

  @BeforeEach
  @Transactional
  public void setup() {

    coffeeRepository.deleteAll();
    markRepository.deleteAll();
    categoryRepository.deleteAll();
    



      Mark mark = new Mark();

      mark.setName("Nestlé");
      markRepository.persist(mark);
      markId = mark.getId();

      Category category = new Category();

      category.setName("Café Forte");
      categoryRepository.persist(category);
      categoryId = category.getId();

  }

  @Inject
  CoffeeService coffeeService;

  @Test
  public void testFindAllEndpoint() {
      given()
        .when().get("/coffee")
        .then()
            .statusCode(200);
  }

  @Test
  public void testFindAllPaginated() {
      given()
        .queryParam("page", 0)
        .queryParam("pageSize", 5)
        .when().get("/coffee")
        .then()
            .statusCode(200);
  }

  @Test
  public void testCreateCoffee() {

      CoffeeDTO dto = new CoffeeDTO(
          "Café Especial",
          10,
          0.5,
          20.0,
          35.0,
          markId, // idMark existente
          categoryId  // idCategory existente
      );

      given()
        .contentType(ContentType.JSON)
        .body(dto)
        .when().post("/coffee")
        .then()
            .statusCode(204); // Como o método POST está atualizando, retorna 204
  }

  @Test
  public void testFindById() {
      var coffee = coffeeService.create(new CoffeeDTO("Teste", 10, 0.5, 15.0, 25.0, markId, categoryId));

      given()
        .when().get("/coffee/search/id/" + coffee.id())
        .then()
            .statusCode(200)
            .body("id", equalTo(coffee.id().intValue()))
            .body("name", equalTo("Teste"));
  }

  @Test
  public void testUpdateCoffee() {
      var coffee = coffeeService.create(new CoffeeDTO("Antigo", 5, 1.0, 10.0, 15.0, markId, categoryId));

      CoffeeDTO updatedDto = new CoffeeDTO(
          "Atualizado",
          20,
          1.0,
          18.0,
          30.0,
          markId,
          categoryId
      );

      given()
        .contentType(ContentType.JSON)
        .body(updatedDto)
        .when().put("/coffee/" + coffee.id())
        .then()
            .statusCode(204);
  }

  @Test
  public void testDeleteCoffee() {
      var coffee = coffeeService.create(new CoffeeDTO("Para deletar", 2, 0.25, 12.0, 20.0, markId, categoryId));

      given()
        .when().delete("/coffee/" + coffee.id())
        .then()
            .statusCode(204);
  }
}