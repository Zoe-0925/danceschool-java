package danceschool.javaversion.controller;

import danceschool.javaversion.model.Membership;
import danceschool.javaversion.service.MembershipService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api/memberships")
public class MembershipController {

  @Autowired
  MembershipService service;

  @GetMapping
  public ResponseEntity findAll() {
    List<Membership> list = service.getAll();

    if (list == null) {
      return ResponseEntity.notFound().build();
    } else {
      return ResponseEntity.ok(list);
    }
  }

  @PostMapping
  public ResponseEntity saveMembership(@RequestBody Membership entity)
    throws Exception {
    try {
      Long id = service.create(entity);
      return ResponseEntity.ok(id);
    } catch (Exception e) {
      return ResponseEntity.notFound().build(); //TODO update the error
    }
  }

  @PutMapping
  public ResponseEntity updateMembership(@RequestBody Membership entity) {
    try {
      service.update(entity);
      return (ResponseEntity) ResponseEntity.ok();
    } catch (Exception e) {
      return ResponseEntity.notFound().build();
    }
  }

  //TODO
  @DeleteMapping("/{id}")
  public ResponseEntity deleteMembership(@PathVariable("id") Long id) {
    try {
      service.deleteMembership(id);
      return (ResponseEntity) ResponseEntity.ok();
    } catch (Exception e) {
      return ResponseEntity.notFound().build(); //TODO update the error
    }
  }
}
