package org.automation.com.tests.ddt.vwo;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.automation.com.asserts.AssertActions;
import org.automation.com.modules.PayloadManager;
import org.automation.com.utils.UtilsExcel;
import org.testng.annotations.Test;

public class VWOLogin
{
    public RequestSpecification requestSpecification= RestAssured.given();
    public ValidatableResponse validatableResponse;
    public Response response;
    public JsonPath jsonPath;
    public AssertActions assertActions;
    public PayloadManager payloadManager;

    @Test(dataProvider = "getData",dataProviderClass = UtilsExcel.class)
    public void testVWOLogin(String email, String password)
    {
        System.out.println("Testing DDT for VWO Login");
        System.out.println(email);
        System.out.println(password);
    }
}

//output will be -There will be 2 TCs
//TC1
/*
Testing DDT for VWO Login
admin
password1

*/


//TC2
/*
Testing DDT for VWO Login
admin1
password123
*/