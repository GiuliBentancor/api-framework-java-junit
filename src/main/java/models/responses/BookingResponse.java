package models.responses;
import  models.requests.BookingModel;

public class BookingResponse {
    private Long bookingid;
    private BookingModel booking;

    public BookingResponse() {
    }

    public Long getBookingid() {
        return bookingid;
    }

    public void setBookingId(Long bookingid) {
       this.bookingid = bookingid;
    }

    public BookingModel getBooking() {
        return booking;
    }

    public void setBooking(BookingModel booking) {
        this.booking = booking;
    }
}