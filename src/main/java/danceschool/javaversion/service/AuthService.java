package danceschool.javaversion.service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;

public class AuthService {

  public String getRoleFromToken(String token) {
    try {
      FirebaseToken decodedToken = firebaseAuth.verifyIdToken(token);
    } catch (FirebaseAuthException e) {
      log.error("Firebase Exception:: ", e.getLocalizedMessage());
    }

    if (Boolean.TRUE.equals(decoded.getClaims().get("admin"))) {
      return "admin";
    }

    if (Boolean.TRUE.equals(decoded.getClaims().get("student"))) {
      return "student";
    }
    return "";
  }
}
