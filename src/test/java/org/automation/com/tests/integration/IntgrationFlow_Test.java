package org.automation.com.tests.integration;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import org.automation.com.base.BaseTest;
import org.automation.com.endpoints.APIConstants;
import org.automation.com.pojos.Booking;
import org.automation.com.pojos.BookingResponse;
import org.automation.com.utils.PropertyReader;
import org.hamcrest.Matchers;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class IntgrationFlow_Test extends BaseTest {
    //with ITestContext, we can transfer data between scripts.
    //here token will be available in all TCs within this class.

    @Description("verify if booking id us created")
    @Test(priority=1)
    public void createBooking(ITestContext iTestContext){
        iTestContext.setAttribute("token",getToken());
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

        iTestContext.setAttribute("bookingid",bookingResponse.getBookingid());
        System.out.println("End of TC 1");
    }
    @Description("verify if booking id exists")
    @Test(priority=2)
    public void verifyBooking(ITestContext iTestContext)
    {
        Integer bookingID= (Integer) iTestContext.getAttribute("bookingid");
        //booking id
        String basePathGET=APIConstants.CREATE_UPDATE_BOOKING_URL+"/"+bookingID;
        System.out.println(basePathGET);

        requestSpecification.basePath(basePathGET);
        response=RestAssured.given(requestSpecification)
                .when().get();
        validatableResponse=response.then().log().all();
        //validatable assertion
        validatableResponse.statusCode(200);
        Booking booking= payloadManager.getResponseFromJSON(response.asString());
        assertThat(booking.getFirstname()).isNotEmpty().isNotNull();
        assertThat(booking.getLastname()).isNotEmpty().isNotNull();
        assertThat(booking.getLastname()).isEqualTo(PropertyReader.readKey("booking.get.lastname"));
        assertThat(booking.getFirstname()).isEqualTo(PropertyReader.readKey("booking.get.firstname"));
        System.out.println("End of TC 2");
    }
    @Description("update booking id")
    @Test(priority=3)
    public void updateByBookingID(ITestContext iTestContext){
        String token= (String) iTestContext.getAttribute("token");
        Integer bookingID= (Integer) iTestContext.getAttribute("bookingid");
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL+"/"+bookingID);
        response= RestAssured.given(requestSpecification).cookie("token",token).when().body(payloadManager.fullUpdatePayloadAsString()).put();
        validatableResponse=response.then().log().all();
        validatableResponse.statusCode(200);
        //Default assertions by RA
        System.out.println(PropertyReader.readKey("booking.put.firstname"));
        validatableResponse.body("firstname", Matchers.equalTo("Dipa"));
        Booking booking=payloadManager.getResponseFromJSON(response.asString());
        //AseertJ
        assertThat(booking.getFirstname()).isEqualTo("Dipa").isNotBlank();
        assertThat(booking.getLastname()).isEqualTo("Chavan").isNotBlank();
        assertThat(booking.getFirstname()).isEqualTo(PropertyReader.readKey("booking.put.firstname"));
        //TestNG
        assertActions.verifyStatusCode(PropertyReader.readKey("booking.put.statuscode.success"));
        System.out.println("End of TC 3");
    }
    @Description("delete booking id")
    @Test(priority=4)
    public void deleteByBookingID(ITestContext iTestContext){
        String token= (String) iTestContext.getAttribute("token");
        Integer bookingID= (Integer) iTestContext.getAttribute("bookingid");

        String basPathDELETE=APIConstants.CREATE_UPDATE_BOOKING_URL+"/"+bookingID;
        System.out.println(basPathDELETE);
        requestSpecification.basePath(basPathDELETE).cookie("token",token);
        validatableResponse=RestAssured.given().spec(requestSpecification).when().delete().then().log().all();
        validatableResponse.statusCode(201);
        System.out.println("End of TC 4");
    }
    @Description("Verify booking exists after its getting deleted")
    @Test(priority=5)
    public void verifyBookingAfterDelete(ITestContext iTestContext)
    {
        Integer bookingID= (Integer) iTestContext.getAttribute("bookingid");
        //booking id
        String basePathGET=APIConstants.CREATE_UPDATE_BOOKING_URL+"/"+bookingID;
        System.out.println(basePathGET);

        requestSpecification.basePath(basePathGET);
        response=RestAssured.given(requestSpecification)
                .when().get();
        validatableResponse=response.then().log().all();
        //validatable assertion
        validatableResponse.statusCode(404);
        System.out.println("End of TC 5");
    }


}
