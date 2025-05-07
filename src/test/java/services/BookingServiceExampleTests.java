package services;
import models.requests.BookingModel;
import models.responses.ResponseContainer;
import models.responses.BookingResponse;
import models.services.BookingService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.List;

public class BookingServiceExampleTests {
    private BookingService service = new BookingService();

    @BeforeEach
    public void setup() {
        service.authenticate();
    }

    @Test
    public void createNewBooking() {

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

        ResponseContainer<BookingResponse> response = service.addBooking(model, null);
        Assertions.assertEquals(200, response.getStatus());
        BookingModel responseModel = response.getData().getBooking();
        
        Assertions.assertNotNull(response.getData().getBookingid());
        Assertions.assertEquals(model.getFirstname(), responseModel.getFirstname());
        Assertions.assertEquals(model.getLastname(), responseModel.getLastname());
        Assertions.assertEquals(model.getDepositpaid(), responseModel.getDepositpaid());
        Assertions.assertEquals(model.getTotalprice(), responseModel.getTotalprice());
        Assertions.assertEquals(model.getAdditionalneeds(), responseModel.getAdditionalneeds());
        Assertions.assertEquals(model.getBookingdates().getCheckin(), responseModel.getBookingdates().getCheckin());
        Assertions.assertEquals(model.getBookingdates().getCheckout(), responseModel.getBookingdates().getCheckout());
    }

    @Test
    public void testGetBookingById() {
        ResponseContainer<BookingModel> response = service.getBookingById(1L, null);
        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertNotNull(response.getData());
    }

    @Test
    public void testGetBookingsList() {
        ResponseContainer<List<BookingModel>> response = service.getBookings(null);
        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertTrue(response.getData().size() > 0);
    }

    @Test
    public void getBookingSuccessfulLessThan1000ms() {
        ResponseContainer<BookingModel> response = service.getBookingById(1000l, null);
        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertTrue(response.getResponseTime() < 1000);
}
}
