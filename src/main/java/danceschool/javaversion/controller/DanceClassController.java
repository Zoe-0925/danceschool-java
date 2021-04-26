package danceschool.javaversion.controller;

import danceschool.javaversion.dto.DanceClassDTO;
import danceschool.javaversion.filter.PaginationFilter;
import danceschool.javaversion.model.*;
import danceschool.javaversion.service.DanceClassService;
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
@RequestMapping("/api/classes")
public class DanceClassController {

  @Autowired
  DanceClassService service;

  @GetMapping("page/{pageNumber}/size/{pageSize}/")
  public ResponseEntity<?> findAllDanceClasss(
    @PathVariable("pageNumber") int pageNumber,
    @PathVariable("pageSize") int pageSize
  ) {
    PaginationFilter filter = new PaginationFilter(pageNumber, pageSize);

    List<DanceClassDTO> list = service.getAll(
      filter.getPageNumber(),
      filter.getPageSize()
    );
    return ResponseEntity.ok(list);
  }

  @PostMapping
  public ResponseEntity<?> saveDanceClass(@RequestBody DanceClass entity) {
    try {
      Long id = service.create(entity);
      return ResponseEntity.ok(id);
    } catch (Exception e) {
      return null; //TODO update error handling
    }
  }

  @PutMapping
  public ResponseEntity<?> updateDanceClass(@RequestBody DanceClass entity) {
    boolean succeeded = service.update(entity);
    return succeeded
      ? ResponseEntity.noContent().build()
      : ResponseEntity.notFound().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity deleteDanceClass(@PathVariable("id") Long id) {
    service.delete(id);

    return ResponseEntity.noContent().build();
  }
}
