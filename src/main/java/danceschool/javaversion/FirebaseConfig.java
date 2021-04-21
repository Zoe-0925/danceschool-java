package danceschool.javaversion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import java.lang.Exception;

@Primary
@Bean
public void firebaseInit() throws IOException {
 FirebaseOptions options = new FirebaseOptions.Builder()				
.setCredentials(GoogleCredentials.getApplicationDefault())				
.setDatabaseUrl(FIREBASE_DATABASE_URL).build();
if (FirebaseApp.getApps().isEmpty()) {
	FirebaseApp.initializeApp(options);
	}
}