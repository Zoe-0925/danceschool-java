package danceschool.javaversion.controller;

import danceschool.javaversion.filter.PaginationFilter;
import danceschool.javaversion.model.Student;
import danceschool.javaversion.service.StudentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
    String[] sort = { "name,asc" };
    PaginationFilter filter = new PaginationFilter(pageNumber, pageSize);
    List<Student> list = service.getAll(
      filter.getPageNumber(),
      filter.getPageSize(),
      sort
    );

    if (list == null) {
      return ResponseEntity.notFound().build();
    } else {
      return ResponseEntity.ok(list);
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity findStudentById(@PathVariable(value = "id") long id) {
    // Implement
  }

  @PostMapping
  public Student saveStudent(@RequestBody Student entity) {
    int id = service.create(entity);
    return ResponseEntity.ok(id);
  }

  @PutMapping
  public ResponseEntity updateStudent(@RequestBody Student entity) {
    Student updated = service.update(entity);
    if (updated == null) {
      return ResponseEntity.notFound().build();
    } else {
      return ResponseEntity.ok(updated);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity deleteStudent(@PathVariable("id") int id) {
    service.delete(id);

    return ResponseEntity.noContent().build();
  }
}
