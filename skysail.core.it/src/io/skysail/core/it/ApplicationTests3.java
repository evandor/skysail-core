package io.skysail.core.it;

import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ApplicationTests3 {

    @Test
    public void createsEntity_postingJSON() {
      //assertThat(1 == 1);
        assertTrue(1 == 1);
//        given().
//        config(RestAssured.config().jsonConfig(jsonConfig().numberReturnType(BIG_DECIMAL))).
//when().
//        get("/price").
//then().
//        body("price", is(new BigDecimal(12.12));
    }

    @Test
    //@Ignore
    public void createsEntity_postingJSON2() {
        get("/root/apps")
            .then()
        .body("name", hasItems("root","demo"));
    }
}
