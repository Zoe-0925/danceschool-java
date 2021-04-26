package danceschool.javaversion.controller;

import danceschool.javaversion.model.Instructor;
import danceschool.javaversion.service.InstructorService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/instructors")
public class InstructorController {

  @Autowired
  InstructorService service;

  @GetMapping
  public ResponseEntity findAllInstructors() {
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
    return null;
    // Implement
  }

  //TODO
  @PostMapping
  public ResponseEntity saveInstructor(@RequestBody Instructor entity) {
    try {
      Long id = service.create(entity);
      return ResponseEntity.ok(id);
    } catch (Exception e) {
      //TODO
      return null;
    }
  }

  @PutMapping
  public ResponseEntity updateInstructor(@RequestBody Instructor entity) {
    try {
      service.update(entity);
      return ResponseEntity.ok().build();
    } catch (Exception e) {
      //TODO
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity deleteInstructor(@PathVariable("id") Long id) {
    service.delete(id);

    return ResponseEntity.noContent().build();
  }
}
