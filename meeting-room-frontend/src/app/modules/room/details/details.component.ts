import { Component, OnInit } from '@angular/core';
import { RoomService } from 'src/app/core/service/room.service';
import { MeetingRoom } from 'src/app/shared/models/meeting.model';

@Component({
  selector: 'app-details',
  templateUrl: './details.component.html',
  styleUrls: ['./details.component.css']
})
export class DetailsComponent implements OnInit {

  room: MeetingRoom

  constructor(
    private roomService: RoomService
  ) { }

  ngOnInit(): void {
    this.room = this.roomService.roomDetails;
  }
}
