import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.core.IsEqual.equalTo;

public class APIAppTests {

    @BeforeAll
    public static void setUpAuth() {
        PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
        authScheme.setUserName("admin");
        authScheme.setPassword("admin");
        RestAssured.authentication = authScheme;
    }

    @BeforeAll
    public static void setUpErrorLogging() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    @DisplayName("POST /api/countries")
    public void createCountry() {

        int idOfCountry =  given()
                            .body("{\n" +
                                    "\"countryName\": \"RU\" \n" + "}")
                            .contentType("application/json")
                            .when()
                            .post("/api/countries")
                            .then()
                            .statusCode(201)
                            .body("countryName", equalTo("RU"))
                            .extract()
                        .response().path("id");

        when().delete("/api/countries/" + idOfCountry);
    }

    @Test
    @DisplayName("GET /api/countries")
    public void getCountryById() {
        int idOfCountry =
                given()
                        .body("{\n" + "\"countryName\": \"RU\" \n" + "}")
                        .contentType("application/json")
                        .when()
                        .post("/api/countries")
                        .body()
                        .path("id");
        when()
                .get("/api/countries/" + idOfCountry)
                .then()
                .statusCode(200)
                .body("countryName", equalTo("RU"));
        when().delete("/api/countries/" + idOfCountry);
    }

    @Test
    @DisplayName("DELETE /api/countries")
    public void deleteCountryById() {
        int idOfCountry =
                given()
                        .body("{\n" + "\"countryName\": \"RU\" \n" + "}")
                        .contentType("application/json")
                        .when()
                        .post("/api/countries")
                        .body()
                        .path("id");
        when()
                .delete("/api/countries/" + idOfCountry)
                .then()
                .statusCode(204);
    }

    @Test
    @DisplayName("PATCH /api/countries")
    public void patchCountryById() {

        given()
                .body("{\n" + "\"id\": 8,\n" + "\"countryName\": \"SS\" \n" + "}")
                .contentType("application/json")
                .when()
                .patch("/api/countries/8")
                .then()
                .statusCode(200)
                .body("countryName", equalTo("SS"))
                .body("id", equalTo(8))
                .body("locations", equalTo(null));
    }

    @Test
    @DisplayName("PUT /api/countries")
    public void putCountryById() {
        given()
                .body("{\n" + "\"id\": 6,\n" + "\"countryName\": \"SZ\" \n" + "}")
                .contentType("application/json")
                .when()
                .put("/api/countries/6")
                .then()
                .statusCode(200)
                .body("countryName", equalTo("SZ"))
                .body("id", equalTo(6))
                .body("locations", equalTo(null));
    }

}
