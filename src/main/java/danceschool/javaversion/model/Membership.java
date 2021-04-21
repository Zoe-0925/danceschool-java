package danceschool.javaversion.model;

import java.util.ArrayList;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Table(name = "Membership")
@Data
public class Membership {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  private String name;

  private String duration;

  private double price;

  private ArrayList<Subscription> subscription;
}
