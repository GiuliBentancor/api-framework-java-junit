package services;

import models.requests.BookingModel;
import models.responses.BookingResponse;
import models.responses.ResponseContainer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BookingCreationTest extends BaseBookingServiceTest {

    @Test
    @DisplayName("Create a new booking returns expected data")
    void createNewBooking() {
        BookingModel model = defaultBooking();
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
    @DisplayName("Create booking without firstname returns 400")
    void createBookingWithoutFirstname() {
        BookingModel model = defaultBooking();
        model.setFirstname(null);
        ResponseContainer<BookingResponse> response = service.addBooking(model, null);
        Assertions.assertEquals(500, response.getStatus());
    }
}
