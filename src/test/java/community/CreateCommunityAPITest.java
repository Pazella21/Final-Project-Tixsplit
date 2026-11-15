
package community;

import base.BaseTest;
import body.auth.LoginBody;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;
import utils.ConfigReader;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class CreateCommunityAPITest extends BaseTest {

    LoginBody loginBody = new LoginBody();

    // Mengambil token dari Login API
    private String getValidToken() {

        Response loginResponse =
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

        String token = loginResponse.path("token");

        assertNotNull(token, "Token tidak boleh kosong");

        return token;
    }

    // Membuat request body Community
    private JSONObject createCommunityBody(
            String name,
            String description
    ) {

        JSONObject requestBody = new JSONObject();

        requestBody.put("name", name);
        requestBody.put("description", description);
        requestBody.put("coverImage", "hobby");
        requestBody.put("joinPolicy", "AUTO_ACCEPT");

        return requestBody;
    }

    // COM-API-001: Create Community dengan token valid
    @Test
    public void testCreateCommunitySuccess() {

        String token = getValidToken();

        Response communityResponse =
                given()
                        .header("Authorization", "Bearer " + token)
                        .contentType("application/json")
                        .accept("application/json")
                        .body(createCommunityBody(
                                ConfigReader.getProperty("community.name"),
                                ConfigReader.getProperty("community.description")
                        ).toString())
                        .when()
                        .post("/api/communities")
                        .then()
                        .log().all()
                        .statusCode(201)
                        .extract()
                        .response();

        assertEquals(
                communityResponse.path("community.name"),
                ConfigReader.getProperty("community.name")
        );

        assertEquals(
                communityResponse.path("community.joinPolicy"),
                ConfigReader.getProperty("community.joinPolicy")
        );

        assertNotNull(
                communityResponse.path("community.id"),
                "Community ID tidak boleh kosong"
        );

        System.out.println("COM-API-001: Create Community berhasil!");
        System.out.println(
                "Community ID: " +
                        communityResponse.path("community.id")
        );
    }

    // COM-API-002: Create Community tanpa token
    @Test
    public void testCreateCommunityWithoutToken() {

        Response response =
                given()
                        .contentType("application/json")
                        .accept("application/json")
                        .body(createCommunityBody(
                                "Community No Token Automation",
                                "Testing without authentication token"
                        ).toString())
                        .when()
                        .post("/api/communities");

        response.then().log().all();

        assertEquals(response.statusCode(), 401);

        System.out.println(
                "COM-API-002: Request tanpa token berhasil ditolak."
        );
    }

    // COM-API-003: Create Community dengan invalid token
    @Test
    public void testCreateCommunityInvalidToken() {

        Response response =
                given()
                        .header(
                                "Authorization",
                                "Bearer invalid-token-12345"
                        )
                        .contentType("application/json")
                        .accept("application/json")
                        .body(createCommunityBody(
                                "Community Invalid Token Automation",
                                "Testing invalid authentication token"
                        ).toString())
                        .when()
                        .post("/api/communities");

        response.then().log().all();

        assertEquals(response.statusCode(), 401);

        System.out.println(
                "COM-API-003: Invalid token berhasil ditolak."
        );
    }

    // COM-API-004: Create Community dengan nama kosong
    @Test
    public void testCreateCommunityEmptyName() {

        String token = getValidToken();

        Response response =
                given()
                        .header("Authorization", "Bearer " + token)
                        .contentType("application/json")
                        .accept("application/json")
                        .body(createCommunityBody(
                                "",
                                "Testing empty community name"
                        ).toString())
                        .when()
                        .post("/api/communities");

        response.then().log().all();

        assertEquals(response.statusCode(), 400);

        System.out.println(
                "COM-API-004: Empty community name berhasil ditolak."
        );
    }

    // COM-API-005: Create Community dengan description kosong
    @Test
    public void testCreateCommunityEmptyDescription() {

        String token = getValidToken();

        Response response =
                given()
                        .header("Authorization", "Bearer " + token)
                        .contentType("application/json")
                        .accept("application/json")
                        .body(createCommunityBody(
                                "Community Empty Description Automation",
                                ""
                        ).toString())
                        .when()
                        .post("/api/communities");

        response.then().log().all();

        assertEquals(response.statusCode(), 201);

        System.out.println(
                "COM-API-005: Empty description diterima API."
        );
    }

    // COM-API-006: Create Community dengan nama duplikat
    @Test
    public void testCreateCommunityDuplicateName() {

        String token = getValidToken();

        String communityName = "Community Duplicate Automation";

        // Request pertama
        Response firstResponse =
                given()
                        .header("Authorization", "Bearer " + token)
                        .contentType("application/json")
                        .body(createCommunityBody(
                                communityName,
                                "First duplicate community test"
                        ).toString())
                        .when()
                        .post("/api/communities");

        firstResponse.then().log().all();

        assertEquals(firstResponse.statusCode(), 201);

        // Request kedua dengan nama yang sama
        Response secondResponse =
                given()
                        .header("Authorization", "Bearer " + token)
                        .contentType("application/json")
                        .body(createCommunityBody(
                                communityName,
                                "Second duplicate community test"
                        ).toString())
                        .when()
                        .post("/api/communities");

        secondResponse.then().log().all();

        assertEquals(secondResponse.statusCode(), 201);

        System.out.println(
                "COM-API-006: Duplicate community name diterima API."
        );
    }
}
