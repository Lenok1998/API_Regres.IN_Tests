package tests;

import data.TestData;
import io.qameta.allure.*;
import models.GetUsersResponseModel;
import models.UserBodyModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static spec.Spec.*;

@Owner("Малышева Елена")
@Story("Дипломный проект")
@Feature("Автоматизация API для Regres.IN")
@Severity(SeverityLevel.NORMAL)



public class ApiUserTests extends TestBase{
    TestData data = new TestData();

    private final static String GET_LIST_USER_URL = "/users?page=",
            USERS_OPERATIONS_URL = "/users/";



@Test
    @Tag("API")
    @DisplayName("Выборка пользователей со второй страницы")
    void getUsersSuccessTestSecondPage() {
        GetUsersResponseModel response = step("Отправляем запрос на выборку", () -> given()
                .spec(successfulRequest)
                .when()
                .get(GET_LIST_USER_URL + "2")
                .then()
                .spec(successfulResponse)
                .extract()
                .response()
                .as(GetUsersResponseModel.class));
        step("Проверяем page id", () ->
                assertEquals("2", response.getPage()));
        step("Проверяем количество элементов в запросе", () ->
                assertEquals(data.numberOfDataPerPage, response.getData().length));

    }


    @Test
    @Tag("API")
    @DisplayName("Создание пользователя")
    void createUserSuccessTest() {
        UserBodyModel userBodyModel = new UserBodyModel();
        userBodyModel.setName(data.name);
        userBodyModel.setJob(data.job);
        UserBodyModel response = step("Отправляем запрос на создание пользователя", () -> given()
                .spec(successfulRequest)
                .when()
                .body(userBodyModel)
                .post(USERS_OPERATIONS_URL)
                .then()
                .spec(loggingResponse)
                .statusCode(201)
                .extract()
                .as(UserBodyModel.class));
        step("Проверяем имя", () ->
                assertEquals(data.name, response.getName()));
        step("Проверяем должность", () ->
                assertEquals(data.job, response.getJob()));

    }

    @Test
    @Tag("API")
    @DisplayName("Обновление данных существующего пользователя через метод PUT")
    void updateUserSuccessTestPut() {
        UserBodyModel userBodyModel = new UserBodyModel();
        userBodyModel.setName(data.name);
        userBodyModel.setJob(data.job);
        UserBodyModel response = step("Отправляем запрос на обновление данных", () -> given()
                .spec(successfulRequest)
                .when()
                .body(userBodyModel)
                .put(USERS_OPERATIONS_URL + data.userId)
                .then()
                .spec(successfulResponse)
                .extract()
                .as(UserBodyModel.class));
        step("Проверяем имя", () ->
                assertEquals(data.name, response.getName()));
        step("Проверяем должность", () ->
                assertEquals(data.job, response.getJob()));

    }

    @Test
    @Tag("API")
    @DisplayName("Обновление данных пользователя через метод patch")
    void updateUserSuccessTestPatch() {
        UserBodyModel userBodyModel = new UserBodyModel();
        userBodyModel.setName(data.name);
        userBodyModel.setJob(data.job);
        UserBodyModel response = step("Отправляем запрос обновления данных", () -> given()
                .spec(successfulRequest)
                .when()
                .body(userBodyModel)
                .patch(USERS_OPERATIONS_URL + data.userId)
                .then()
                .spec(successfulResponse)
                .extract()
                .as(UserBodyModel.class));

        step("Проверяем имя", () ->
                assertEquals(data.name, response.getName()));
        step("Проверяем должность", () ->
                assertEquals(data.job, response.getJob()));

    }


    @Test
    @Tag("Api")
    @DisplayName("Успешное удаление пользователя")
    void deleteUserSuccessTest() {
        step("Отправляем запрос удаления данных пользователя", () ->
                given()
                        .spec(successfulRequest)
                        .when()
                        .delete(USERS_OPERATIONS_URL + data.userId)
                        .then()
                        .spec(loggingStatus)
                        .statusCode(204)
                        .extract()
                        .response());
    }

}
