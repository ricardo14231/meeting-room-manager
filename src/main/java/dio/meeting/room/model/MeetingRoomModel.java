package dio.meeting.room.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MeetingRoomModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String nameMeeting;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false, length = 5)
    private String startHour;

    @Column(nullable = false, length = 5)
    private String endHour;

    @Column
    private LocalDateTime createAt;
}
