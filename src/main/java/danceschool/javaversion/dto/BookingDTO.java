package danceschool.javaversion.dto;


import java.time.LocalDateTime;
import lombok.Data;

@Data
public class BookingDTO {

  private int id;
  private LocalDateTime bookingDate;
  private LocalDateTime date;

  private int classID;
  private String studentEmail;
  private String courseName;
}
