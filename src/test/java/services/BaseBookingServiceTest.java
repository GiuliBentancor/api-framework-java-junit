package services;

import models.requests.BookingModel;
import models.services.BookingService;
import org.junit.jupiter.api.BeforeEach;

public class BaseBookingServiceTest {
    protected final BookingService service = new BookingService();

    @BeforeEach
    public void setup() {
        service.authenticate();
    }

    protected BookingModel defaultBooking() {
        BookingModel model = new BookingModel();
        model.setFirstname("Jim");
        model.setLastname("Brown");
        model.setDepositpaid(true);
        model.setTotalprice(111);
        model.setAdditionalneeds("Breakfast");
        BookingModel.BookingDates bookingDates = new BookingModel.BookingDates();
        bookingDates.setCheckin("2018-01-01");
        bookingDates.setCheckout("2019-01-01");
        model.setBookingdates(bookingDates);
        return model;
    }
}
