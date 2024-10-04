package org.automation.com.pojos;

public class Booking {
    private String firstname;
    private String lastname;
    private Integer totalprice;
    private Boolean depositpaid;
    private bookingdates bookingdates;
    private String additionalneeds;

    public bookingdates getBookingDates()
    {
        return bookingdates;
    }

    public void setBookingDates(bookingdates bookingdates)
    {
        this.bookingdates = bookingdates;
    }

    public String getFirstname()
    {
        return firstname;
    }

    public void setFirstname(String firstname)
    {
        this.firstname = firstname;
    }

    public String getLastname()
    {
        return lastname;
    }

    public void setLastname(String lastname)
    {
        this.lastname = lastname;
    }

    public String getAdditionalneeds()
    {
        return additionalneeds;
    }

    public void setAdditionalneeds(String additionalneeds)
    {
        this.additionalneeds = additionalneeds;
    }

    public Integer getTotalprice()
    {
        return totalprice;
    }

    public void setTotalprice(Integer totalprice)
    {
        this.totalprice = totalprice;
    }

    public boolean isDepositpaid()
    {
        return depositpaid;
    }

    public void setDepositpaid(boolean depositpaid)
    {
        this.depositpaid = depositpaid;
    }
}
