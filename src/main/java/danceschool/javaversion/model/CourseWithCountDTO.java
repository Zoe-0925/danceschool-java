package danceschool.javaversion.model;

import java.util.ArrayList;
import lombok.Data;

@Data
public class CourseWithCountDTO {

  public ArrayList<CourseDTO> Data;
  public int Count;
}
