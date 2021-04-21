package danceschool.javaversion.model;

import java.util.ArrayList;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "Course")
@Data
public class Course {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  public int ID;

  public String nme;

  public double price;

  public Instructor instructor;

  public int instructorID;

  public int bookingLimit;

  public ArrayList<DanceClass> danceClasses;
}
