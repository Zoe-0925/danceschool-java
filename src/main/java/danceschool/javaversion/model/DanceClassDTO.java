package danceschool.javaversion.model;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class DanceClassDTO { //: IMapFrom<DanceClass>

  private int id;

  private LocalDateTime startTime;
  private LocalDateTime endTime;
  private String name;

  private int count;
}
