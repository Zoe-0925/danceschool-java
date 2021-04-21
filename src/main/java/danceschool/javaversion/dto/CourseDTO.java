package danceschool.javaversion.dto;

import lombok.Data;

@Data
public class CourseDTO { //: IMapFrom<Course>

  public int ID;

  public String Name;

  public double Price;
  public int BookingLimit;
  public int ClassCount;
  public int InstructorID;
}
