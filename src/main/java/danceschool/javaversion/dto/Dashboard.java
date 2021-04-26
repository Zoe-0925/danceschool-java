package danceschool.javaversion.dto;

import danceschool.javaversion.model.Instructor;
import java.util.List;
import lombok.Data;

@Data
public class Dashboard {

  private List<InstructorIDWithCountDTO> topInstructors;
  private List<DanceClassDTO> topClasses;

  private int totalCourses;
  private int totalBookings;
  private int totalStudents;
  private int totalSubscriptions;

  private List<MembershipNameWithCountDTO> bookingByMembership;
  private List<CountByDate> lastWeekbookings;

  private List<Instructor> instructors;

  public Dashboard(
    int totalCourses,
    int totalBookings,
    int totalStudents,
    int totalSubscriptions,
    List<Instructor> instructors,
    List<DanceClassDTO> topClasses,
    List<InstructorIDWithCountDTO> topInstructors,
    List<MembershipNameWithCountDTO> bookingByMembership,
    List<CountByDate> lastWeekbookings
  ) {
    this.totalCourses = totalCourses;
    this.totalBookings = totalBookings;
    this.totalStudents = totalStudents;
    this.totalSubscriptions = totalSubscriptions;
    this.instructors = instructors;
    this.topClasses = topClasses;
    this.topInstructors = topInstructors;
    this.bookingByMembership = bookingByMembership;
    this.lastWeekbookings = lastWeekbookings;
  }
}
