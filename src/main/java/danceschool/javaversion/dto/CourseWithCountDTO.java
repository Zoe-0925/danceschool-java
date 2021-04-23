package danceschool.javaversion.dto;

import java.util.ArrayList;
import lombok.Data;

@Data
public class CourseWithCountDTO {

  public ArrayList<CourseDTO> data;
  public int count;
}
