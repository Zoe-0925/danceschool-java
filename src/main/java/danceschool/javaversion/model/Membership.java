package danceschool.javaversion.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Table(name = "Membership")
@Data
public class Membership {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull
  @Size(max = 30)
  private String name;

  @NotNull
  @Size(max = 30)
  private String duration;

  @NotNull
  private double price;

  @OneToMany(mappedBy = "subscription", cascade = CascadeType.ALL)
  Set subscription = new HashSet();

  public void setCanceled(Object canceled) {}
}
