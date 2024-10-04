package org.automation.com.modules;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import org.automation.com.pojos.*;

import java.util.Properties;

public class PayloadManager
{
    //serialization and deserialization
    Gson gson;
    //converting java object to the json string
    public String createPayloadBookingAsString()
    {
        Booking booking= new Booking();
        bookingdates bookingdates=new bookingdates();
        booking.setFirstname("Deepa");
        booking.setLastname("Chavan");
        booking.setDepositpaid(true);
        booking.setTotalprice(1200);

        bookingdates.setCheckin("2024-12-12");
        bookingdates.setCheckout("2024-12-12");
        booking.setBookingDates(bookingdates);
        booking.setAdditionalneeds("Lunch");

        System.out.println(booking);
        //we need to convert this String object to JSON Object(byteStream)->Serialization
        gson= new Gson();
        String jsonPayLoad= gson.toJson(booking);
        System.out.println(jsonPayLoad);
        return jsonPayLoad;
    }

    public String createPayloadBookingAsStringFaker()
    {
        Faker faker=new Faker();
        Booking booking= new Booking();
        bookingdates bookingdates=new bookingdates();
        booking.setFirstname(faker.name().firstName());
        booking.setLastname(faker.name().lastName());
        booking.setDepositpaid(true);
        booking.setTotalprice(faker.random().nextInt(1200));

        bookingdates.setCheckin("2024-12-12");
        bookingdates.setCheckout("2024-12-12");
        booking.setBookingDates(bookingdates);
        booking.setAdditionalneeds("Lunch");

        System.out.println(booking);
        //we need to convert this String object to JSON Object(byteStream)->Serialization
        gson= new Gson();
        String jsonPayLoad= gson.toJson(booking);
        System.out.println(jsonPayLoad);
        return jsonPayLoad;
    }

    public BookingResponse bookingResponse(String responseString)
    {
        gson=new Gson();
        BookingResponse bookingResponse=gson.fromJson(responseString,BookingResponse.class);
        return bookingResponse;
    }

    public String setAuthPayload()
    {
        //Auth object converted to JSON string
        Auth auth=new Auth();
        auth.setUsername("admin");
        auth.setPassword("password123");
        gson=new Gson();
        String jsonAuthPayLoad= gson.toJson(auth);
        System.out.println(jsonAuthPayLoad);
        return jsonAuthPayLoad;
    }

    public String getTokenFromJSON(String strTokenResponse)
    {
        gson=new Gson();
        TokenResponse tokenResponse=gson.fromJson(strTokenResponse,TokenResponse.class);
        return tokenResponse.getToken();
    }

    //de serialization of booking ID
    public Booking getResponseFromJSON(String strBookingResponse)
    {
        gson=new Gson();
        Booking booking=gson.fromJson(strBookingResponse,Booking.class);
        return booking;
    }

    public String fullUpdatePayloadAsString()
    {
        Booking booking= new Booking();
        bookingdates bookingdates=new bookingdates();
        booking.setFirstname("Dipa");
        booking.setLastname("Chavan");
        booking.setDepositpaid(true);
        booking.setTotalprice(1200);

        bookingdates.setCheckin("2024-12-12");
        bookingdates.setCheckout("2024-12-12");
        booking.setBookingDates(bookingdates);
        booking.setAdditionalneeds("Lunch");

        System.out.println(booking);
        //we need to convert this String object to JSON Object(byteStream)->Serialization
        gson= new Gson();
        String jsonFullUpdatePayLoad= gson.toJson(booking);
        System.out.println(jsonFullUpdatePayLoad);
        return jsonFullUpdatePayLoad;

    }
}
