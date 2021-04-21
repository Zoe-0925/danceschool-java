package danceschool.javaversion.controller;

import danceschool.javaversion.filter.PaginationFilter;
import danceschool.javaversion.model.*;
import danceschool.javaversion.service.DanceClassService;
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
@RequestMapping("/api/classes")
public class DanceClassController {

  @Autowired
  DanceClassService service;

  @GetMapping
  public List<DanceClass> findAllDanceClasss(
    @PathVariable("pageNumber") int pageNumber,
    @PathVariable("pageSize") int pageSize
  ) {
    PaginationFilter filter = new PaginationFilter(pageNumber, pageSize);
    List<Booking> list = service.getAll(
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
  public ResponseEntity<DanceClass> findDanceClassById(
    @PathVariable(value = "id") long id
  ) {
    // Implement
  }

  @PostMapping
  public DanceClass saveDanceClass(@RequestBody DanceClas entity) {
    int id = service.create(entity);
    return ResponseEntity.ok(id);
  }

  @PutMapping
  public ResponseEntity updateDanceClass(@RequestBody DanceClass entity) {
    DanceClass updated = service.update(entity);
    if (updated == null) {
      return ResponseEntity.notFound().build();
    } else {
      return ResponseEntity.ok(updated);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity deleteDanceClass(@PathVariable("id") int id) {
    service.delete(id);

    return ResponseEntity.noContent().build();
  }
}