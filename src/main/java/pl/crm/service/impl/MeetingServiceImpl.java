package pl.crm.service.impl;

import pl.crm.model.meeting.Meeting;
import pl.crm.model.meeting.MeetingType;
import pl.crm.repository.MeetingRepository;
import org.springframework.stereotype.Service;
import pl.crm.service.MeetingService;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MeetingServiceImpl implements MeetingService {

    private final MeetingRepository meetingRepository;

    public MeetingServiceImpl(MeetingRepository meetingRepository) {
        this.meetingRepository = meetingRepository;
    }

    @Override
    public void saveMeet(Meeting meeting) {
        meetingRepository.save(meeting);
    }

    @Override
    public List<Meeting> getMeets() {
        return meetingRepository.findAll();
    }

    @Override
    public List<MeetingType> getMeetingstypes() {
        MeetingType[] values = MeetingType.values();
        return Arrays.stream(values).toList();
    }

    public Meeting getMeetingById(Long id) {
        return meetingRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Meeting with ID " + id + " not found"));
    }

    public void updateMeeting(Meeting meeting) {
        Long meetingId = meeting.getVisitId();
        Meeting existingMeeting = meetingRepository.findById(meetingId)
                .orElseThrow(() -> new IllegalStateException("Meeting with ID " + meetingId + " does not exist."));

        existingMeeting.setMeetingType(meeting.getMeetingType());
        existingMeeting.setVisitDate(meeting.getVisitDate());
        existingMeeting.setOutcome(meeting.getOutcome());
        existingMeeting.setNotes(meeting.getNotes());

        meetingRepository.save(existingMeeting);
    }

}
