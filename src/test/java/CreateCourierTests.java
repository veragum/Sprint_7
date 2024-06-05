
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;


public class CourierTests {
    private CourierSteps courierSteps = new CourierSteps();
    private String login;
    private String password;
    @Test
    public void shouldReturnOkTrue () {
        login = RandomStringUtils.randomAlphabetic(10);
        password = RandomStringUtils.randomAlphabetic(10);

        courierSteps
                .createCourier(login, password, "")
                .statusCode(201)
                .body("ok", is(true));
    }

    @Test
    public void shouldReturnId(){
        login = RandomStringUtils.randomAlphabetic(10);
        password = RandomStringUtils.randomAlphabetic(10);


        courierSteps
                .createCourier(login, password, "");

        courierSteps
                .login(login, password)
                .statusCode(200)
                .body("id", notNullValue());
    }

@After
    public void tearDown(){
       Integer id = courierSteps.login(login, password)
                .extract().body().path("id");
       if(id !=null) {
           courierSteps.delete(id);
       }
}
}