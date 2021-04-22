package danceschool.javaversion.model;

import java.util.ArrayList;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Table(name = "Membership")
@Data
public class Membership {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @NotNull
  @Size(max = 30)
  private String name;

  @NotNull
  @Size(max = 30)
  private String duration;

  @NotNull
  private double price;

  private ArrayList<Subscription> subscription;
}
