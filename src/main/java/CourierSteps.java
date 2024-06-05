import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class CreateCourierSteps {
    private static final String HOST = "https://qa-scooter.praktikum-services.ru";
    private static final String COURIER = "/api/v1/courier";
    private static final String LOGIN = "/api/v1/courier/login";
    private static final String DELETE = "/api/v1/courier/{id}";

    @Step("Create courier")
    public ValidatableResponse createCourier(String login, String password, String firstName) {
        return given().log().ifValidationFails()
                .contentType(ContentType.JSON)
                .baseUri(HOST)
                .body("{\n" +
                        "    \"login\": \"" + login + "\",\n" +
                        "    \"password\": \"" + password + "\",\n" +
                        "    \"firstName\": \"" + firstName + "\"\n" +
                        "}")
                .when()
                .post(COURIER)
                .then();
    }

    @Step("Log in courier")
    public ValidatableResponse login(String login, String password) {
        return given()
                .contentType(ContentType.JSON)
                .baseUri(HOST)
                .body("{\n" +
                        "    \"login\": \"" + login + "\",\n" +
                        "    \"password\": \"" + password + "\"\n" +
                        "}")
                .when()
                .post(LOGIN)
                .then();
    }

    @Step("Delete courier")
    public ValidatableResponse delete(int id) {
        return given()
                .contentType(ContentType.JSON)
                .baseUri(HOST)
                .pathParam("id", id)
                .when()
                .delete(DELETE)
                .then();
    }
}
