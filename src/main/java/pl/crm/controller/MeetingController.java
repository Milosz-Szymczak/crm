package pl.crm.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.crm.model.Customer;
import pl.crm.model.meeting.Meeting;
import pl.crm.model.meeting.MeetingType;
import pl.crm.model.users.User;
import pl.crm.security.service.UserService;
import pl.crm.service.CustomerService;
import pl.crm.service.MeetingService;

import java.util.List;

@Controller
public class MeetingController {
    private final MeetingService meetingService;
    private final CustomerService customerService;
    private final UserService userService;

    public MeetingController(MeetingService meetingService, CustomerService customerService, UserService userService) {
        this.meetingService = meetingService;
        this.customerService = customerService;
        this.userService = userService;
    }


    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/form-add-meeting")
    public String addNewMeet(Model model) {
        List<MeetingType> meetingType = meetingService.getMeetingstypes();
        List<Customer> customers = customerService.getCustomer();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String user = authentication.getName();
        User currentUser = userService.findByUsername(user);

        model.addAttribute("customer", customers);
        model.addAttribute("user", currentUser);
        model.addAttribute("meeting", new Meeting());
        model.addAttribute("meetingType", meetingType);
        return "form-add-meeting";
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PostMapping("/form-add-meeting")
    public String saveMeet(@ModelAttribute("meeting") Meeting meeting) {
        meetingService.saveMeet(meeting);
        return "redirect:/meeting";
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/meeting")
    public String meets(Model model) {
        List<Meeting> meets = meetingService.getMeets();
        model.addAttribute("meeting", meets);
        return "meeting";
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/form-update-meeting/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        List<MeetingType> meetingType = meetingService.getMeetingstypes();
        Meeting meeting = meetingService.getMeetingById(id);
        model.addAttribute("meetingType", meetingType);

        model.addAttribute("meeting", meeting);
        return "form-update-meeting";
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping("/update-meeting")
    public String updateMeeting(@ModelAttribute("meeting") Meeting meeting) {
        meetingService.updateMeeting(meeting);
        return "redirect:/meeting";
    }
}
