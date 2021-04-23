package danceschool.javaversion.dto;

import danceschool.javaversion.model.Booking;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class BookingDTO {

  private Long id;
  private LocalDateTime bookingDate;
  private LocalDateTime date;

  private int classID;
  private String studentEmail;
  private String courseName;

  public BookingDTO(Booking booking) {
    this.id = booking.getId();
    this.bookingDate = booking.getBookingDate();
    this.date = booking.getDanceClass().getStartTime();
    this.classID = booking.getClassID();
    this.studentEmail = booking.getStudent().getEmail();
    this.courseName = booking.getDanceClass().getCourseName();
  }
}
