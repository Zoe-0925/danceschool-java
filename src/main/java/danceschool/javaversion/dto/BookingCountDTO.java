package danceschool.javaversion.dto;

import java.util.List;
import lombok.Data;

@Data
public class BookingCountDTO {

  private List<BookingDTO> data;

  private int count;

  public BookingCountDTO(List<BookingDTO> bookingList, int count) {
    this.data = bookingList;
    this.count = count;
  }
}
