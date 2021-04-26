package danceschool.javaversion.controller;

import danceschool.javaversion.dto.CourseDTO;
import danceschool.javaversion.filter.PaginationFilter;
import danceschool.javaversion.model.*;
import danceschool.javaversion.service.CourseService;
import java.util.List;
import java.util.Optional;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

  @Autowired
  CourseService service;

  @GetMapping("page/{pageNumber}/size/{pageSize}/")
  public ResponseEntity<?> findAllCourses(
    @PathVariable("pageNumber") int pageNumber,
    @PathVariable("pageSize") int pageSize
  ) {
    PaginationFilter filter = new PaginationFilter(pageNumber, pageSize);

    //TODO add sort by what
    List<CourseDTO> list = service.getAll(
      filter.getPageNumber(),
      filter.getPageSize()
    );

    if (list == null) {
      return ResponseEntity.notFound().build();
    } else {
      return ResponseEntity.ok(list);
    }
  }

  @GetMapping("page/{pageNumber}/size/{pageSize}/")
  public ResponseEntity<?> findAllBookings(
    @PathVariable int pageNumber,
    @PathVariable int pageSize
  ) {
    PaginationFilter filter = new PaginationFilter(pageNumber, pageSize);

    List<CourseDTO> list = service.getAll(
      filter.getPageNumber(),
      filter.getPageSize()
    );

    return list == null
      ? ResponseEntity.notFound().build()
      : ResponseEntity.ok(list);
  }

  @GetMapping("/search/{name}")
  public ResponseEntity<List<Course>> findCourseByName(
    @PathVariable(value = "id") String name
  ) {
    List<Course> courses = service.findByName(name);
    return ResponseEntity.ok(courses);
  }

  @PostMapping
  public ResponseEntity<Long> saveCourse(@RequestBody Course entity) {
    try {
      Long id = service.create(entity);
      return ResponseEntity.ok(id);
    } catch (Exception e) {
      return null;
    }
  }

  @PutMapping
  public ResponseEntity<?> updateCourse(@RequestBody Course entity) {
    try {
      service.update(entity);
      return (ResponseEntity) ResponseEntity.ok();
    } catch (Exception e) {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteCourse(@PathVariable("id") Long id) {
    service.delete(id);

    return ResponseEntity.noContent().build();
  }
}
