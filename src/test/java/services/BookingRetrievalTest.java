package services;

import java.util.List;
import models.requests.BookingModel;
import models.responses.BookingResponse;
import models.responses.ResponseContainer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BookingRetrievalTest extends BaseBookingServiceTest {

    @Test
    @DisplayName("Get booking by id")
    void getBookingById() {
        ResponseContainer<BookingModel> response = service.getBookingById(1L, null);
        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertNotNull(response.getData());
    }

    @Test
    @DisplayName("Get booking by invalid id returns 404")
    void getBookingNotFound() {
        ResponseContainer<BookingModel> response = service.getBookingById(-1L, null);
        Assertions.assertEquals(404, response.getStatus());
        Assertions.assertNull(response.getData());
    }

    @Test
    @DisplayName("Get bookings list")
    void getBookingsList() {
        ResponseContainer<List<BookingModel>> response = service.getBookings(null);
        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertTrue(response.getData().size() > 0);
    }

    @Test
    @DisplayName("Created booking can be retrieved")
    void createdBookingCanBeRetrieved() {
        BookingModel model = defaultBooking();
        ResponseContainer<BookingResponse> created = service.addBooking(model, null);
        Long bookingId = created.getData().getBookingid();

        ResponseContainer<BookingModel> response = service.getBookingById(bookingId, null);
        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertEquals(model.getFirstname(), response.getData().getFirstname());
    }

    @Test
    @DisplayName("Get booking successful less than 2000ms")
    void getBookingSuccessfulLessThan2000ms() {
        ResponseContainer<BookingModel> response = service.getBookingById(1L, null);
        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertTrue(response.getResponseTime() < 2000);
    }
}
