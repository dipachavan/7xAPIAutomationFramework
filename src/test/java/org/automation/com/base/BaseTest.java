package org.automation.com.base;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.automation.com.asserts.AssertActions;
import org.automation.com.endpoints.APIConstants;
import org.automation.com.modules.PayloadManager;
import org.testng.annotations.BeforeTest;

public class BaseTest {
    //parent class for all Tests

    public RequestSpecification requestSpecification= RestAssured.given();
    public ValidatableResponse validatableResponse;
    public Response response;
    public JsonPath jsonPath;
    public AssertActions assertActions;
    public PayloadManager payloadManager;

    @BeforeTest
    public void setUp()
    {
        payloadManager=new PayloadManager();
        assertActions= new AssertActions();
        requestSpecification=new RequestSpecBuilder().setBaseUri(APIConstants.BASE_URL)
                .addHeader("Content-Type","application/json")
                .build().log().all();

        //below is same as above
//        requestSpecification=RestAssured.given()
//                .baseUri(APIConstants.BASE_URL)
//                .contentType(ContentType.JSON)
//                .log().all();
    }

    public String getToken(){
        requestSpecification=RestAssured.given()
                .baseUri(APIConstants.BASE_URL)
                .basePath(APIConstants.AUTH_URL);
        //setting payload
        String tokenPayLoad= payloadManager.setAuthPayload();
        //get the token
        response = requestSpecification.contentType(ContentType.JSON).body(tokenPayLoad).when().post();
        //token extraction
        String token=payloadManager.getTokenFromJSON(response.asString());
        return token;
    }

}
