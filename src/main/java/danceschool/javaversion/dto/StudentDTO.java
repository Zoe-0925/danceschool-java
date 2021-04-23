package danceschool.javaversion.dto;

import danceschool.javaversion.model.Student;
import lombok.Data;

@Data
public class StudentDTO {

  private Long id;

  private String userName;

  private String email;
  private String membership;

  public StudentDTO(Student student) {
    this.id = student.getId();
    this.userName = student.getUserName();
    this.email = student.getEmail();
    this.membership = student.getSubscription().getMembershipName();
  }
}
