package danceschool.javaversion.controller;

import danceschool.javaversion.dto.SubscriptionDTO;
import danceschool.javaversion.filter.PaginationFilter;
import danceschool.javaversion.model.Subscription;
import danceschool.javaversion.service.SubscriptionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.HeadersBuilder;
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
  public ResponseEntity<T> findAllSubscriptions(
    @PathVariable("pageNumber") int pageNumber,
    @PathVariable("pageSize") int pageSize
  ) {
    PaginationFilter filter = new PaginationFilter(pageNumber, pageSize);
    List<SubscriptionDTO> list = service.getAll(
      filter.getPageNumber(),
      filter.getPageSize()
    );

    return list == null
      ? ResponseEntity.notFound().build()
      : ResponseEntity.ok(list);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Subscription> findSubscriptionById(
    @PathVariable(value = "id") long id
  ) {
    // Implement
  }

  @PostMapping
  public ResponseEntity<Long> saveSubscription(
    @RequestBody Subscription entity
  ) {
    Long id = service.create(entity);
    return ResponseEntity.ok(id);
  }

  @PutMapping
  public ResponseEntity updateSubscription(@RequestBody Subscription entity) {
    boolean succeeded = service.update(entity);
    return succeeded
      ? (ResponseEntity<Object>) ResponseEntity.ok()
      : ResponseEntity.notFound().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity deleteSubscription(@PathVariable("id") Long id) {
    service.delete(id);

    return ResponseEntity.noContent().build();
  }
}
