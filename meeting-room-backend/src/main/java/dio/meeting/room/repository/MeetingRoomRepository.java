package dio.meeting.room.repository;

import dio.meeting.room.model.MeetingRoomModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetingRoomRepository extends JpaRepository <MeetingRoomModel, Long>{
}
