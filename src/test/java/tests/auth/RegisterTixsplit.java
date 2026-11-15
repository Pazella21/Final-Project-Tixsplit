
package tests.auth;

import base.BaseTest;
import body.auth.RegisterTixsplitBody;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.ConfigReader;
import utils.Utils;

import java.util.Locale;

import static io.restassured.RestAssured.given;

public class RegisterTixsplit extends BaseTest {

    RegisterTixsplitBody registerBody = new RegisterTixsplitBody();

    // TC-AUTH-001: Registrasi dengan data valid
    @Test
    public void testRegisterSuccess() {

        Response response =
                given()
                        .contentType("application/json")
                        .accept("application/json")
                        .body(registerBody.registerData(
                                "auth001.name",
                                "auth001.password"
                        ))
                        .when()
                        .post("/api/auth/register")
                        .then()
                        .log().all()
                        .statusCode(201)
                        .extract()
                        .response();

        System.out.println("AUTH-001: Registrasi berhasil!");
        System.out.println("Response: " + response.asString());
    }

    // TC-AUTH-002: Password tepat 8 karakter
    @Test
    public void testRegisterPasswordExactly8Characters() {

        Response response =
                given()
                        .contentType("application/json")
                        .accept("application/json")
                        .body(registerBody.registerData(
                                "auth002.name",
                                "auth002.password"
                        ))
                        .when()
                        .post("/api/auth/register")
                        .then()
                        .log().all()
                        .statusCode(201)
                        .extract()
                        .response();

        System.out.println("AUTH-002: Registrasi dengan password 8 karakter.");
        System.out.println("Response: " + response.asString());
    }

    // TC-AUTH-003: Email huruf kapital
    @Test
    public void testRegisterUppercaseEmail() {

        String email = Utils.generateRandomEmail()
                .toUpperCase(Locale.ROOT);

        // Request menggunakan email uppercase
        Response response =
                given()
                        .contentType("application/json")
                        .accept("application/json")
                        .body(
                                "{\"name\":\""
                                        + ConfigReader.getProperty("auth003.name")
                                        + "\",\"email\":\""
                                        + email
                                        + "\",\"password\":\""
                                        + ConfigReader.getProperty("auth003.password")
                                        + "\"}"
                        )
                        .when()
                        .post("/api/auth/register")
                        .then()
                        .log().all()
                        .statusCode(201)
                        .extract()
                        .response();

        System.out.println("AUTH-003: Registrasi dengan email uppercase.");
        System.out.println("Response: " + response.asString());
    }

    // TC-AUTH-004: Password kurang dari 8 karakter
    @Test
    public void testRegisterPasswordLessThan8Characters() {

        Response response =
                given()
                        .contentType("application/json")
                        .accept("application/json")
                        .body(registerBody.registerData(
                                "auth004.name",
                                "auth004.password"
                        ))
                        .when()
                        .post("/api/auth/register")
                        .then()
                        .log().all()
                        .extract()
                        .response();

        System.out.println("AUTH-004: Registrasi dengan password kurang dari 8 karakter.");
        System.out.println("HTTP Status: " + response.getStatusCode());
        System.out.println("Response: " + response.asString());

        // Status aktual perlu diperiksa sebelum menetapkan assertion.
    }

    // TC-AUTH-005: Email sudah terdaftar
    @Test
    public void testRegisterExistingEmail() {

        Response response =
                given()
                        .contentType("application/json")
                        .accept("application/json")
                        .body(registerBody.registerDataWithEmail(
                                "auth005.name",
                                "auth005.email",
                                "auth005.password"
                        ))
                        .when()
                        .post("/api/auth/register")
                        .then()
                        .log().all()
                        .statusCode(409)
                        .extract()
                        .response();

        System.out.println("AUTH-005: Registrasi menggunakan email yang sudah terdaftar.");
        System.out.println("Response: " + response.asString());
    }
}
