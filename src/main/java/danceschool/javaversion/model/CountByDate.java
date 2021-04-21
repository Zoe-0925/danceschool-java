package danceschool.javaversion.model;


import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CountByDate {

  private int count;

  private LocalDateTime date;
}
