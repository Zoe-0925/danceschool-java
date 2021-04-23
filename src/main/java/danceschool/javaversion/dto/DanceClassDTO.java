package danceschool.javaversion.dto;

import danceschool.javaversion.model.DanceClass;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class DanceClassDTO { //: IMapFrom<DanceClass>

  private Long id;

  private LocalDateTime startTime;
  private LocalDateTime endTime;
  private String name;

  private int count;

  public DanceClassDTO(DanceClass danceClass) {
    this.id = danceClass.getId();
    this.startTime = danceClass.getStartTime();
    this.endTime = danceClass.getEndTime();
    this.name = danceClass.getCourse().getName();
  }
}
