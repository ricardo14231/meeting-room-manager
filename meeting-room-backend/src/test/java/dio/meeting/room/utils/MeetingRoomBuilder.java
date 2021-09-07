package dio.meeting.room.utils;

import dio.meeting.room.model.MeetingRoomModel;

import java.time.LocalDate;

public class MeetingRoomBuilder {

    public static MeetingRoomModel createMeeting() {
        return MeetingRoomModel.builder()
                .id(1L)
                .nameMeeting("Room meeting.")
                .date(LocalDate.of(2021,10, 8))
                .startHour("14:00")
                .endHour("16:00")
                .build();
    }
}
