package danceschool.javaversion.model;

import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Table(name = "Course")
@Data

public class Course {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  public Long id;

  @NotNull
  @Size(max = 50)
  public String name;

  @NotNull
  public double price;

  public Instructor instructor;

  @NotNull
  public Long instructorID;

  @NotNull
  public int bookingLimit;

  @OneToMany(mappedBy = "danceClass", cascade = CascadeType.ALL)
  Set danceClasses = new HashSet();
}
