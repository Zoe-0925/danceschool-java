package danceschool.javaversion.dto;

import danceschool.javaversion.model.Instructor;
import java.util.ArrayList;
import lombok.Data;


@Data
public class Dashboard {

  private ArrayList<InstructorIDWithCountDTO> topInstructors;
  private ArrayList<DanceClassDTO> topClasses;

  private int totalCourses;
  private int totalBookings;
  private int totalStudents;
  private int totalSubscriptions;

  private ArrayList<MembershipNameWithCountDTO> bookingByMembership;
  private ArrayList<CountByDate> lastWeekbookings;

  private ArrayList<Instructor> instructors;
}
