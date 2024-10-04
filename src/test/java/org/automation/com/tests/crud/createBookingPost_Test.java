package org.automation.com.tests.crud;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.automation.com.base.BaseTest;
import org.automation.com.endpoints.APIConstants;
import org.automation.com.pojos.BookingResponse;
import org.automation.com.utils.PropertyReader;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class createBookingPost_Test extends BaseTest {

    @Owner("Deepa")
    @Description("Create booking ID")
    @Test
    public void createBookingTest()
    {
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
        response= RestAssured.given(requestSpecification).when().body(payloadManager.createPayloadBookingAsString()).post();
        validatableResponse=response.then().log().all();
        validatableResponse.statusCode(200);
        //Default assertions by RA
        validatableResponse.body("booking.firstname", Matchers.equalTo("Deepa"));
        BookingResponse bookingResponse=payloadManager.bookingResponse(response.asString());
        //AseertJ
        assertThat(bookingResponse.getBookingid()).isNotNull();
        assertThat(bookingResponse.getBooking().getFirstname()).isEqualTo("Deepa").isNotBlank();
        assertThat(bookingResponse.getBooking().getLastname()).isEqualTo("Chavan").isNotBlank();
        assertThat(bookingResponse.getBooking().getFirstname()).isEqualTo(PropertyReader.readKey("booking.post.firstname"));
        //TestNG
        assertActions.verifyStatusCode(PropertyReader.readKey("booking.post.statuscode.success"));

        Assert.assertEquals("Deepa","Deepa");
    }


}
