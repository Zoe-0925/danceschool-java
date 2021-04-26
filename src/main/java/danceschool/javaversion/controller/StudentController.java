package danceschool.javaversion.controller;

import danceschool.javaversion.dto.StudentDTO;
import danceschool.javaversion.exception.RecordNotFoundException;
import danceschool.javaversion.filter.PaginationFilter;
import danceschool.javaversion.model.Student;
import danceschool.javaversion.service.StudentService;
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
@RequestMapping("/api/students")
public class StudentController {

  @Autowired
  StudentService service;

  @GetMapping("page/{pageNumber}/size/{pageSize}/")
  public ResponseEntity findAllStudents(
    @PathVariable("pageNumber") int pageNumber,
    @PathVariable("pageSize") int pageSize
  ) {
    PaginationFilter filter = new PaginationFilter(pageNumber, pageSize);
    List<StudentDTO> list = service.getAll(
      filter.getPageNumber(),
      filter.getPageSize()
    );

    if (list == null) {
      return ResponseEntity.notFound().build();
    } else {
      return ResponseEntity.ok(list);
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity findStudentById(@PathVariable(value = "id") long id) {
    return null;
    // Implement
  }

  @GetMapping("/search/{query}")
  public ResponseEntity searchStudentByName(
    @PathVariable(value = "query") String query
  ) {
    List<Student> result = service.findByName(query);
    if (result == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(result);
  }

  @PostMapping
  public ResponseEntity saveStudent(@RequestBody Student entity) {
    try {
      Long id = service.create(entity);
      return ResponseEntity.ok(id);
    } catch (Exception e) {
      //TODO
    }
    return null;
  }

  @PutMapping
  public ResponseEntity updateStudent(@RequestBody Student entity) {
    try {
      service.update(entity);
      return (ResponseEntity) ResponseEntity.ok();
    } catch (Exception e) {
      //TODO
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity deleteStudent(@PathVariable("id") Long id) {
    service.delete(id);

    return ResponseEntity.noContent().build();
  }
}
