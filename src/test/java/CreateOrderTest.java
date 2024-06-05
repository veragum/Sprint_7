import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.core.IsNull.notNullValue;

@RunWith(Parameterized.class)
public class CreateOrderTest {
    private CreateOrderSteps createOrderSteps = new CreateOrderSteps();

    String firstName;
    String lastName;
    String address;
    String metroStation;
    String phone;
    String rentTime;
    String deliveryDate;
    String comment;
    private String[] color;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new String[]{"BLACK", "GREY"}},
                {new String[]{"GREY"}},
                {new String[]{"BLACK"}},
                {new String[]{"null"}},
        });
    }

    public CreateOrderTest(String[] color) {
        this.color = color;
    }

    @Test
    public void responseShouldContentTrackNumber() {
        firstName = RandomStringUtils.randomAlphabetic(10);
        lastName = RandomStringUtils.randomAlphabetic(10);
        address = RandomStringUtils.randomAlphabetic(10);
        metroStation = RandomStringUtils.randomAlphabetic(10);
        phone = RandomStringUtils.randomNumeric(11);
        rentTime = RandomStringUtils.randomNumeric(1);
        deliveryDate = "2022-06-06";
        comment = RandomStringUtils.randomAlphabetic(10);

        createOrderSteps
                .createOrder(firstName, lastName, address, metroStation,
                        phone, rentTime, deliveryDate, comment, color)
                .statusCode(201)
                .body("track", notNullValue());
    }
}
