package danceschool.javaversion.service;

import danceschool.javaversion.dto.BookingCountDTO;
import danceschool.javaversion.dto.BookingDTO;
import danceschool.javaversion.dto.CountByDate;
import danceschool.javaversion.dto.MembershipNameWithCountDTO;
import danceschool.javaversion.exception.RecordNotFoundException;
import danceschool.javaversion.model.Booking;
import danceschool.javaversion.model.DanceClass;
import danceschool.javaversion.model.Student;
import danceschool.javaversion.repository.BookingRepository;
import danceschool.javaversion.repository.DanceClassRepository;
import danceschool.javaversion.repository.StudentRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
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
  public List<BookingDTO> getAll(int page, int pageSize) {
    Pageable paging = PageRequest.of(
      page,
      pageSize,
      Sort.by("bookingDate").descending()
    );

    List<BookingDTO> bookingList = repository
      .findAll(paging)
      .getContent()
      .stream()
      .map(this::convertToBookingDTO)
      .collect(Collectors.toList());

    return bookingList;
  }

  private BookingDTO convertToBookingDTO(Booking booking) {
    return new BookingDTO(booking);
  }

  //TODO
  @Cacheable
  public List<BookingDTO> getByCourse(Long id) {
    return repository
      .getByCourse(id)
      .stream()
      .map(this::convertToBookingDTO)
      .collect(Collectors.toList());
  }

  //TODO
  @Cacheable
  public List<BookingDTO> getByStudent(Long id) {
    return repository
      .getByStudent(id)
      .stream()
      .map(this::convertToBookingDTO)
      .collect(Collectors.toList());
  }

  @Cacheable
  public BookingCountDTO getWithCount() {
    Iterable<Booking> bookings = repository.findAll();

    List<BookingDTO> bookingList = StreamSupport
      .stream(bookings.spliterator(), false)
      .map(this::convertToBookingDTO)
      .collect(Collectors.toList());

    return new BookingCountDTO(bookingList, bookingList.size());
  }

  public List<Integer> getTopDanceClassIDs() {
    return repository.getTopDanceClassIDs();
  }

  //TODO
  @Cacheable
  public int findBookingCountByMonth() {
    return 0;
  }

  @Cacheable
  public int getTotal() {
    return repository.getCount();
  }

  @Cacheable
  public List<CountByDate> getLastWeeksBookings() {
    return repository.getLastWeeksBookings();
  }

  @Cacheable
  public List<MembershipNameWithCountDTO> getByMembership() {
    return repository.getByMembership();
  }

  //TODO
  @Cacheable
  public int findBookingCountByYear() {
    return 0;
  }

  @CachePut
  public Long create(Booking entity) throws Exception {
    try {
      entity = repository.save(entity);
      Optional<Student> student = studentRepository.findById(
        entity.getStudentID()
      );
      student.get().getBookings().add(entity);
      Optional<DanceClass> danceClass = danceClassRepository.findById(
        entity.getClassID()
      );
      danceClass.get().getBookings().add(entity);
      return entity.getId();
    } catch (Exception e) {
      throw e;
    }
  }

  @CacheEvict(allEntries = true)
  public void delete(Long id) throws RecordNotFoundException {
    Optional<Booking> booking = repository.findById(id);

    if (booking != null) {
      repository.deleteById(id);
      Booking entity = booking.get();
      Optional<Student> student = studentRepository.findById(
        entity.getStudentID()
      );
      student.get().getBookings().remove(entity);
      Optional<DanceClass> danceClass = danceClassRepository.findById(
        entity.getClassID()
      );
      danceClass.get().getBookings().remove(entity);
    } else {
      //     throw new RecordNotFoundException("No Booking record exist for given id");
    }
  }
}
