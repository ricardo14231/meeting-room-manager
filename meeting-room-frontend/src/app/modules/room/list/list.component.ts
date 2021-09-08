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
      error: () => alert("Não foi possível acessar a API.")   
    })
  }

  editRoom(id: number): void {
    this.roomService.room = this.meetings[id];
    this.router.navigate(['/room/edit']);
  }

  deleteRoom(index: number): void {
    this.roomService.deleteMeetingRoom(index).subscribe({
      next: () => this.reloadData(),
      error: err => alert(err?.error)
    });
  }

  detailsRoom(index: number): void {
    this.roomService.roomDetails = this.meetings[index];
    this.router.navigate(['/room/details']);
  }

  private reloadData(): void {
    
    this.roomService.listAllMeetingRoom().subscribe(
      res => {
        this.meetings = res
        this.changeDetectorRefs.detectChanges();
      })
  }

}
