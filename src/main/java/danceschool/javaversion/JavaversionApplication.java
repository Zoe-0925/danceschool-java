package danceschool.javaversion;

import com.howtodoinjava.demo.entity.EmployeeEntity;
import com.howtodoinjava.demo.repository.EmployeeRepository;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavaversionApplication implements CommandLineRunner {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Override
  public void run(String... args) throws Exception {
    Optional<Student> emp = repository.findById(2L);

    logger.info("Server started successfully.");
  }
}
