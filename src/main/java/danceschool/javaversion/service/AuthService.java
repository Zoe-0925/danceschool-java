package danceschool.javaversion.service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;

public class AuthService {

  public String getRoleFromToken(String token)
    throws FirebaseAuthException, Exception {
    try {
      FirebaseToken decodedToken = FirebaseAuth
        .getInstance()
        .verifyIdToken(token);
      if (Boolean.TRUE.equals(decodedToken.getClaims().get("admin"))) {
        return "admin";
      }

      if (Boolean.TRUE.equals(decodedToken.getClaims().get("student"))) {
        return "student";
      }
    } catch (FirebaseAuthException e) {
      return "";
    } catch (Exception ex) {
      //TODO exception code
    }
    return "";
  }
}
