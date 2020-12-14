package interview;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;

public class ReqresApiTest {

    private static final String BASE_URI = "https://reqres.in";

    private static final String API_USERS = "/api/users";

    private RequestSpecification given() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        return RestAssured.given()
                .baseUri(BASE_URI)
                .contentType("application/json")
                .log().uri();
    }

    @Test
    public void getListUsers() {
        given().
                when().
                basePath(API_USERS).
                param("page", 2).
                get().
                then().statusCode(200).
                assertThat().
                body("page", equalTo(2)).
                body("per_page", equalTo(6)).
                body("total", equalTo(12)).
                body("total_pages", equalTo(2)).
                body("support.url", equalTo("https://reqres.in/#support-heading")).
                body("support.text", equalTo("To keep ReqRes free, contributions towards server costs are appreciated!"));
    }

    @Test
    public void getSingleUser() {
        given().
                when().
                basePath(API_USERS + "/" + 2).
                get().
                then().statusCode(200).
                assertThat().
                body("data.id", equalTo(2)).
                body("data.email", equalTo("janet.weaver@reqres.in")).
                body("data.first_name", equalTo("Janet")).
                body("data.last_name", equalTo("Weaver")).
                body("data.avatar", equalTo("https://reqres.in/img/faces/2-image.jpg")).
                body("support.url", equalTo("https://reqres.in/#support-heading")).
                body("support.text", equalTo("To keep ReqRes free, contributions towards server costs are appreciated!"));
    }

    @Test
    public void singleUserNotFound() {
        given().
                when().
                basePath(API_USERS + "/" + 23).
                get().
                then().statusCode(404);
    }

    @Test
    public void createNewUser() {
        String userName = "morpheus";
        String userJob = "leader";

        JSONObject requestParams = new JSONObject();
        requestParams.put("name", userName);
        requestParams.put("job", userJob);

        given().
                when().
                basePath(API_USERS).
                body(requestParams.toString()).
                post().
                then().statusCode(201).
                assertThat().
                body("name", equalTo(userName)).
                body("job", equalTo(userJob));
    }
}
