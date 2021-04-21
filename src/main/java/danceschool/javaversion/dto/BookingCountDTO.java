package danceschool.javaversion.dto;


import java.util.ArrayList;
import lombok.Data;

@Data
public class BookingCountDTO {

  private int count;

  private ArrayList<BookingDTO> data;
}
