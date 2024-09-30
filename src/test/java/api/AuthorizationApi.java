package api;

import models.CredentialsModel;
import models.LoginResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.openqa.selenium.Cookie;
import tests.TestBase;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static specs.ResponseRequestSpecs.requestSpec;
import static specs.ResponseRequestSpecs.responseSpec200;


@Tag("AddBook")
@DisplayName("Add book to the collection")
public class AuthorizationApi extends TestBase {

    CredentialsModel authData;

    {
        authData = new CredentialsModel();
    }

    private LoginResponseModel authResponse;

    public LoginResponseModel authorizeTestAPI() {

        authData.setUserName("vladimir30812");
        authData.setPassword("Vladimir30812&");

        authResponse = step("Authorize via API", () ->
                given(requestSpec)
                        .body(authData)
                        .when()
                        .post("/Account/v1/Login")
                        .then()
                        .spec(responseSpec200)
                        .extract().as(LoginResponseModel.class)
        );

        step("Set cookies and open profile page", () -> {
            open("/favicon.ico");
            getWebDriver().manage().addCookie(new Cookie("userID", authResponse.getUserId()));
            getWebDriver().manage().addCookie(new Cookie("expires", authResponse.getExpires()));
            getWebDriver().manage().addCookie(new Cookie("token", authResponse.getToken()));
        });
        return authResponse;
    }
}
