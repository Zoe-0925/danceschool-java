package danceschool.javaversion.service;

import danceschool.javaversion.exception.RecordNotFoundException;
import danceschool.javaversion.model.Booking;
import danceschool.javaversion.repository.BookingRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Bookingervice {

  @Autowired
  BookingRepository repository;

  public List<Booking> getAll() {
    List<Booking> bookingList = repository.findAll();

    //TODO DTO

    return bookingList;
  }

  //TODO
  public List<BookingDTO> getByCourse(String name) {}

  //TODO
  public List<BookingDTO> getByStudent(int id) {}

  public BookingWithCountDTO getWithCount() {
    List<Booking> BookingList = repository.findAll();

    if (BookingList.size() > 0) {
      return new BookingWithCountDTO(BookingList, BookingList.length());
    } else {
      return null;
    }
  }

  //TODO
  public int findBookingCountByMonth() {}

  //TODO
  public int findBookingCountByYear() {}

  public int create(Booking entity) {
    Booking created = repository.save(entity);
    return created.getId();
  }

  public Boolean Update(Booking entity) throws RecordNotFoundException {
    Optional<Booking> Booking = repository.findById(entity.getId());

    if (Booking.isPresent()) {
      Booking newEntity = Booking.get();
      newEntity.setBookingDate(entity.getsetBookingDate());
      newEntity.setStudentID(entity.getStudentID());
      newEntity.setInstructorID(entity.getInstructorID());
      newEntity.setMembershipID(entity.getMembershipID());
      newEntity.setClassID(entity.getClassID());

      newEntity = repository.save(newEntity);

      return newEntity;
    } else {
      return null;
    }
  }

  public void delete(int id) throws RecordNotFoundException {
    Optional<Booking> Booking = repository.findById(id);

    if (Booking.isPresent()) {
      repository.deleteById(id);
    } else {
      throw new RecordNotFoundException("No Booking record exist for given id");
    }
  }
}