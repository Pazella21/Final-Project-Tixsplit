
package tests.auth;

import base.BaseTest;
import body.auth.LoginBody;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class LoginTest extends BaseTest {

    LoginBody loginBody = new LoginBody();

    // TC-AUTH-006: Login dengan email dan password benar
    @Test
    public void testLoginSuccess() {

        Response response =
                given()
                        .contentType("application/json")
                        .accept("application/json")
                        .body(loginBody.loginData(
                                "auth006.email",
                                "auth006.password"
                        ))
                        .when()
                        .post("/api/auth/login")
                        .then()
                        .log().all()
                        .statusCode(200)
                        .extract()
                        .response();

        System.out.println("AUTH-006: Login berhasil!");
        System.out.println("Response: " + response.asString());
    }

    // TC-AUTH-007: Login dengan password salah
    @Test
    public void testLoginWrongPassword() {

        Response response =
                given()
                        .contentType("application/json")
                        .accept("application/json")
                        .body(loginBody.loginData(
                                "auth007.email",
                                "auth007.password"
                        ))
                        .when()
                        .post("/api/auth/login")
                        .then()
                        .log().all()
                        .statusCode(401)
                        .extract()
                        .response();

        System.out.println("AUTH-007: Login ditolak karena password salah.");
        System.out.println("Response: " + response.asString());
    }

    // TC-AUTH-008: Login dengan email belum terdaftar
    @Test
    public void testLoginUnregisteredEmail() {

        Response response =
                given()
                        .contentType("application/json")
                        .accept("application/json")
                        .body(loginBody.loginData(
                                "auth008.email",
                                "auth008.password"
                        ))
                        .when()
                        .post("/api/auth/login")
                        .then()
                        .log().all()
                        .statusCode(401)
                        .extract()
                        .response();

        System.out.println("AUTH-008: Login ditolak karena email belum terdaftar.");
        System.out.println("Response: " + response.asString());
    }
}
