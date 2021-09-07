package dio.meeting.room.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MeetingRoomDTO {

    private Long id;

    @NotEmpty
    @Size(min = 3, max = 50)
    private String nameMeeting;

    @NotNull
    private LocalDate date;

    @NotEmpty
    @Size(min = 5, max = 5)
    private String startHour;

    @NotEmpty
    @Size(min = 5, max = 5)
    private String endHour;
}
