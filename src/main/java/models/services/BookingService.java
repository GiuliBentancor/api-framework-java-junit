package models.services;
import base.ServiceBase;
import models.requests.BookingModel;
import models.responses.BookingResponse;
import models.responses.ResponseContainer;

import java.util.List;
import java.util.Map;

public class BookingService extends ServiceBase {
    public BookingService() {
        super("/booking");
    }

    public ResponseContainer<BookingResponse> addBooking(BookingModel model, Map<String, String> headers) {
        return this.post(this.url, model, headers, BookingResponse.class);
    }

    public ResponseContainer<BookingModel> getBookingById(Long bookingId, Map<String, String> headers) {
        return this.getSingleObject(this.url + "/" + bookingId, headers, BookingModel.class);
    }

    public ResponseContainer<List<BookingModel>> getBookings(Map<String, String> headers) {
        return this.getListOfObjects(this.url, headers);
    }
}