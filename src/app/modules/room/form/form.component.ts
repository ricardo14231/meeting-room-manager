import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { RoomService } from 'src/app/core/service/room.service';
import { MeetingRoom } from 'src/app/shared/models/meeting.model';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
export class FormComponent implements OnInit {

  roomForm: FormGroup;
  room: MeetingRoom;
  private _idRoomEdit: number;

  constructor(
    private roomService: RoomService,
    private formBuilder: FormBuilder,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.initObjRoom();
    this.createForm(this.room)
    if(this.roomService.edit) {
      this.room = this.roomService.room;
      this._idRoomEdit = this.room.id;
      this.createForm(this.room);
    }
  }

  onSave() {
    this.room = this.roomForm.getRawValue() as MeetingRoom;
    
    if(this.roomService.edit) {
      this.room.id = this._idRoomEdit;
    }

    this.roomService.onSave(this.room);

  }

  cancelForm() {
    this.resetForm();
    this.router.navigate(['room']);
  }

  private resetForm() {
    this.roomForm.reset();
  }

  private createForm(room: MeetingRoom): void {
    this.roomForm = this.formBuilder.group({
      nameMeeting: [room.nameMeeting, [Validators.required]],
      date: [room.date, [Validators.required]],
      startHour: [room.startHour, [Validators.required]],
      endHour: [room.endHour, [Validators.required]]
    })
  }

  private initObjRoom() {
    this.room = {
      id: null,
      nameMeeting: null,
      date: null,
      startHour: null,
      endHour: null
    }
  }

}
