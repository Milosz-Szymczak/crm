package pl.crm.service;

import org.springframework.stereotype.Service;
import pl.crm.model.meeting.Meeting;
import pl.crm.model.meeting.MeetingType;

import java.util.List;

@Service
public interface MeetingService {


    void saveMeet(Meeting meeting);

    List<Meeting> getMeets();

    List<MeetingType> getMeetingstypes();

    Meeting getMeetingById(Long id);

    void updateMeeting(Meeting meeting);
}
