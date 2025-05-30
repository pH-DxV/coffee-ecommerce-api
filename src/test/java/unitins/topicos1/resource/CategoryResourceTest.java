package unitins.topicos1.resource;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import unitins.topicos1.Category.dto.CategoryDTO;
import unitins.topicos1.Category.model.Category;
import unitins.topicos1.Category.repository.CategoryRespository;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

@QuarkusTest
public class CategoryResourceTest {

    @Inject
    CategoryRespository categoryRepository;

    @BeforeEach
    @Transactional
    public void setup() {
        categoryRepository.deleteAll();
        // Garante que o banco de dados está limpo antes de cada teste.
        // Isso é crucial para evitar que testes interfiram uns nos outros.
    }

    // Testes para o método create
    @Test
    public void testCreateCategory() {
        CategoryDTO validDTO = new CategoryDTO("Café Torrado", "Descrição do Café Torrado", "Médio", "Brasil");

        given()
                .contentType(ContentType.JSON)
                .body(validDTO)
                .when().post("/categories")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON) // Adicionado ContentType para a resposta
                .body("name", equalTo("Café Torrado"))
                .body("description", equalTo("Descrição do Café Torrado"))
                .body("roastLevel", equalTo("Médio"))
                .body("origin", equalTo("Brasil"));
    }

    @Test
    public void testCreateCategoryWithInvalidName() {
        CategoryDTO invalidDTO = new CategoryDTO(null, "Descrição", "Médio", "Brasil");

        given()
                .contentType(ContentType.JSON)
                .body(invalidDTO)
                .when().post("/categories")
                .then()
                .statusCode(400);
    }

    @Test
    public void testCreateCategoryWithInvalidDescription() {
        CategoryDTO invalidDTO = new CategoryDTO("Café", null, "Médio", "Brasil");

        given()
                .contentType(ContentType.JSON)
                .body(invalidDTO)
                .when().post("/categories")
                .then()
                .statusCode(400);
    }

    @Test
    public void testCreateCategoryWithInvalidRoastLevel() {
        CategoryDTO invalidDTO = new CategoryDTO("Café", "Descrição", null, "Brasil");

        given()
                .contentType(ContentType.JSON)
                .body(invalidDTO)
                .when().post("/categories")
                .then()
                .statusCode(400);
    }

    @Test
    public void testCreateCategoryWithInvalidOrigin() {
        CategoryDTO invalidDTO = new CategoryDTO("Café", "Descrição", "Médio", null);

        given()
                .contentType(ContentType.JSON)
                .body(invalidDTO)
                .when().post("/categories")
                .then()
                .statusCode(400);
    }

    // Testes para o método delete
    @Test
    @Transactional
    public void testDeleteCategory() {
        Category category = new Category();
        category.setName("Categoria para deletar");
        category.setDescription("Descrição para deletar");
        category.setRoastLevel("Torra Média");
        category.setOrigin("Origem");
        categoryRepository.persist(category);

        given()
                .pathParam("id", category.getId())
                .when().delete("/categories/{id}")
                .then()
                .statusCode(204);

        // Verifica se a categoria foi realmente deletada
        given()
                .pathParam("id", category.getId())
                .when().get("/categories/search/id/{id}")
                .then()
                .statusCode(200) // O código correto é 200, mesmo que o corpo seja nulo.
                .contentType(ContentType.JSON)
                .body(equalTo(null));
    }

    @Test
    public void testDeleteCategoryNotFound() {
        given()
                .pathParam("id", 99999) // ID que não existe
                .when().delete("/categories/{id}")
                .then()
                .statusCode(404); // Mantendo o código 404 para NOT FOUND
    }

    // Testes para o método update
    @Test
    @Transactional
    public void testUpdateCategory() {
        Category category = new Category();
        category.setName("Categoria Original");
        category.setDescription("Descrição Original");
        category.setRoastLevel("Torra Clara");
        category.setOrigin("Origem A");
        categoryRepository.persist(category);

        CategoryDTO updatedDTO = new CategoryDTO("Categoria Atualizada", "Descrição Atualizada", "Torra Escura", "Origem B");

        given()
                .contentType(ContentType.JSON)
                .pathParam("id", category.getId())
                .body(updatedDTO)
                .when().put("/categories/{id}")
                .then()
                .statusCode(204); // Status code correto para update sem retorno de body

        // Verifica se a categoria foi realmente atualizada
        given()
                .pathParam("id", category.getId())
                .when().get("/categories/search/id/{id}")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON) // Adicionado ContentType para a resposta
                .body("name", equalTo("Categoria Atualizada"))
                .body("description", equalTo("Descrição Atualizada"))
                .body("roastLevel", equalTo("Torra Escura"))
                .body("origin", equalTo("Origem B"));
    }

    @Test
    public void testUpdateCategoryNotFound() {
        CategoryDTO updatedDTO = new CategoryDTO("Categoria Atualizada", "Descrição Atualizada", "Torra Escura", "Origem B");
        given()
                .contentType(ContentType.JSON)
                .pathParam("id", 99999) // ID que não existe
                .body(updatedDTO)
                .when().put("/categories/{id}")
                .then()
                .statusCode(500); //  Deve retornar 500
    }

    // Testes para o método findAll
    @Test
    @Transactional
    public void testFindAllCategories() {
        // Cria algumas categorias para testar
        Category category1 = new Category();
        category1.setName("Categoria 1");
        category1.setDescription("Desc 1");
        category1.setRoastLevel("Media");
        category1.setOrigin("Origem1");
        categoryRepository.persist(category1);

        Category category2 = new Category();
        category2.setName("Categoria 2");
        category2.setDescription("Desc 2");
        category2.setRoastLevel("Forte");
        category2.setOrigin("Origem2");
        categoryRepository.persist(category2);

        given()
                .when().get("/categories")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)  // Adicionado ContentType para a resposta
                .body("", hasSize(2)) // Verifica o tamanho da lista
                .body("[0].name", equalTo("Categoria 1"))
                .body("[1].name", equalTo("Categoria 2"));
    }

    @Test
    @Transactional
    public void testFindAllCategoriesWithPagination() {
        // Cria algumas categorias para testar
        for (int i = 0; i < 5; i++) {
            Category category = new Category();
            category.setName("Categoria " + i);
            category.setDescription("Desc " + i);
            category.setRoastLevel("Media");
            category.setOrigin("Origem");
            categoryRepository.persist(category);
        }

        given()
                .queryParam("page", 0)
                .queryParam("pageSize", 3)
                .when().get("/categories")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON) // Adicionado ContentType para a resposta
                .body("", hasSize(3))
                .body("[0].name", equalTo("Categoria 0"))
                .body("[1].name", equalTo("Categoria 1"))
                .body("[2].name", equalTo("Categoria 2"));

        given()
                .queryParam("page", 1)
                .queryParam("pageSize", 3)
                .when().get("/categories")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON) // Adicionado ContentType para a resposta
                .body("", hasSize(2))
                .body("[0].name", equalTo("Categoria 3"))
                .body("[1].name", equalTo("Categoria 4"));
    }

    @Test
    public void testFindAllCategoriesWithInvalidPagination() {
        given()
                .queryParam("page", -1)
                .queryParam("pageSize", 3)
                .when().get("/categories")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON); // Mesmo com paginação inválida, deve retornar 200 OK
    }

    // Testes para o método findById
    @Test
    @Transactional
    public void testFindCategoryById() {
        Category category = new Category();
        category.setName("Categoria X");
        category.setDescription("Desc X");
        category.setRoastLevel("Forte");
        category.setOrigin("Origem X");
        categoryRepository.persist(category);

        given()
                .pathParam("id", category.getId())
                .when().get("/categories/search/id/{id}")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON) // Adicionado ContentType para a resposta
                .body("name", equalTo("Categoria X"))
                .body("description", equalTo("Desc X"));
    }

    @Test
    public void testFindCategoryByIdNotFound() {
        given()
                .pathParam("id", 99999) // ID que não existe
                .when().get("/categories/search/id/{id}")
                .then()
                .statusCode(200) //findById retorna null e o endpoint retorna 200 com body nulo
                .contentType(ContentType.JSON)
                .body(equalTo(null));
    }

    // Testes para o método findByName
    @Test
    @Transactional
    public void testFindCategoryByName() {
        Category category1 = new Category();
        category1.setName("Café Especial");
        category1.setDescription("Descrição 1");
        category1.setRoastLevel("Média");
        category1.setOrigin("Brasil");
        categoryRepository.persist(category1);

        Category category2 = new Category();
        category2.setName("Café Especial");
        category2.setDescription("Descrição 2");
        category2.setRoastLevel("Forte");
        category2.setOrigin("Colombia");
        categoryRepository.persist(category2);

        given()
                .pathParam("name", "Café Especial")
                .when().get("/categories/search/name/{name}")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON) // Adicionado ContentType para a resposta
                .body("", hasSize(2))
                .body("[0].description", equalTo("Descrição 1"))
                .body("[1].description", equalTo("Descrição 2"));
    }

    @Test
    public void testFindCategoryByNameNotFound() {
        given()
                .pathParam("name", "Categoria Inexistente")
                .when().get("/categories/search/name/{name}")
                .then()
                .statusCode(200) //findByName retorna lista vazia
                .contentType(ContentType.JSON)
                .body("", hasSize(0));
    }

    // Testes para contagem
    @Test
    @Transactional
    public void testCountCategories() {
        // Cria algumas categorias
        Category category1 = new Category();
        category1.setName("Cat 1");
        category1.setDescription("Desc 1");
        category1.setRoastLevel("Med");
        category1.setOrigin("Origin 1");
        categoryRepository.persist(category1);

        Category category2 = new Category();
        category2.setName("Cat 2");
        category2.setDescription("Desc 2");
        category2.setRoastLevel("Forte");
        category2.setOrigin("Origin 2");
        categoryRepository.persist(category2);

        long expectedCount = categoryRepository.count();

        given()
                .when().get("/categories/count")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON) // Adicionado ContentType para a resposta
                .body(equalTo((int) expectedCount));
    }
}

