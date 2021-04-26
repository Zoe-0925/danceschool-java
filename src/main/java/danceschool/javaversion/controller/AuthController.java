package danceschool.javaversion.controller;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.auth.UserRecord.CreateRequest;
import danceschool.javaversion.dto.SignUpRequest;
import danceschool.javaversion.dto.Token;
import danceschool.javaversion.model.Student;
import danceschool.javaversion.service.AuthService;
import danceschool.javaversion.service.StudentService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Account")
public class AuthController {

  @Autowired
  AuthService service;

  @Autowired
  StudentService studentService;

  @PostMapping("/sign-up")
  public ResponseEntity<?> signUp(@RequestBody SignUpRequest request)
    throws Exception {
    CreateRequest args = new CreateRequest()
      .setEmail(request.getEmail())
      .setPassword(request.getPassword())
      .setDisplayName(request.getUserName())
      .setDisabled(false)
      .setEmailVerified(true);

    UserRecord userRecord = FirebaseAuth.getInstance().createUser(args);
    System.out.println("Successfully created new user: " + userRecord.getUid());

    Map<String, Object> claims = new HashMap<>();
    claims.put("admin", true);
    FirebaseAuth.getInstance().setCustomUserClaims(userRecord.getUid(), claims);

    Student student = new Student(request.getUserName(), request.getEmail());
    Long studentId = studentService.create(student);
    return ResponseEntity.ok(studentId);
  }

  @PostMapping("/verify")
  public ResponseEntity<?> verifyToken(@RequestBody Token idToken)
    throws FirebaseAuthException, Exception {
    String role = service.getRoleFromToken(idToken.getIdToken());
    return ResponseEntity.ok(role);
  }
}
