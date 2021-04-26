package danceschool.javaversion.service;

import danceschool.javaversion.dto.CountByDate;
import danceschool.javaversion.dto.CourseDTO;
import danceschool.javaversion.dto.DanceClassDTO;
import danceschool.javaversion.dto.Dashboard;
import danceschool.javaversion.dto.InstructorIDWithCountDTO;
import danceschool.javaversion.dto.MembershipNameWithCountDTO;
import danceschool.javaversion.exception.RecordNotFoundException;
import danceschool.javaversion.model.Instructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@CacheConfig
public class DashboardService {

  @Autowired
  CourseService courseService;

  @Autowired
  BookingService bookingService;

  @Autowired
  StudentService studentService;

  @Autowired
  SubscriptionService subscriptionService;

  @Autowired
  InstructorService instructorService;

  @Autowired
  DanceClassService danceClassService;

  @Cacheable
  public Dashboard getDashboard() {
    int totalCourses = courseService.getTotal();
    int totalBookings = bookingService.getTotal();
    int totalStudents = studentService.getTotal();
    int totalSubscriptions = subscriptionService.getTotal();
    List<Instructor> instructors = instructorService.getAll();

    List<DanceClassDTO> topClasses = danceClassService.getTop();
    List<InstructorIDWithCountDTO> topInstructors = instructorService.getTop();
    List<MembershipNameWithCountDTO> bookingByMembership = bookingService.getByMembership();
    List<CountByDate> lastWeekbookings = bookingService.getLastWeeksBookings();

    return new Dashboard(
      totalCourses,
      totalBookings,
      totalStudents,
      totalSubscriptions,
      instructors,
      topClasses,
      topInstructors,
      bookingByMembership,
      lastWeekbookings
    );
  }
}
