package danceschool.javaversion.dto;

import java.util.List;
import lombok.Data;

@Data
public class CourseWithCountDTO {

  public List<CourseDTO> data;
  public int count;

  public CourseWithCountDTO(List<CourseDTO> data, int count) {
    this.data = data;
    this.count = count;
  }
}
