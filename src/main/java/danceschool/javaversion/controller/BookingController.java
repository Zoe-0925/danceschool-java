package danceschool.javaversion.controller;

import danceschool.javaversion.dto.BookingDTO;
import danceschool.javaversion.filter.PaginationFilter;
import danceschool.javaversion.model.Booking;
import danceschool.javaversion.service.BookingService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

  @Autowired
  BookingService service;

  @GetMapping("page/{pageNumber}/size/{pageSize}/")
  @Cacheable("_bookings_")
  public ResponseEntity findAllBookings(
    @PathVariable int pageNumber,
    @PathVariable int pageSize
  ) {
    PaginationFilter filter = new PaginationFilter(pageNumber, pageSize);

    List<BookingDTO> list = service.getAll(
      filter.getPageNumber(),
      filter.getPageSize()
    );

    if (list == null) {
      return ResponseEntity.notFound().build();
    } else {
      return ResponseEntity.ok(list);
    }
  }

  //TODO
  @GetMapping("page/{pageNumber}/size/{pageSize}/count")
  public ResponseEntity findAllBookingsWithCount(
    @PathVariable("pageNumber") int pageNumber,
    @PathVariable("pageSize") int pageSize
  ) {
    return null;
  }

  @GetMapping("page/{PageNumber}/size/{PageSize}/")
  public ResponseEntity findBookingsByCourse() {
    return null;
  }

  @GetMapping
  public ResponseEntity findBookingsByStudent() {
    return null;
  }

  @PostMapping
  public ResponseEntity saveBooking(@RequestBody Booking entity)
    throws Exception {
    Long id = service.create(entity);
    return ResponseEntity.ok(id);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity deleteBooking(@PathVariable("id") Long id) {
    try {
      service.delete(id);
      return ResponseEntity.noContent().build();
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
