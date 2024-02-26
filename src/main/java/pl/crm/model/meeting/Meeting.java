package pl.crm.model.meeting;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import pl.crm.model.Customer;
import pl.crm.model.users.User;

import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "meetings")
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "visit_id")
    private Long visitId;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "meetingtype")
    private MeetingType meetingType;

    @Column(name = "visit_date", nullable = false)
    private String visitDate;

    @Column(name = "outcome", nullable = false)
    private String outcome;

    @Column(name = "notes")
    private String notes;
}
