package danceschool.javaversion.controller;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import danceschool.javaversion.dto.SignUpRequest;
import danceschool.javaversion.dto.SignUpResult;
import danceschool.javaversion.dto.Token;
import danceschool.javaversion.dto.UserRecordArgs;
import danceschool.javaversion.model.Student;
import danceschool.javaversion.service.AuthService;
import danceschool.javaversion.service.StudentService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Account")
public class AuthController {

  @Autowired
  AuthService service;

  @Autowired
  StudentService studentService;

  @PostMapping("/sign-up")
  public ResponseEntity signUp(@RequestBody SignUpRequest request) {
    UserRecordArgs args = new UserRecordArgs(
      request.getEmail(),
      request.getPassword(),
      request.getUserName()
    );

    UserRecord userRecord = FirebaseAuth.getInstance().createUser(args);
    System.out.println("Successfully created new user: " + userRecord.getUid());

    Map<String, Object> claims = new HashMap<>();
    claims.put("admin", true);
    FirebaseAuth.getInstance().setCustomUserClaims(userRecord.getUid(), claims);

    Student student = new Student(request.getUserName(), request.getEmail());
    studentService.create(student);
    return (ResponseEntity) ResponseEntity.ok();
  }

  @PostMapping("/verify")
  public ResponseEntity verifyToken(@RequestBody Token idToken) {
    try {
      String role = service.getRoleFromToken(idToken.getIdToken());
      return ResponseEntity.ok(role);
    } catch (FirebaseAuthException ex) {
      //TODO exception code
    } catch (Exception ex) {
      //TODO exception code
    }
  }
}
