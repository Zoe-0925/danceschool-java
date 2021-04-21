package danceschool.javaversion.controller;

import danceschool.javaversion.model.Instructor;
import danceschool.javaversion.service.InstructorService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/instructors")
public class InstructorController {

  @Autowired
  InstructorService service;

  @GetMapping
  public List<Instructor> findAllInstructors() {
    List<Instructor> list = service.getAll();

    if (list == null) {
      return ResponseEntity.notFound().build();
    } else {
      return ResponseEntity.ok(list);
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<Instructor> findInstructorById(
    @PathVariable(value = "id") long id
  ) {
    // Implement
  }

  @PostMapping
  public Instructor saveInstructor(@RequestBody Instructor entity) {
    int id = service.create(entity);
    return ResponseEntity.ok(id);
  }

  @PutMapping
  public ResponseEntity updateInstructor(@RequestBody Instructor entity) {
    Instructor updated = service.update(entity);
    if (updated == null) {
      return ResponseEntity.notFound().build();
    } else {
      return ResponseEntity.ok(updated);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity deleteInstructor(@PathVariable("id") int id) {
    service.delete(id);

    return ResponseEntity.noContent().build();
  }
}
