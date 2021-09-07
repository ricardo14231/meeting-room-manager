package dio.meeting.room.service;

import dio.meeting.room.dto.MeetingRoomDTO;
import dio.meeting.room.exceptions.MeetingRoomBadRequestException;
import dio.meeting.room.mapper.MeetingRoomMapper;
import dio.meeting.room.message.MessageResponse;
import dio.meeting.room.model.MeetingRoomModel;
import dio.meeting.room.repository.MeetingRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MeetingRoomService {

    private final MeetingRoomMapper meetingRoomMapper = MeetingRoomMapper.INSTANCE;

    private final MeetingRoomRepository meetingRoomRepository;

    public String createMeetingRoom(MeetingRoomDTO meetingRoomDTO) {
        MeetingRoomModel meetingToSave = meetingRoomMapper.toModel(meetingRoomDTO);
        Long id = meetingRoomRepository.save(meetingToSave).getId();
        return MessageResponse.messageObjCreate(id, "Sala de reunião");
    }

    public MeetingRoomDTO findByIdMeetingRoom(Long id) {
        MeetingRoomModel meetingRoom = verifyIfExists(id);
        return meetingRoomMapper.toDTO(meetingRoom);
    }

    public List<MeetingRoomDTO> listAllMeetingRoom() {
        return meetingRoomRepository.findAll().stream()
                .map(meetingRoomMapper::toDTO)
                .collect(Collectors.toList());
    }

    public String updateMeetingRoom(Long id, MeetingRoomDTO meetingRoomDTO) {
        verifyIfExists(id);
        meetingRoomRepository.save(meetingRoomMapper.toModel(meetingRoomDTO));
        return MessageResponse.messageObjUpdate(id, "Sala de reunião");
    }

    public String deleteMeetingRoom(Long id) {

        verifyIfExists(id);
        meetingRoomRepository.deleteById(id);
        return MessageResponse.messageObjDelete(id, "Sala de reunião");
    }

    private MeetingRoomModel verifyIfExists(Long id) {
        return meetingRoomRepository.findById(id)
                .orElseThrow(() -> new MeetingRoomBadRequestException("Element not found"));
    }

}
