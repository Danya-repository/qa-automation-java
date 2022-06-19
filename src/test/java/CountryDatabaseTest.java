import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class CountryDatabaseTest {
    private static Connection connection;
    private int idOfCountry;
    private int idNotDoubleCountry;
    private static final String nameOfCountry = "RU";

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

    @BeforeAll
    public static void openConnection() throws SQLException {
        connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:6432/app-db",
                "app-db-admin",
                "P@ssw0rd"
        );
    }

    @AfterAll
    public static void closeConnection() throws SQLException {
        connection.close();
    }

    @BeforeEach
    public void createCountryBeforeTests() throws SQLException {
        Statement sqlSelect = connection.createStatement();
        ResultSet resultSet = sqlSelect.executeQuery("SELECT * FROM country order by id DESC limit 1");
        PreparedStatement sqlInsert = connection
                .prepareStatement("INSERT INTO country(id, country_name) VALUES(?,?)");

        int startID = resultSet.next() ? resultSet.getInt(1) : -1;
        idOfCountry = startID + 1;

        sqlInsert.setInt(1, idOfCountry);
        sqlInsert.setString(2, nameOfCountry);
        sqlInsert.executeUpdate();
    }

    @AfterEach
    public void deleteCountryAfterTests() throws SQLException {
        PreparedStatement deleteCountry = connection.prepareStatement("DELETE FROM country WHERE id =?");
        deleteCountry.setInt(1,idOfCountry);
        deleteCountry.executeUpdate();

        PreparedStatement sqlDeleteUniqueCountry = connection.prepareStatement("DELETE FROM country WHERE id =?");
        sqlDeleteUniqueCountry.setInt(1, idNotDoubleCountry);
        sqlDeleteUniqueCountry.executeUpdate();
    }

    @Test
    @DisplayName("Get country")
    public void getCountry() {
        when()
                .get("/api/countries/" + idOfCountry)
                .then()
                .statusCode(200)
                .body("id", not(isEmptyOrNullString()),
                        "countryName", is(nameOfCountry));
    }

    @Test
    @DisplayName("Create country without double")
    public void createCountry() throws SQLException {
        Collection<Integer> countryArray = new ArrayList<>();
        Statement sql = connection.createStatement();

        idNotDoubleCountry = given()
                .contentType(ContentType.JSON)
                .body("{\n" + " \"countryName\": \"GC\"\n" + "}")
                .when()
                .post("/api/countries")
                .then()
                .statusCode(201)
                .body("id", not(empty()))
                .extract()
                .path("id");

        ResultSet resultSet = sql.executeQuery("SELECT * FROM country WHERE country_name = 'GC'");

        while (resultSet.next()) {
            countryArray.add(resultSet.getInt(1));
        }

        assertThat(countryArray.size(), is(1));
        assertThat(countryArray, hasItem(idNotDoubleCountry));
    }

    @Test
    @DisplayName("Create double country")
    public void createDoubleCountry() throws SQLException {
        PreparedStatement sqlUpdate = connection
                .prepareStatement("UPDATE public.country SET country_name = 'OO' WHERE id = ?");
        sqlUpdate.setInt(1, idOfCountry);
        sqlUpdate.executeUpdate();

        given()
                .contentType(ContentType.JSON)
                .body("{\n" + " \"countryName\": \"OO\"\n" + "}")
                .when()
                .post("/api/countries")
                .then()
                .statusCode(500);
    }

    @Test
    @DisplayName("Update country")
    public void updateCountry() throws SQLException {
        Collection<Integer> countryArray = new ArrayList<>();
        Statement sql = connection.createStatement();

        given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        " \"id\": \"" + idOfCountry + "\",\n" +
                        " \"countryName\": \"BH\"\n" + "}")
                .when()
                .patch("/api/countries/" + idOfCountry)
                .then()
                .statusCode(200)
                .body("id", is(idOfCountry), "countryName", is("BH"));
        ResultSet resultSet = sql.executeQuery("SELECT * FROM country WHERE country_name = 'BH'");

        while (resultSet.next()) {
            countryArray.add(resultSet.getInt(1));
        }

        assertThat(countryArray.size(), is(1));
        assertThat(countryArray, hasItem(idOfCountry));
    }

    @Test
    @DisplayName("Delete country")
    public void deleteCountry() throws SQLException {
        Collection<Integer> countryArray = new ArrayList<>();
        Statement sql = connection.createStatement();

        when()
                .delete(String.format("/api/countries/%s", idOfCountry))
                .then()
                .statusCode(204);

        ResultSet resultSet = sql.executeQuery(String.format("SELECT * FROM country WHERE id = '%s'", idOfCountry));

        while (resultSet.next()) {
            countryArray.add(resultSet.getInt(1));
        }

        assertThat(countryArray.size(), is(0));
    }
}