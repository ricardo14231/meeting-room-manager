import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { RoomService } from 'src/app/core/service/room.service';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent {

  constructor(
    private roomService: RoomService,
    private router: Router
  ) { }

  newRoom() {
    this.roomService.edit = false;
    this.router.navigate(['room/create']);
  }

}
