package ch.zli.m223;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import ch.zli.m223.model.Entry;
import groovy.xml.Entity;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;
import java.time.LocalDateTime;

@QuarkusTest
public class EntryResourceTest {

    @Test
    public void testIndexEndpoint() {
        given()
          .when().get("/entries")
          .then()
             .statusCode(200)
             .body(is("[]"));
    }

    @Test
    public void testDeleteEntry() {
        long id = given().header("Content-type", "application/json")
            .body(new Entry(LocalDateTime.now(), LocalDateTime.now().plus(Duration.ofHours(1))))
            .when().post("/entries").getBody().as(Entry.class).getId();
        given()
            .when().delete("/entries/" + id)
            .then()
                .statusCode(204);
        given()
            .when().get("/entries/" + id)
            .then()
                .statusCode(204)
                .body(is(""));
    }

    @Test
    public void testUpdateEndpoint() {
        Long id = given()
                .header("Content-type", "application/json")
                .body(new Entry(LocalDateTime.now(), LocalDateTime.now()))
                .when().post("/entries").getBody().as(Entry.class).getId();
        LocalDateTime expLocalDateTime = LocalDateTime.now();
        given()
                .header("Content-type", "application/json")
                .body(new Entry(id, expLocalDateTime, LocalDateTime.now()))
                .when().put("/entries/")
                .then()
                .statusCode(204);
        LocalDateTime actualLocalDateTime = given()
                .when().get("/entries/" + id).getBody().as(Entry.class).getCheckIn();
        assertEquals(expLocalDateTime.getSecond(), actualLocalDateTime.getSecond());
    }

}