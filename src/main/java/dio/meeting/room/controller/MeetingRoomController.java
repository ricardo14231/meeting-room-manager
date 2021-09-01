package dio.meeting.room.controller;

import dio.meeting.room.dto.MeetingRoomDTO;
import dio.meeting.room.service.MeetingRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("meeting")
public class MeetingRoomController {

    @Autowired
    private MeetingRoomService meetingRoomService;

    @PostMapping("/create")
    public ResponseEntity<String> createMeetingRoom(@RequestBody @Valid MeetingRoomDTO meetingRoomDTO) {
        String response = meetingRoomService.createMeetingRoom(meetingRoomDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MeetingRoomDTO> findByIdMeetingRoom(@PathVariable Long id) {
        return new ResponseEntity<>(meetingRoomService.findByIdMeetingRoom(id), HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<MeetingRoomDTO>> listAllMeetingRoom() {
        return new ResponseEntity<>(meetingRoomService.listAllMeetingRoom(), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String>
        updateMeetingRoom(@RequestBody @Valid MeetingRoomDTO meetingRoomDTO, @PathVariable Long id) {

        return new ResponseEntity<>(meetingRoomService.updateMeetingRoom(id, meetingRoomDTO), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMeetingRoom(@PathVariable Long id) {
        return new ResponseEntity<>(meetingRoomService.deleteMeetingRoom(id), HttpStatus.OK);
    }
}
