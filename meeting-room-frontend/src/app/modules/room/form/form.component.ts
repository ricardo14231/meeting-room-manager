import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
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
  private subscription: Subscription;

  constructor(
    public roomService: RoomService,
    private formBuilder: FormBuilder,
    private router: Router,
  ) { }

  ngOnInit(): void {
    this.initObjRoom();
    this.createForm(this.room)
    if (this.roomService.edit) {
      this.room = this.roomService.room;
      this._idRoomEdit = this.room.id;
      this.createForm(this.room);
    }
  }

  onSave(): void {

    this.room = this.roomForm.getRawValue() as MeetingRoom;

    if (this.roomService.edit) {
      this.room.id = this._idRoomEdit;
    }

    this.roomService.onSave(this.room);

    this.subscription = this.roomService.responseOnSave.subscribe({
      next: () => this.router.navigate(['/room']),
      error: err => {
        let fieldsError = JSON.parse(err.error).fields;
        let messageError = JSON.parse(err.error).fieldsMessage;

        alert(`Erro ao salvar o agendamento.\n\nCampo(s): ${fieldsError}\n\nMessagem: ${messageError}`)
      }
    })
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
      nameMeeting: [room.nameMeeting, [Validators.required, Validators.minLength(3), Validators.maxLength(50)]],
      date: [room.date, [Validators.required]],
      startHour: [room.startHour, [Validators.required, Validators.pattern(/^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$/)]],
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

  ngOnDestroy(): void {
    this.subscription?.unsubscribe();
  }
}
