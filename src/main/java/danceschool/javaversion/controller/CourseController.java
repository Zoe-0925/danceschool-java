package danceschool.javaversion.controller;

import danceschool.javaversion.filter.PaginationFilter;
import danceschool.javaversion.model.*;
import danceschool.javaversion.serivce.CourseService;
import java.util.List;
import java.util.Optional;
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
@RequestMapping("/api/courses")
public class CourseController {

  @Autowired
  CourseService service;

  @GetMapping("page/{pageNumber}/size/{pageSize}/")
  public List<Course> findAllCourses(
    @PathVariable("pageNumber") int pageNumber,
    @PathVariable("pageSize") int pageSize
  ) {
    PaginationFilter filter = new PaginationFilter(pageNumber, pageSize);
    List<Course> list = service.getAll(
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
  public ResponseEntity<Course> findCourseById(
    @PathVariable(value = "id") long id
  ) {
    Optional<Course> course = courseRepository.findById(id);

    if (course.isPresent()) {
      return ResponseEntity.ok().body(course.get());
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping
  public Course saveCourse(@RequestBody Course entity) {
    int id = service.create(entity);
    return ResponseEntity.ok(id);
  }

  @PutMapping
  public ResponseEntity updateCourse(@RequestBody Course entity) {
    Course updated = service.update(entity);
    if (updated == null) {
      return ResponseEntity.notFound().build();
    } else {
      return ResponseEntity.ok(updated);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity deleteCourse(@PathVariable("id") int id) {
    service.delete(id);

    return ResponseEntity.noContent().build();
  }
}
