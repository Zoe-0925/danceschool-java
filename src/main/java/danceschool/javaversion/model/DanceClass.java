package danceschool.javaversion.model;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Table(name = "DanceClass")
@Data
public class DanceClass {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @NotNull
  private LocalDateTime startTime;

  @NotNull
  private LocalDateTime endTime;

  @NotNull
  private String courseName;

  @NotNull
  private int courseID;

  @ManyToOne
  @JoinColumn(name = "courseID")
  private Course course;

  @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
  Set bookings = new HashSet();
}
