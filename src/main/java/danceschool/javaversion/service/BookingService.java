package danceschool.javaversion.service;

import danceschool.javaversion.dto.BookingDTO;
import danceschool.javaversion.exception.RecordNotFoundException;
import danceschool.javaversion.helper.SortDirection;
import danceschool.javaversion.model.Booking;
import danceschool.javaversion.model.Student;
import danceschool.javaversion.repository.BookingRepository;
import danceschool.javaversion.repository.DanceClassRepository;
import danceschool.javaversion.repository.StudentRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = { "_bookings_" }) // tells Spring where to store
public class BookingService {

  @Autowired
  BookingRepository repository;

  @Autowired
  StudentRepository studentRepository;

  @Autowired
  DanceClassRepository danceClassRepository;

  @Cacheable // caches the result of findAll() method
  public List<BookingDTO> getAll(int page, int size, String[] sort) {
    List<Booking> bookings = new ArrayList<Booking>();

    // sort=[field, direction]
    bookings.add(new Booking(SortDirection.getSortDirection(sort[1]), sort[0]));

    Pageable pagingSort = PageRequest.of(page, size, Sort.by(bookings));

    Page<Booking> bookingList = repository
      .findAll(pageReq)
      .getContent()
      .stream()
      .map(this::convertToBookingDTO)
      .collect(Collectors.toList());

    return bookingList;
  }

  private BookingDTO convertToBookingDTO(Booking booking) {
    BookingDTO bookingDTO = new BookingDTO();
    bookingDTO.setId(booking.getId());
    bookingDTO.setBookingDate(booking.getBookingDate());
    bookingDTO.setDate(booking.getDanceClass().getStartTime());
    bookingDTO.setClassID(booking.getClassID());
    bookingDTO.setStudentEmail(booking.getStudent().getEmail());
    bookingDTO.setCourseName(booking.getDanceClass().getCourseName());

    return bookingDTO;
  }

  //TODO
  @Cacheable
  public List<BookingDTO> getByCourse(String name) {}

  //TODO
  @Cacheable
  public List<BookingDTO> getByStudent(int id) {}

  @Cacheable
  public BookingWithCountDTO getWithCount() {
    List<Booking> BookingList = repository
      .findAll()
      .forEach(_bookings_with_count_::add);
    if (BookingList.size() > 0) {
      return new BookingWithCountDTO(BookingList, BookingList.length());
    } else {
      return null;
    }
  }

  //TODO
  @Cacheable
  public int findBookingCountByMonth() {}

  //TODO
  @Cacheable
  public int findBookingCountByYear() {}

  @CachePut
  public int create(Booking entity) throws Exception {
    try {
      entity = repository.save(entity);
      Student student = studentRepository.findById(entity.getStudentID);
      student.getBookings.add(entity);
      DanceClass danceClass = danceClassRepository.findById(entity.getClassID);
      danceClass.getBookings().add(entity);
      return entity.getId();
    } catch (Exception e) {
      throw e;
    }
  }

  @CacheEvict(allEntries = true)
  public void delete(int id) throws RecordNotFoundException {
    Booking entity = repository.findById(id);

    if (booking != null) {
      repository.deleteById(id);
      Student student = studentRepository.findById(entity.getStudentID);
      student.getBookings.remove(entity);
      DanceClass danceClass = danceClassRepository.findById(entity.getClassID);
      danceClass.getBookings().remove(entity);
    } else {
      throw new RecordNotFoundException("No Booking record exist for given id");
    }
  }
}
