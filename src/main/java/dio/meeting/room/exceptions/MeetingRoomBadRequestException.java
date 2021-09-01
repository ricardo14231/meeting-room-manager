package dio.meeting.room.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MeetingRoomBadRequestException extends RuntimeException {

    public MeetingRoomBadRequestException(String message) {
        super(message);
    }
}
