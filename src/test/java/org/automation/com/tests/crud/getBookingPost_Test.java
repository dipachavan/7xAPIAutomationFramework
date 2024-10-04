package org.automation.com.tests.crud;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.Test;

public class getBookingPost_Test {
    @Owner("Deepa")
    @Description("Get booking ID")
    @Test
    public void getBookingTest(){
        Assert.assertEquals("Deepa","Deepa");
    }
}
