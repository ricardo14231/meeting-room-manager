import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { RoomService } from 'src/app/core/service/room.service';
import { MeetingRoom } from 'src/app/shared/models/meeting.model';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {

  meetings: MeetingRoom [] = [];

  constructor(
    private roomService: RoomService,
    private router: Router,
    private changeDetectorRefs: ChangeDetectorRef
  ) { }

  ngOnInit(): void {    
    this.roomService.listAllMeetingRoom().subscribe({
      next: response => this.meetings = response,
      error: err => console.log(err)     
    })
  }

  editRoom(id: number): void {
    this.roomService.room = this.meetings[id];
    this.router.navigate(['/room/edit']);
  }

  deleteRoom(index: number): void {
    this.roomService.deleteMeetingRoom(index).subscribe({
      next: (ss) => {
        this.reloadData()
        console.log(ss)

      },
      error: err => console.log(err)
    });
  }

  detailsRoom(index: number): void {

  }

  private reloadData(): void {
    
    this.roomService.listAllMeetingRoom().subscribe(
      res => {
        console.log(res)

        this.meetings = res
        this.changeDetectorRefs.detectChanges();
      })
  }

}
