package danceschool.javaversion;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.FirebaseApp;
import com.google.auth.oauth2.GoogleCredentials;

@SpringBootApplication
@EnableCaching //enables Spring Caching functionality
public class JavaversionApplication implements CommandLineRunner {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  /**
  //TODO
  //Replace to environment variables
  @Bean
  JedisConnectionFactory jedisConnectionFactory() {
    RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(
      "danceschool.redis.cache.windows.net",
      6380
    );
    redisStandaloneConfiguration.setPassword(
      RedisPassword.of("Rg98IbQPtmRFOlzvyHG6f1Z1T6smKQTYwU8O7iQNh4o=")
    );
    return new JedisConnectionFactory(redisStandaloneConfiguration);
    // abortConnect: False
    // allowAdmin: True
  } 

  @Bean
  RedisTemplate<String, User> redisTemplate() {
    RedisTemplate<String, User> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(jedisConnectionFactory());
    return redisTemplate;
  }*/

  @Override
  public void run(String... args) throws Exception {
    logger.info("Server started successfully.");
  }

  public static void main(String[] args) throws IOException {
    SpringApplication.run(JavaversionApplication.class, args);

    FirebaseOptions options = FirebaseOptions
      .builder()
      .setCredentials(GoogleCredentials.getApplicationDefault())
      .build();

    FirebaseApp.initializeApp(options);
  }
}
