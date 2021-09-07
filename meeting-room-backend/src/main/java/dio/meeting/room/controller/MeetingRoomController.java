package dio.meeting.room.controller;

import dio.meeting.room.dto.MeetingRoomDTO;
import dio.meeting.room.service.MeetingRoomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("meeting")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api("API Gerenciamento de sala de reuniões.")
public class MeetingRoomController {

    private final MeetingRoomService meetingRoomService;

    @PostMapping("/create")
    @ApiOperation("Salva uma nova agenda da sala de reuniões.")
    public ResponseEntity<String> createMeetingRoom(@RequestBody @Valid MeetingRoomDTO meetingRoomDTO) {
        String response = meetingRoomService.createMeetingRoom(meetingRoomDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation("Retorna o agendamento da sala de reuniões por ID.")
    public ResponseEntity<MeetingRoomDTO> findByIdMeetingRoom(@PathVariable Long id) {
        return new ResponseEntity<>(meetingRoomService.findByIdMeetingRoom(id), HttpStatus.OK);
    }

    @GetMapping("/list")
    @ApiOperation("Retorna a lista de agendas da sala de reuniões.")
    public ResponseEntity<List<MeetingRoomDTO>> listAllMeetingRoom() {
        return new ResponseEntity<>(meetingRoomService.listAllMeetingRoom(), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    @ApiOperation("Atualiza a agenda de uma sala de reuniões.")
    public ResponseEntity<String>
        updateMeetingRoom(@RequestBody @Valid MeetingRoomDTO meetingRoomDTO, @PathVariable Long id) {

        return new ResponseEntity<>(meetingRoomService.updateMeetingRoom(id, meetingRoomDTO), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("Deleta uma agenda.")
    public ResponseEntity<String> deleteMeetingRoom(@PathVariable Long id) {
        return new ResponseEntity<>(meetingRoomService.deleteMeetingRoom(id), HttpStatus.OK);
    }
}
