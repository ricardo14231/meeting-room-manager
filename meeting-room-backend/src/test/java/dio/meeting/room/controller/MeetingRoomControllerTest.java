package dio.meeting.room.controller;

import dio.meeting.room.dto.MeetingRoomDTO;
import dio.meeting.room.exceptions.MeetingRoomBadRequestException;
import dio.meeting.room.mapper.MeetingRoomMapper;
import dio.meeting.room.message.MessageResponse;
import dio.meeting.room.model.MeetingRoomModel;
import dio.meeting.room.repository.MeetingRoomRepository;
import dio.meeting.room.service.MeetingRoomService;
import dio.meeting.room.utils.MeetingRoomBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DisplayName("Service Meeting Room.")
class MeetingRoomControllerTest {

    private final MeetingRoomMapper meetingMapper = MeetingRoomMapper.INSTANCE;

    @InjectMocks
    private MeetingRoomService meetingRoomService;

    @Mock
    private MeetingRoomRepository meetingRoomRepository;

    @BeforeEach
    void setUp() {
        MeetingRoomModel meeting = MeetingRoomBuilder.createMeeting();
        BDDMockito.when(meetingRoomRepository.save(Mockito.any())).thenReturn(meeting);

        BDDMockito.when(meetingRoomRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(meeting));

        BDDMockito.when(meetingRoomRepository.findAll()).thenReturn(Collections.singletonList(meeting));

        BDDMockito.doNothing().when(meetingRoomRepository).deleteById(Mockito.anyLong());
    }

    @Test
    @DisplayName("Returns save meeting room successful.")
    void create_returnsMessageCreate_whenSuccessful() {
        MeetingRoomDTO meetingToSave = meetingMapper.toDTO(MeetingRoomBuilder.createMeeting());
        meetingToSave.setId(null);

        String responseMessage = meetingRoomService.createMeetingRoom(meetingToSave);

        Assertions.assertEquals(MessageResponse
                .messageObjCreate(1L, "Sala de reunião"), responseMessage);

        Assertions.assertTrue(responseMessage.contains("1"));
    }

    @Test
    @DisplayName("Returns meeting room by id")
    void findById_returnsMeetingRoom_whenSuccessful() {
        MeetingRoomDTO expectedMeeting = meetingMapper.toDTO(MeetingRoomBuilder.createMeeting());

        MeetingRoomDTO responseMeeting = meetingRoomService.findByIdMeetingRoom(expectedMeeting.getId());

        Assertions.assertEquals(expectedMeeting.getId(), responseMeeting.getId());

        Assertions.assertEquals(expectedMeeting.getNameMeeting(), responseMeeting.getNameMeeting());
    }

    @Test
    @DisplayName("Returns meeting bad request exception room by id")
    void findById_returnsMeetingBadRequestException_whenMeetingRoomNotFound() {

        BDDMockito.doReturn(Optional.empty()).when(meetingRoomRepository).findById(Mockito.anyLong());

        Assertions.assertThrows(
                MeetingRoomBadRequestException.class, () -> meetingRoomService.findByIdMeetingRoom(1L));
    }

    @Test
    @DisplayName("Returns list of meeting room.")
    void listAll_returnsListOfMeetingRoom_whenSuccessful() {
        List<MeetingRoomDTO> expectedListMeeting = Collections.singletonList(
                meetingMapper.toDTO(MeetingRoomBuilder.createMeeting()));

        List<MeetingRoomDTO> responseList = meetingRoomService.listAllMeetingRoom();

        Assertions.assertFalse(responseList.isEmpty());

        Assertions.assertTrue(responseList.size() == expectedListMeeting.size());

        Assertions.assertEquals(expectedListMeeting, responseList);
    }

    @Test
    @DisplayName("Returns list empty.")
    void listAll_returnsListEmpty_whenSuccessful() {
        List<MeetingRoomDTO> expectedListMeeting = Collections.emptyList();

        BDDMockito.when(meetingRoomRepository.findAll()).thenReturn(Collections.emptyList());

        List<MeetingRoomDTO> responseList = meetingRoomService.listAllMeetingRoom();

        Assertions.assertTrue(responseList.isEmpty());
    }

    @Test
    @DisplayName("Returns message successful when update.")
    void update_returnsMessageSuccess_whenSuccessful() {
        MeetingRoomDTO meetingToUpdate = meetingMapper.toDTO(MeetingRoomBuilder.createMeeting());

        String responseMessage = meetingRoomService.updateMeetingRoom(meetingToUpdate.getId(), meetingToUpdate);

        Assertions.assertEquals(responseMessage,
                MessageResponse.messageObjUpdate(meetingToUpdate.getId(), "Sala de reunião"));

        Assertions.assertTrue(responseMessage.contains("1"));
    }

    @Test
    @DisplayName("Returns meeting bad request exception to update meeting room.")
    void update_returnsMeetingBadRequest_whenMeetingRoomNotFound() {
        MeetingRoomDTO meetingToUpdate = meetingMapper.toDTO(MeetingRoomBuilder.createMeeting());

        BDDMockito.doReturn(Optional.empty()).when(meetingRoomRepository).findById(Mockito.anyLong());

        Assertions.assertThrows(MeetingRoomBadRequestException.class,
                () -> meetingRoomService.updateMeetingRoom(meetingToUpdate.getId(), meetingToUpdate));
    }


    @Test
    @DisplayName("Returns message meeting room deleted successful.")
    void delete_returnsMessageSuccess_whenSuccessful() {
        String responseMessage = meetingRoomService.deleteMeetingRoom(1L);

        Assertions.assertTrue(responseMessage.contains("1"));
    }

    @Test
    @DisplayName("returns meeting room exception not found when deleting the meeting room.")
    void delete_returnsMeetingBadRequestException_whenMeetingRoomNotFound() {

        BDDMockito.doReturn(Optional.empty()).when(meetingRoomRepository).findById(Mockito.anyLong());

        Assertions.assertThrows(MeetingRoomBadRequestException.class,
                () -> meetingRoomService.deleteMeetingRoom(1L));
    }
}