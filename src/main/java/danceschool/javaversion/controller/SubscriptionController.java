package danceschool.javaversion.controller;

import danceschool.javaversion.filter.PaginationFilter;
import danceschool.javaversion.model.Subscription;
import danceschool.javaversion.service.SubscriptionService;
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
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

  @Autowired
  SubscriptionService service;

  @GetMapping
  public List<Subscription> findAllSubscriptions(
    @PathVariable("pageNumber") int pageNumber,
    @PathVariable("pageSize") int pageSize
  ) {
    PaginationFilter filter = new PaginationFilter(pageNumber, pageSize);
    List<Subscription> list = service.getAll(
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
  public ResponseEntity<Subscription> findSubscriptionById(
    @PathVariable(value = "id") long id
  ) {
    // Implement
  }

  @PostMapping
  public Subscription saveSubscription(@RequestBody Subscription entity) {
    Long id = service.create(entity);
    return ResponseEntity.ok(id);
  }

  @PutMapping
  public ResponseEntity updateSubscription(@RequestBody Subscription entity) {
    Subscription updated = service.update(entity);
    if (updated == null) {
      return ResponseEntity.notFound().build();
    } else {
      return ResponseEntity.ok(updated);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity deleteSubscription(@PathVariable("id") Long id) {
    service.delete(id);

    return ResponseEntity.noContent().build();
  }
}
