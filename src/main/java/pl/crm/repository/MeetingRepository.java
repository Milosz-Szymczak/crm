package pl.crm.repository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;
import pl.crm.model.meeting.Meeting;

@Repository
public interface MeetingRepository extends ListCrudRepository<Meeting, Long> {
}
