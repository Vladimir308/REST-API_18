package tests;

import api.AuthorizationApi;
import api.BooksApi;
import componentsUI.CartComponentUI;
import models.LoginResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;

@Tag("AllTests")
@DisplayName("Delete book from collection")
public class DeleteAndAddBookTest extends TestBase {
    @Test
    public void addBook() {
        AuthorizationApi authorization = new AuthorizationApi();
        LoginResponseModel authResponse = authorization.authorizeTestAPI();

        BooksApi cartActions = new BooksApi(authResponse);

        authorization.authorizeTestAPI();
        cartActions.addBookAPI();

    }

    @Test
    @Tag("DeleteBookAPI")
    public void deleteBookFromCollectionAPI() {
        AuthorizationApi authorization = new AuthorizationApi();
        LoginResponseModel authResponse = authorization.authorizeTestAPI();

        BooksApi cartActions = new BooksApi(authResponse);

        authorization.authorizeTestAPI();
        cartActions.deleteBookAPI();
        cartActions.addBookAPI();
        cartActions.deleteBookAPI();
    }


    @Tag("Delete_book_with_API_and_UI")
    @Test
    public void deleteBookFromCollection() {
        AuthorizationApi authorization = new AuthorizationApi();
        CartComponentUI cartActionsUI = new CartComponentUI();
        LoginResponseModel authResponse = authorization.authorizeTestAPI();

        BooksApi cartActions = new BooksApi(authResponse);

        step("API actions", () -> {
            cartActions.deleteBookAPI();
            cartActions.addBookAPI();
        });
        step("UI actions", () -> {
            cartActionsUI.openCart()
                    .checkBookInCart()
                    .deleteBookInCart()
                    .confirmDeleteBook();
        });
    }
}


