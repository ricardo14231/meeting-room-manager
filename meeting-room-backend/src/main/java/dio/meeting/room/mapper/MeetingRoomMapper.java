package dio.meeting.room.mapper;

import dio.meeting.room.dto.MeetingRoomDTO;
import dio.meeting.room.model.MeetingRoomModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MeetingRoomMapper {

    MeetingRoomMapper INSTANCE = Mappers.getMapper(MeetingRoomMapper.class);

    MeetingRoomModel toModel(MeetingRoomDTO meetingRoomDTO);

    MeetingRoomDTO toDTO(MeetingRoomModel meetingRoomModel);
}
