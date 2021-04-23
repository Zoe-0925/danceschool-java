package danceschool.javaversion.dto;

import danceschool.javaversion.model.Course;
import lombok.Data;

@Data
public class CourseDTO { //: IMapFrom<Course>

  public Long id;

  public String name;

  public double price;
  public int bookingLimit;
  public int classCount;
  public int instructorID;

  public CourseDTO(Course course) {
    this.id = course.getId();
    this.name = course.getName();
    this.price = course.getPrice();
    this.bookingLimit = course.getBookingLimit();
    this.classCount = course.getDanceClasses().size();
    this.instructorID = course.getInstructorID();
  }
}
